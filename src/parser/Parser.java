package parser;

import java.util.ArrayList;

import ast.*;
import lexer.Lexer;
import token.*;
import utils.Twin;

public class Parser {
	
	public Parser(Lexer lex) {
		lex_ = lex;
	}
	
	private Lexer lex_;
	
	// Some common helper functions
	private Identifier expectIdentifier(String errorMessage) {
		if (!lex_.current().isIdentifier()) {
			// report error: expect a export name
			return null;
		} else {
			return (Identifier) lex_.advance();
		}
	}
	
	private void expect(Token wish) {
		if (lex_.current() != wish) {
			// report error: expect wish.toString() instead of lex_.current().toString()
		}
	}

	// Parsing routines about module
	private void parseModuleDeclaration() {
		if (lex_.advance(Keyword.MODULE)) {
			Identifier id = expectIdentifier("expect a module name");
			// set current module name to id
		} else {
			// this is an anonymous module
		}
	}
	
	private Twin<Identifier> parseImportName(Identifier preparsed) {
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
	 */
	private void parseImportNameList(Identifier firstId) {
		Twin<Identifier> idPair = parseImportName(firstId);
		
		while (lex_.advance(BinaryOp.COMMA)) {
			idPair = parseImportName(null);
			// do something, for example, collecting idPair
		}
	}
	
	private void parseImportClause() {
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
		 * 2. (int, char, float) // tuple type
		 * 3. 
		 */
		return null;
	}
	
	private ArrayList<Identifier> parseIdentifierList() {
		ArrayList<Identifier> idList = new ArrayList<Identifier>();
		
		if (lex_.current().isIdentifier()) {
			idList.add((Identifier) lex_.advance());
			
			while (lex_.advance(BinaryOp.COMMA)) {
				idList.add(expectIdentifier("expect a identifier after a comma"));
			}
		}
		
		return idList;
	}
	
	private void parseParenthesisExpression() {
		expect(Punctuator.LPAREN);
		parseExpression();
		expect(Punctuator.RPAREN);
	}
	
	private EnumType parseEnumDeclaration() {
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
	
	private void parseClassDeclaration() {
		expect(Keyword.CLASS);
	}
	
	private void parseAliasDeclaration() {
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
	
	private void parseIfStatement() {
		expect(Keyword.IF);
		parseParenthesisExpression();
		parseStatement();
		if (lex_.advance(Keyword.ELSE))
			parseStatement();
	}
	
	private void parseWhileLoop() {
		expect(Keyword.WHILE);
		parseParenthesisExpression();
		parseStatement();
	}
	
	private void parseDoWhileLoop() {
		expect(Keyword.DO);
		parseStatement();
		expect(Keyword.WHILE);
		parseParenthesisExpression();
		expect(Punctuator.SEMICOLON);
	}
	
	private void parseForLoop() {
		expect(Keyword.FOR);
	}
	
	private void parseStatement() {
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
			
		default:
			
		}
	}
	
	// Parsing routines about expression
	// We use top down operator precedence method by Pratt
	
	private AstNode parseNullDenotation(Token token) {
		return null;
	}
	
	private AstNode parseLeftNotation(BinaryOp op, AstNode left) {
		AstNode right = parseExpression(op.getPrecedence());
		// TODO: do something with left and right
		
		if (op.isBinaryOp()) {
			
		}
		return null;
	}
	
	private AstNode parseTernaryCondition(Operator op, AstNode preparsed) {
		AstNode cond = preparsed,
				then = parseExpression(0),
				otherwise = null;
		expect(Punctuator.COLON);
		otherwise = parseExpression(0);
		// TODO: do something with cond, then and otherwise
		return null;
	}
	
	private AstNode parseExpression(int rightBindingPower) {
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
	
	private void parseExpression() {
		parseExpression(0);
	}
	
	private void reportError(String info) {
		
	}
	
}
