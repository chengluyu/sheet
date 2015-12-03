package parser;

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

	// Parsing routines
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
	
	private void parseStatement() {
		
	}
	
	private void parseExpression() {
		
	}
	
	private void reportError(String info) {
		
	}
	
}
