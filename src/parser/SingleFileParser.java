package parser;

import java.util.ArrayList;

import ast.*;
import lexer.Lexer;
import token.*;
import type.EnumType;
import type.Type;
import utils.LexicalException;
import utils.Pair;
import utils.ParsingException;
import utils.Twin;
import utils.UnimplementedException;

public class SingleFileParser {
    
    public SingleFileParser(Lexer lex) {
        lex_ = lex;
        scope_ = new Scope();
    }
    
    public static enum State {
        PARSING,
        FINISHED
    }
    
    private Lexer lex_;
    private Scope scope_;
    
    // Some common helper functions
    private Identifier expectIdentifier(String errorMessage) throws Exception {
        if (!lex_.current().isIdentifier()) {
            reportParsingError("expect an identifier");
            return null;
        } else {
            return (Identifier) lex_.advance();
        }
    }
    
    private void expect(Token wish) throws Exception {
        if (lex_.current() != wish) {
            // report error: expect wish.toString() instead of lex_.current().toString()
            reportParsingError("expect an " + wish.toString() + " instead of "
                    + lex_.current().toString());
        }
    }

    // Parsing routines about module
    private void parseModuleDeclaration() throws Exception {
        if (lex_.advance(Keyword.MODULE)) {
            Identifier id = expectIdentifier("expect a module name");
            // set current module name to id
        } else {
            // this is an anonymous module
        }
    }
    
    private Twin<Identifier> parseImportName(Identifier preparsed) throws Exception {
        Identifier exportName = preparsed, alias = null;
        
        if (exportName == null)
            exportName = expectIdentifier("expect a export name");
        
        if (lex_.advance(Keyword.AS))
            alias = expectIdentifier("expect a alias name");
        
        return new Twin<Identifier>(exportName, alias);
    }
    
    /**
     * Parse import name sequence in following form:
     * ID ("as" ID)? ("," ID ("as" ID)?)*
     * @param firstId 
     * @throws Exception 
     */
    private void parseImportNameList(Identifier firstId) throws Exception {
        Twin<Identifier> idPair = parseImportName(firstId);
        
        while (lex_.advance(BinaryOp.COMMA)) {
            idPair = parseImportName(null);
            // do something, for example, collecting idPair
        }
    }
    
    private void parseImportClause() throws Exception {
        /*
         * Single import clause has following several forms:
         * 1. import SomeModule;
         * 2. import Something from SomeModule;
         * 3. import Something as MyThing from SomeModule;
         *    import Something as MyThing, SomeStuff as MyStuff from SomeModule;
         */
        while (lex_.advance(Keyword.IMPORT)) {
            if (!lex_.current().isIdentifier()) {
                // report error: expect a module name or a export name
            }
            
            Identifier id = (Identifier) lex_.advance();
            
            if (lex_.current() == Punctuator.SEMICOLON) { // Situation 1
                
            } else { // Situation 2 and 3
                parseImportNameList(id);
                expect(Keyword.FROM);
                Identifier module = expectIdentifier("expect a module name");
            }
        }
    }
    
    // Parsing routines about type system
    
    private Type parseTypeSpecifier() {
        /*
         * Some type specifier examples:
         * 1. int // just primitive type
         * 2. TypeName // user-defined type
         * 3. int[] // array type
         * 4. func(int)->int // function type
         */
        return null;
    }
    
    private ArrayList<Identifier> parseIdentifierList() throws Exception {
        ArrayList<Identifier> idList = new ArrayList<Identifier>();
        
        if (lex_.current().isIdentifier()) {
            idList.add((Identifier) lex_.advance());
            
            while (lex_.advance(BinaryOp.COMMA)) {
                idList.add(expectIdentifier("expect a identifier after a comma"));
            }
        }
        
        return idList;
    }
    
    private void parseParenthesisExpression() throws Exception {
        expect(Punctuator.LPAREN);
        parseExpression();
        expect(Punctuator.RPAREN);
    }
    
    private EnumType parseEnumDeclaration() throws Exception {
        /*
         * Enum type declaration is in the following form:
         * enum EnumName {
         *   EnumValue1, EnumValue2, EnumValue3
         * }
         */
        expect(Keyword.ENUM);
        Identifier enumName = expectIdentifier("expect a enum name");
        expect(Punctuator.LBRACE);
        ArrayList<Identifier> a = parseIdentifierList();
        // TODO: working on a and enumName
        expect(Punctuator.RBRACE);
        
        EnumType type = new EnumType(enumName.getLiteral());
        type.addEnumValue(a);
        return type;
    }
    
    private void parseClassDeclaration() throws Exception {
        throw new UnimplementedException("Unimplemented parsing routinue: class");
    }
    
    private void parseAliasDeclaration() throws Exception {
        /*
         * Alias type declaration is in the following form:
         * type TypeA = TypeB;
         */
        expect(Keyword.TYPE);
        Identifier alias = expectIdentifier("expect a alias type name");
        expect(AssignmentOp.ASSIGN);
        Type origin = parseTypeSpecifier();
        // TODO: working on alias and origin
        expect(Punctuator.SEMICOLON);
    }
    
