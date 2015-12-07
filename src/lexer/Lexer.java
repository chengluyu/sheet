package lexer;

import text.Scanner;
import text.RawStream;
import token.*;
import utils.LexicalException;

public class Lexer {
	
	public Lexer(RawStream stream) throws LexicalException {
		initialize(stream);
	}
	
	public boolean advance(Token tok) throws LexicalException {
		if (current_ == tok) {
			advance();
			return true;
		}
		return false;
	}
	
	public Token current() {
		return current_;
	}
	
	public Token advance() throws LexicalException {
		Token save = current_;
		scan();
		return save;
	}
	
	private Scanner scan_;
	private boolean eos_;
	private Token current_;
	
	private void initialize(RawStream stream) throws LexicalException {
		scan_ = new Scanner(stream);
		eos_ = false;
		current_ = null;
		scan();
	}
	
	private void scan() throws LexicalException {
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
				scan_.ignore();
				token = Punctuator.LPAREN;
				break;
			case ')':
				scan_.ignore();
				token = Punctuator.RPAREN;
				break;
			case '[':
				scan_.ignore();
				token = Punctuator.LBRACK;
				break;
			case ']':
				scan_.ignore();
				token = Punctuator.RBRACK;
				break;
			case '{':
				scan_.ignore();
				token = Punctuator.LBRACE;
				break;
			case '}':
				scan_.ignore();
				token = Punctuator.RBRACE;
				break;
			case ':':
				scan_.ignore();
				token = Punctuator.COLON;
				break;
			case ';':
				scan_.ignore();
				token = Punctuator.SEMICOLON;
				break;
			case '?':
				scan_.ignore();
				token = Punctuator.CONDITIONAL;
				break;
			case '.': // . ... number-start-with-period
				scan_.ignore();
				if (scan_.match('.')) {
					if (scan_.peek() == '.') {
						scan_.ignore();
						token = Punctuator.ELLIPSIS;
					} else {
						throw new LexicalException("Expected a period instead of " + scan_.peek());
					}
				} else if (Character.isDigit(scan_.peek()))
					token = scanFloatingPointNumber("0");
				else
					token = Punctuator.PERIOD;
				break;
				
			// Single-character unary operators
			case '~':
				scan_.ignore();
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
			case ' ':
			case '\t':
			case '\r':
				scan_.ignore();
				token = Token.WHITESPACE;
				break;
				
			// String and character literals
			case '"':
				scan_.ignore();
				token = scanStringLiteral();
				break;
			case '\'':
				scan_.ignore();
				token = scanCharLiteral();
				break;
			
			// Literals
			default:
				if (Character.isJavaIdentifierStart(scan_.peek())) {
					token = scanIdentifierOrKeyword();
				} else if (Character.isDigit(scan_.peek())) {
					token = scanNumber();
				} else {
					throw new LexicalException("unrecognized character: " + scan_.peek());
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
	
	private void skipWhiteSpace() {
		while (Character.isWhitespace(scan_.peek()))
			scan_.ignore();
	}
	
	private int scanCharEscapeeCodePoint(int maxValue) throws LexicalException {
		int codepoint = Integer.parseInt(scanHexidecimalInteger());
		if (codepoint > maxValue) {
			throw new LexicalException("invaild Unicode code point");
		}
		return codepoint;
	}
	
	private StringValue scanStringLiteral() throws LexicalException {
		StringBuilder sb = new StringBuilder();
		
		do {
			if (scan_.match('\\')) {
				switch (scan_.peek()) {
				case '\n':
					scan_.ignore();
					skipWhiteSpace();
					break;
				case 'n':
					scan_.ignore();
					sb.append('\n');
					break;
				case 't':
					scan_.ignore();
					sb.append('\t');
					break;
				case 'r':
					scan_.ignore();
					sb.append('\r');
					break;
				case 'u':
					scan_.ignore();
					if (scan_.match('{')) {
						int codepoint = scanCharEscapeeCodePoint(0x10FFFF);
						sb.append(Character.lowSurrogate(codepoint));
						sb.append(Character.highSurrogate(codepoint));
						if (!scan_.match('}'))
							throw new LexicalException("missing right brace in an Unicode escapee");
					} else {
						throw new LexicalException("invaild Unicode escapee character");
					}
				default:
					throw new LexicalException(String.format(
							"unknown escapee character '%c'", scan_.next()));
				}
			} else {
				sb.append(scan_.next());
			}
		} while (scan_.match('"'));
		
		return new StringValue(sb.toString());
	}
	
	private CharValue scanCharLiteral() throws LexicalException {
		char ch;
		
		if (scan_.match('\\')) {
			switch (scan_.peek()) {
			case 'n':
				scan_.ignore();
				ch = '\n';
				break;
			case 't':
				scan_.ignore();
				ch = '\t';
				break;
			case 'r':
				scan_.ignore();
				ch = '\r';
				break;
			case 'x':
				scan_.ignore();
				int codepoint = scanCharEscapeeCodePoint(0xFFFF);
				ch = (char) codepoint;
				break;
			default:
				throw new LexicalException(String.format(
						"unknown escapee character '%c'", scan_.next()));
			}
		} else if (scan_.match('\n'))
			throw new LexicalException("invaild char constant");
		else
			ch = scan_.next();
		
		if (!scan_.match('\''))
			throw new LexicalException("invaild character literal");
		
		return new CharValue(ch); 
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
	
	private Token scanNumber() throws LexicalException {
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
				literal = scanDecimalInteger(true);
			else
				literal = "0";
			
			char c = scan_.peek();
			if (c == '.' || c == 'e' || c == 'E' || c == 'f')
				return scanFloatingPointNumber(literal);
			break;
		}
		IntegerValue.Type type = scanIntegerType();
		
		return new IntegerValue(literal, type);
	}
	
	private String scanHexidecimalInteger() {
		StringBuilder sb = new StringBuilder();
		if (isHexidecimalDigit(scan_.peek())) {
			do {
				sb.append(scan_.next());
			} while (isHexidecimalDigit(scan_.peek()));
		}
		return sb.toString();
	}
	
	private String scanOctalInteger() {
		StringBuilder sb = new StringBuilder();
		if (isOctalDigit(scan_.peek())) {
			do {
				sb.append(scan_.next());
			} while (isOctalDigit(scan_.peek()));
		}
		return sb.toString();
	}
	
	private String scanBinaryInteger() {
		StringBuilder sb = new StringBuilder();
		if (isBinaryDigit(scan_.peek())) {
			do {
				sb.append(scan_.next());
			} while (isBinaryDigit(scan_.peek()));
		}
		return sb.toString();
	}
	
	private IntegerValue.Type scanIntegerType() throws LexicalException {
		// i8, i16, i32, i64, u8, u16, u32, u64
		if (scan_.peek() == 'i') {
			int size = Integer.parseInt(scanDecimalInteger(false));
			switch (size) {
			case 8: return IntegerValue.Type.int8;
			case 16: return IntegerValue.Type.int16;
			case 32: return IntegerValue.Type.int32;
			case 64: return IntegerValue.Type.int64;
			default: throw new LexicalException("invaild integer suffix");
			}
		} else if (scan_.peek() == 'u') {
			int size = Integer.parseInt(scanDecimalInteger(false));
			switch (size) {
			case 8: return IntegerValue.Type.uint8;
			case 16: return IntegerValue.Type.uint16;
			case 32: return IntegerValue.Type.uint32;
			case 64: return IntegerValue.Type.uint64;
			default: throw new LexicalException("invaild integer suffix");
			}
		} else return IntegerValue.Type.int32; // default integer type
	}
	
	private NumberValue.Type scanNumberType() throws LexicalException {
		// i8, i16, i32, i64, u8, u16, u32, u64
		if (scan_.peek() == 'f') {
			int size = Integer.parseInt(scanDecimalInteger(false));
			if (size == 32)
				return NumberValue.Type.float32;
			else if (size == 64)
				return NumberValue.Type.float64;
			else
				throw new LexicalException("invaild number suffix");
		} else return NumberValue.Type.float64; // default number type;
	}
	
	private String scanDecimalInteger(boolean nullable) throws LexicalException {
		StringBuilder sb = new StringBuilder();
		if (isDecimalDigit(scan_.peek())) {
			do {
				sb.append(scan_.next());
			} while (isDecimalDigit(scan_.peek()));
		} else if (!nullable) {
			throw new LexicalException("expect a dicimal integer");
		}
		return sb.toString();
	}
	
	private NumberValue scanFloatingPointNumber(String intPart) throws LexicalException {
		// fraction part
		StringBuilder sb = new StringBuilder();
		if (scan_.match('.')) {
			sb.append('.');
			sb.append(scanDecimalInteger(false));
		}
		// exponent part
		if (scan_.match('e') || scan_.match('E')) {
			sb.append('E');
			if (scan_.peek() == '+' || scan_.peek() == '-')
				sb.append(scan_.next());
			sb.append(scanDecimalInteger(false));
		}
		// number suffix
		NumberValue.Type type = scanNumberType();
		return new NumberValue(sb.toString(), type);
	}
	
	// some helper functions
	
	private static boolean isDecimalDigit(char ch) {
		return '0' <= ch && ch <= '9';
	}
	
	private static boolean isBinaryDigit(char ch) {
		return ch == '0' || ch == '1';
	}
	
	private static boolean isOctalDigit(char ch) {
		return '0' <= ch && ch <= '7';
	}
	
	private static boolean isHexidecimalDigit(char ch) {
		return ('0' <= ch && ch <= '9') ||
				('A' <= ch && ch <= 'F') ||
				('a' <= ch && ch <= 'f');
	}
	
}
