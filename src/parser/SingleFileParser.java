package parser;

import java.util.ArrayList;

import ast.*;
import lexer.Lexer;
import symbol.TypeSymbol;
import token.AssignmentOp;
import token.BinaryOp;
import token.Identifier;
import token.Keyword;
import token.Punctuator;
import token.Token;
import token.Value;
import utils.Pair;
import utils.ParsingException;
import utils.Twin;
import utils.UnimplementedException;

public class SingleFileParser {
    
    public SingleFileParser(Lexer lex) {
        lex_ = lex;
    }
    
    public static enum State {
        PARSING,
        FINISHED
    }
    
    public void parse() throws Exception {
    	parseModuleDeclaration();
    	parseImportClause();
    	parseDeclarations();
    }
    
    private Lexer lex_;
    private AstNodeFactory astNode_;
    
    private static final int LOWEST_PRECEDENCE = 0;
    
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
    
    private void parseExportDeclaration() {
    	
    }
    
    // Parsing routines about type system
    
    private TypeSpecifier parseTypeSpecifier() {
        /*
         * Some type specifier examples:
         * 1. int // just primitive type
         * 2. TypeName // user-defined type
         * 3. int[] // array type
         * 4. func(int)->int // function type
         */
        return null;
    }
    
    private ArrayList<String> parseEnumValueList() throws Exception {
        ArrayList<String> idList = new ArrayList<String>();
        
        if (lex_.current().isIdentifier()) {
        	Identifier id = (Identifier) lex_.advance();
            idList.add(id.getLiteral());
            
            while (lex_.advance(BinaryOp.COMMA)) {
            	id = expectIdentifier("expect a identifier after a comma"); 
                idList.add(id.getLiteral());
            }
        }
        
        return idList;
    }
    
    private Expression parseParenthesisExpression() throws Exception {
        expect(Punctuator.LPAREN);
        Expression expr = parseExpression();
        expect(Punctuator.RPAREN);
        return expr;
    }
    
    // Type declarations
    
    private EnumDeclaration parseEnumDeclaration() throws Exception {
        /*
         * Enum type declaration is in the following form:
         * enum EnumName {
         *   EnumValue1, EnumValue2, EnumValue3
         * }
         */
        expect(Keyword.ENUM);
        Identifier enumName = expectIdentifier("expect a enum name");
        expect(Punctuator.LBRACE);
        ArrayList<String> enumValues = parseEnumValueList();
        expect(Punctuator.RBRACE);
        return astNode_.createEnumDecl(enumName.getLiteral(), enumValues);
    }
    
    private ClassDeclaration parseClassDeclaration() throws Exception {
        throw new UnimplementedException("Unimplemented parsing routinue: class");
    }
    
    private TypeAliasDeclaration parseAliasDeclaration() throws Exception {
        /*
         * Alias type declaration is in the following form:
         * type TypeA = TypeB;
         */
        expect(Keyword.TYPE);
        Identifier alias = expectIdentifier("expect a alias type name");
        expect(AssignmentOp.ASSIGN);
        TypeSpecifier origin = parseTypeSpecifier();
        expect(Punctuator.SEMICOLON);
        return astNode_.createTypeAliasDecl(alias.getLiteral(), origin);
    }
    
    // Constant declaration
    
    private Pair<String, Expression> parseSingleConstantDeclaration() throws Exception {
        /*
         * Parsing something like `a = b`
         */
        Identifier id = expectIdentifier("expect a constant name");
        expect(AssignmentOp.ASSIGN);
        Expression value = parseExpression();
        return new Pair<String, Expression>(id.getLiteral(), value);
    }
    
    private ConstantDeclaration parseConstantDeclaration() throws Exception {
        /*
         * Constant declaration is in following form:
         * const TypeName ConstA = blablabla, ConstB = blablabla;
         */
        expect(Keyword.CONST);
        TypeSpecifier constType = parseTypeSpecifier();
        ArrayList<Pair<String, Expression>> constants = new ArrayList<Pair<String, Expression>>();
        constants.add(parseSingleConstantDeclaration());
        while (lex_.advance(BinaryOp.COMMA))
        	constants.add(parseSingleConstantDeclaration());
        expect(Punctuator.SEMICOLON);
        return astNode_.createConstantDecl(constType, constants);
    }
    
    // Variable declaration
    
    private Pair<String, Expression> parseSingleVariableDeclaration() throws Exception {
        /*
         * Parsing something like `a = b` or just an `a`
         */
        Identifier id = expectIdentifier("expect a variable name");
        Expression value = null;
        if (lex_.advance(AssignmentOp.ASSIGN)); {
            value = parseExpression();
        }
        return new Pair<String, Expression>(id.getLiteral(), value);
    }
    
