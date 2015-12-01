package lexer;

import java.util.ArrayDeque;

import text.Scanner;
import token.*;

public class Lexer {
	
	public Lexer() {
		
	}
	
	private Scanner scan_;
	
	private void scan() {
		Token token = null;
		
		skipWhiteSpace();
		
		switch (scan_.peek()) {
		
		}
	}
	
	private void skipWhiteSpace() {
		while (Character.isWhitespace(scan_.peek())) {
			scan_.ignore();
		}
	}
	
	private void skipSingleLineComment(boolean noPrefix) {
		if (!noPrefix) {
			scan_.match('/');
			scan_.match('/');	
		}
		
		while (!scan_.match('\n')) {
			scan_.ignore();
		}
	}
	
	private void skipMultipleLineComment(boolean noPrefix) {
		if (!noPrefix) {
			scan_.match('/');
			scan_.match('*');	
		}
		
		int cascade = 1;
		
		while (true) {
			if (scan_.match('/') && scan_.peek() == '*') {
				scan_.ignore();
				cascade++;
			} else if (scan_.match('*') && scan_.peek() == '/') {
				scan_.ignore();
				cascade--;
				if (cascade == 0) break;
			} else {
				scan_.ignore();
			}
		}
	}
	
	private Token scanIdentifierOrKeyword() {
		if (!Character.isJavaIdentifierStart(scan_.peek()))
			return null;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(scan_.next());
		
		while (Character.isJavaIdentifierPart(scan_.peek())) {
			sb.append(scan_.next());
		}
		
		String word = sb.toString();
		
		if (Keyword.isKeyword(word))
			return Keyword.getKeywordToken(word);
		
		return new Identifier(word);
	}
	
	private Token scanNumber() {
		scan_.match('0');
		
		String literal;
		
		switch (scan_.peek()) {
		case 'x':
			scan_.ignore();
			literal = scanHexidecimalInteger();
			break;
		case 'o':
			scan_.ignore();
			literal = scanOctalInteger();
			break;
		case 'b':
			scan_.ignore();
			literal = scanBinaryInteger();
			break;
		default:
			if (Character.isDigit(scan_.peek()))
				literal = scanDecimalInteger();
			else
				literal = "0";
			
			char c = scan_.peek();
			if (c == '.' || c == 'e' || c == 'E' || c == 'f')
				return scanFloatingPointNumber(literal);
			break;
		}
	}
	
	private String scanHexidecimalInteger() {
		return null;
	}
	
	private String scanOctalInteger() {
		return null;
	}
	
	private String scanBinaryInteger() {
		return null;
	}
	
	private String scanDecimalInteger() {
		return null;
	}
	
	private Token scanFloatingPointNumber(String intPart) {
		return null;
	}
}
