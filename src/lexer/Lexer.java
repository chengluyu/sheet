package lexer;

import text.Scanner;
import token.*;

public class Lexer {
	
	public Lexer() {
		
	}
	
	private Scanner scan_;
	private int line_, column_;
	private boolean eos_;
	
	private void scan() {
		if (eos_) return;
		
		Token token = null;
		
		do {
			switch (scan_.peek()) {
			
			// End of source
			case Scanner.EOF:
				token = Token.EOS;
				eos_ = true;
				break;
				
			// Single-character punctuators
			case '(':
				token = Punctuator.LPAREN;
				break;
			case ')':
				token = Punctuator.RPAREN;
				break;
			case '[':
				token = Punctuator.LBRACK;
				break;
			case ']':
				token = Punctuator.RBRACK;
				break;
			case '{':
				token = Punctuator.LBRACE;
				break;
			case '}':
				token = Punctuator.RBRACE;
				break;
			case ':':
				token = Punctuator.COLON;
				break;
			case ';':
				token = Punctuator.SEMICOLON;
				break;
			case '?':
				token = Punctuator.CONDITIONAL;
				break;
			case '.': // . ... number-start-with-period
				scan_.ignore();
				if (scan_.match('.')) {
					if (scan_.peek() == '.') {
						scan_.ignore();
					}
				}
				token = Punctuator.PERIOD;
				token = Punctuator.ELLIPSIS;
				break;
			case '+':
				token = Punctuator.INC;
				break;
			case '-':
				token = Punctuator.DEC;
				break;
			case '=':
				token = Punctuator.ARROW;
				break;
				
			// Single-character unary operators
			case '~':
				token = UnaryOp.BIT_NOT;
				break;
			
			// Whitespace
			case '\n':
				column_ = 0;
				line_++;
			case ' ':
			case '\t':
			case '\r':
				column_++;
				scan_.ignore();
				token = Token.WHITESPACE;
				break;
			
			// literals
			default:
				if (Character.isJavaIdentifierStart(scan_.peek())) {
					token = scanIdentifierOrKeyword();
				} else if (Character.isDigit(scan_.peek())) {
					token = scanNumber();
				} else {
					token = new Illegal("Unrecognized character: " + scan_.peek());
				}
				break;
			}
		} while (token == Token.WHITESPACE);
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
		
		return null; // To make compiler happy
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