    private Pair<Identifier, AstNode> parseSingleConstantDeclaration() throws Exception {
        /*
         * Parsing something like `a = b`
         */
        Identifier id = expectIdentifier("expect a constant name");
        expect(AssignmentOp.ASSIGN);
        AstNode value = parseExpression();
        return new Pair<Identifier, AstNode>(id, value);
    }
    
    private void parseConstantDeclaration() throws Exception {
        /*
         * Constant declaration is in following form:
         * const TypeName ConstA = blablabla, ConstB = blablabla;
         */
        // expect(Keyword.CONST);
        Type constType = parseTypeSpecifier();
        Pair<Identifier, AstNode> assignment = parseSingleConstantDeclaration();
        
        while (lex_.advance(BinaryOp.COMMA)) {
            assignment = parseSingleConstantDeclaration();
            // TODO: working on assignment
        }
        expect(Punctuator.SEMICOLON);
    }
    
    private Pair<Identifier, AstNode> parseSingleVariableDeclaration() throws Exception {
        /*
         * Parsing something like `a = b` or just an `a`
         */
        Identifier id = expectIdentifier("expect a variable name");
        AstNode value = null;
        if (lex_.advance(AssignmentOp.ASSIGN)); {
            value = parseExpression();
        }
        return new Pair<Identifier, AstNode>(id, value);
    }
    
    private void parseVariableDeclaration() throws Exception {
        /*
         * Constant declaration is in following form:
         * TypeName VarA = blablabla, VarB;
         */
        Type varType = parseTypeSpecifier();
        Pair<Identifier, AstNode> assignment = parseSingleVariableDeclaration();
        
        while (lex_.advance(BinaryOp.COMMA)) {
            assignment = parseSingleVariableDeclaration();
            // TODO: working on assignment
        }
        expect(Punctuator.SEMICOLON);
    }
    
    private void parseIfStatement() throws Exception {
        expect(Keyword.IF);
        parseParenthesisExpression();
        parseStatement();
        if (lex_.advance(Keyword.ELSE))
            parseStatement();
    }
    
    private void parseWhileLoop() throws Exception {
        expect(Keyword.WHILE);
        parseParenthesisExpression();
        parseStatement();
    }
    
    private void parseDoWhileLoop() throws Exception {
        /*
         * Do-while loop is in following form:
         * do 
         *   Statement
         * while (Expression);
         */
        expect(Keyword.DO);
        parseStatement();
        expect(Keyword.WHILE);
        parseParenthesisExpression();
        expect(Punctuator.SEMICOLON);
    }
    
    private void parseForLoop() throws Exception {
        expect(Keyword.FOR);
    }
    
    private void parseStatementBlock() throws Exception {
        expect(Punctuator.LBRACE);
        
        expect(Punctuator.RBRACE);
    }
    
    private void parseStatement() throws Exception {
        switch (lex_.current().getTag()) {
        case CLASS:
            parseClassDeclaration();
            break;
        case DO:
            parseDoWhileLoop();
            break;
        case ENUM:
            parseEnumDeclaration();
            break;
        case FOR:
            parseForLoop();
            break;
        case IF:
            parseIfStatement();
            break;
        case WHILE:
            parseWhileLoop();
            break;
        case LBRACE:
            parseStatementBlock();
            break;
        default:
            // TODO: report error unrecognized token
        }
    }
    
    // Parsing routines about expression
    // We use top down operator precedence method by Pratt
    
    private AstNode parseNullDenotation(Token token) {
        return null;
    }
    
    private AstNode parseLeftNotation(BinaryOp op, AstNode left) throws Exception {
        AstNode right = parseExpression(op.getPrecedence());
        // TODO: do something with left and right
        
        if (op.isBinaryOp()) {
            
        }
        return null;
    }
    
    private AstNode parseTernaryCondition(BinaryOp op, AstNode preparsed) throws Exception {
        AstNode cond = preparsed,
                then = parseExpression(0),
                otherwise = null;
        expect(Punctuator.COLON);
        otherwise = parseExpression(0);
        // TODO: do something with cond, then and otherwise
        return null;
    }
    
    private AstNode parseExpression(int rightBindingPower) throws Exception {
        Token token = lex_.advance();
        AstNode left = parseNullDenotation(token);
        while (true) {
            if (!lex_.current().isBinaryOp())
                break;
            
            BinaryOp op = (BinaryOp) lex_.current();
            if (rightBindingPower >= op.getPrecedence())
                break;
            
            lex_.advance();
            left = parseLeftNotation(op, left);
        }
        return left;
    }
    
    private AstNode parseExpression() throws Exception {
        return parseExpression(0);
    }
    
    private void parseModule() {
        
    }
    
    private void reportParsingError(String message) throws Exception {
        throw new ParsingException(message);
    }
    
}
