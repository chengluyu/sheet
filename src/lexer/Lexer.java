package lexer;

import text.Scanner;
import text.RawStream;
import token.*;

public class Lexer {
	
	public Lexer(RawStream stream) {
		initialize(stream);
	}
	
	public boolean advance(Token tok) {
		if (current_ == tok) {
			advance();
			return true;
		}
		return false;
	}
	
	public Token current() {
		return current_;
	}
	
	public Token advance() {
		Token save = current_;
		scan();
		return save;
	}
	
	private Scanner scan_;
	private int line_, column_;
	private boolean eos_;
	private Token current_;
	
	private void initialize(RawStream stream) {
		scan_ = new Scanner(stream);
		line_ = 1;
		column_ = 1;
		eos_ = false;
		current_ = null;
		scan();
	}
	
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
						token = Punctuator.ELLIPSIS;
					} else {
						token = new Illegal("Expected a period instead of " + scan_.peek());
					}
				} else if (Character.isDigit(scan_.peek()))
					token = scanFloatingPointNumber("0");
				else
					token = Punctuator.PERIOD;
				break;
				
			// Single-character unary operators
			case '~':
				token = UnaryOp.BIT_NOT;
				break;
				
			case '!': // ! !=
				scan_.ignore();
				if (scan_.match('='))
					token = ComparisionOp.NE;
				else
					token = UnaryOp.NOT;
				break;
				
			case '=': // = => ==
				scan_.ignore();
				if (scan_.match('='))
					token = ComparisionOp.EQ;
				else if (scan_.match('>'))
					token = Punctuator.ARROW;
				else
					token = AssignmentOp.ASSIGN;
				break;
				
			case '<': // < <= << <<=
				scan_.ignore();
				if (scan_.match('<')) {
					if (scan_.peek() == '=')
						token = AssignmentOp.SHL;
					else
						token = BinaryOp.SHL;
				} else if (scan_.match('='))
					token = ComparisionOp.LTE;
				else
					token = ComparisionOp.LT;
				break;
				
			case '>': // > >= >> >>=
				scan_.ignore();
				if (scan_.match('>')) {
					if (scan_.peek() == '=')
						token = AssignmentOp.SHR;
					else
						token = BinaryOp.SHR;
				} else if (scan_.match('='))
					token = ComparisionOp.GTE;
				else
					token = ComparisionOp.GT;
				break;
				
			case '+': // + ++ +=
				scan_.ignore();
				if (scan_.match('+'))
					token = Punctuator.INC;
				else if (scan_.match('='))
					token = AssignmentOp.ADD;
				else
					token = BinaryOp.ADD;
				break;
				
			case '-': // - -- -=
				scan_.ignore();
				if (scan_.match('-'))
					token = Punctuator.DEC;
				else if (scan_.match('='))
					token = AssignmentOp.SUB;
				else
					token = BinaryOp.SUB;
				break;
				
			case '*': // * *=
				scan_.ignore();
				if (scan_.match('='))
					token = AssignmentOp.MUL;
				else
					token = BinaryOp.MUL;
				break;
			
			case '/': // / /= // /*
				scan_.ignore();
				if (scan_.match('='))
					token = AssignmentOp.MUL;
				else if (scan_.match('/')) {
					skipSingleLineComment(true);
					token = Token.SINGLE_LINE_COMMENT;
				} else if (scan_.match('*')) {
					skipMultipleLineComment(true);
					token = Token.MULTIPLE_LINE_COMMENT;
				} else
					token = BinaryOp.DIV;
				break;
				
			case '%': // % %=
				scan_.ignore();
				if (scan_.match('='))
					token = AssignmentOp.MOD;
				else
					token = BinaryOp.MOD;
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
			
			// Literals
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
		} while (token == Token.WHITESPACE ||
				token == Token.SINGLE_LINE_COMMENT ||
				token == Token.MULTIPLE_LINE_COMMENT);
		
		current_ = token;
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