    private VariableDeclaration parseVariableDeclaration() throws Exception {
        /*
         * Constant declaration is in following form:
         * TypeName VarA = blablabla, VarB;
         */
    	expect(Keyword.LET);
        TypeSpecifier varType = parseTypeSpecifier();
        ArrayList<Pair<String, Expression>> vars = new ArrayList<Pair<String, Expression>>();
        vars.add(parseSingleVariableDeclaration());
        while (lex_.advance(BinaryOp.COMMA))
        	vars.add(parseSingleVariableDeclaration());
        expect(Punctuator.SEMICOLON);
        return astNode_.createVariableDecl(varType, vars);
    }
    
    private void parseSingleArgument() throws Exception {
    	expectIdentifier("expect a argument name");
    	expect(Punctuator.COLON);
    	parseTypeSpecifier();
    }
    
    private void parseArgumentList() throws Exception {
    	expect(Punctuator.LPAREN);
    	if (lex_.current().isIdentifier()) {
    		do {
    			parseSingleArgument();
    		} while (lex_.advance(BinaryOp.COMMA));
    	}
    	expect(Punctuator.RPAREN);
    }
    
    private StatementBlock parseFunctionBody() throws Exception {
    	expect(Punctuator.LBRACE);
    	StatementBlock body = parseStatementBlock(Punctuator.RBRACE);
    	expect(Punctuator.RBRACE);
    	return body;
    }
    
    private FunctionDeclaration parseFunctionDeclaration() throws Exception {
    	expect(Keyword.FUNC);
    	parseArgumentList();
    	if (lex_.advance(Punctuator.RETURN_ARROW)) {
    		parseTypeSpecifier();
    	}
    	parseFunctionBody();
		return null;
    }
    
    private Declaration parseDeclarations() throws Exception {
    	switch (lex_.currentTag()) {
    	case CLASS:
    		return parseClassDeclaration();
    	case ENUM:
    		return parseEnumDeclaration();
    	case TYPE:
    		return parseAliasDeclaration();
    	case FUNC:
    		return parseFunctionDeclaration();
    	case CONST:
    		return parseConstantDeclaration();
    	case LET:
    		return parseVariableDeclaration();
    	default:
    		throw new ParsingException(
    				"only declaration statements can be placed in top environment");
    	}
    }
    
    private IfStatement parseIfStatement() throws Exception {
        expect(Keyword.IF);
        Expression cond = parseParenthesisExpression();
        Statement then = parseStatement(), otherwise = null;
        if (lex_.advance(Keyword.ELSE))
            otherwise = parseStatement();
        return astNode_.createIfStmt(cond, then, otherwise);
    }
    
    private WhileStatement parseWhileLoop() throws Exception {
        expect(Keyword.WHILE);
        Expression cond = parseParenthesisExpression();
        Statement body = parseStatement();
        return astNode_.createWhileStmt(cond, body);
    }
    
    private DoWhileStatement parseDoWhileLoop() throws Exception {
        /*
         * Do-while loop is in following form:
         * do 
         *   Statement
         * while (Expression);
         */
        expect(Keyword.DO);
        Statement body = parseStatement();
        expect(Keyword.WHILE);
        Expression cond = parseParenthesisExpression();
        expect(Punctuator.SEMICOLON);
        return astNode_.createDoWhileStmt(cond, body);
    }
    
    private void parseForLoop() throws Exception {
        expect(Keyword.FOR);
    }
    
    private Statement parseStatement() throws Exception {
        switch (lex_.currentTag()) {
        case CONST:
        	return parseConstantDeclaration();
        case LET:
        	return parseVariableDeclaration();
        case DO:
            return parseDoWhileLoop();
        case FOR:
        	return null;
            // return parseForLoop();
        case IF:
            return parseIfStatement();
        case WHILE:
            return parseWhileLoop();
        case LBRACE:
            return parseStatementBlock(Punctuator.RBRACE);
        default:
            return parseExpressionStatement();
        }
    }
    
    private StatementBlock parseStatementBlock(Token endToken) throws Exception {
    	ArrayList<Statement> stmts = new ArrayList<Statement>();
    	while (!lex_.advance(endToken)) {
    		stmts.add(parseStatement());
    	}
    	return astNode_.createStatementBlock(stmts);
    }
    
    private ExpressionStatement parseExpressionStatement() throws Exception {
    	Expression expr = parseExpression();
    	return astNode_.createExpressionStmt(expr);
    }
    
    // Parsing routines about expression
    // We use top down operator precedence method by Pratt
    
    private Expression parseNullDenotation(Token token) {
    	
        return null;
    }
    
    private Expression parseLeftNotation(BinaryOp op, AstNode left) throws Exception {
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
    
    private Expression parseExpression(int rightBindingPower) throws Exception {
        Token token = lex_.advance();
        Expression left = parseNullDenotation(token);
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
    
    private Expression parseExpression() throws Exception {
        return parseExpression(LOWEST_PRECEDENCE);
    }
    
    private void reportParsingError(String message) throws Exception {
        throw new ParsingException(message);
    }
    
}
