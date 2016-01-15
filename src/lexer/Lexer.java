package lexer;

import text.Scanner;
import utils.LexicalError;
import utils.Position;

public class Lexer {

	public Lexer(Scanner scan) {
		scan_ = scan;
		row_ = 1;
		column_ = 1;
		tokenFactory_ = new TokenFactory(this);
		ignore();
	}

	private Scanner scan_;
	
	public Token advance() throws LexicalError {
		return scan();
	}
	
	// Token factory
	
	private TokenFactory tokenFactory_;

	// Position records

	private int row_;
	private int column_;

	public Position position() {
		return new Position(row_, column_);
	}

	// Helper functions of stream operations.

	private char peek;

	private char next() {
		char save = peek;
		ignore();
		return save;
	}

	private boolean match(char ch) {
		if (peek == ch) {
			ignore();
			return true;
		}
		return false;
	}

	private void expect(char ch) throws LexicalError {
		if (peek == ch)
			ignore();
		else
			throw new LexicalError(position(),
					String.format("expect '%c' instead of '%c'", ch, peek));
	}

	private void ignore() {
		peek = scan_.advance();
		if (peek == '\n') {
			row_++;
			column_ = 0;
		}
		column_++;
	}

	private Token select(Tag tokenTag) {
		return tokenFactory_.get(tokenTag);
	}
	
	private Token select(char cond, Tag then, Tag otherwise) {
		return tokenFactory_.get(match(cond) ? then : otherwise);
	}

	private boolean isBinDigit() {
		return peek == '0' || peek == '1';
	}

	private boolean isOctDigit() {
		return '0' <= peek && peek <= '7';
	}

	private boolean isDigit() {
		return '0' <= peek && peek <= '9';
	}

	private boolean isHexDigit() {
		return isDigit() || ('A' <= peek && peek <= 'F') ||
				('a' <= peek && peek <= 'f');
	}

	private boolean isIdentifierStart() {
		return Character.isJavaIdentifierStart(peek);
	}

	private boolean isIdentifierPart() {
		return Character.isJavaIdentifierPart(peek);
	}

	private String scanBinDigits() {
		StringBuilder sb = new StringBuilder();
		while (isBinDigit())
			sb.append(next());
		return sb.toString();
	}

	private String scanOctDigits() {
		StringBuilder sb = new StringBuilder();
		while (isOctDigit())
			sb.append(next());
		return sb.toString();
	}

	private String scanDigits() throws LexicalError {
		StringBuilder sb = new StringBuilder();
		while (isDigit())
			sb.append(next());

		String str = sb.toString();
		if (str.equals(""))
			throw new LexicalError(position(), "expect decimal digits");
		return str;
	}

	private String scanDigits(char firstChar) {
		StringBuilder sb = new StringBuilder();
		sb.append(firstChar);
		while (isDigit())
			sb.append(next());
		return sb.toString();
	}

	private String scanHexDigits() {
		StringBuilder sb = new StringBuilder();
		while (isHexDigit())
			sb.append(next());
		return sb.toString();
	}

	private String scanIdentifier(char firstChar) {
		StringBuilder sb = new StringBuilder();
		sb.append(firstChar);
		while (isIdentifierPart())
			sb.append(next());
		return sb.toString();
	}

	// Scan routines

	private Token scan() throws LexicalError {
		char save;
		while (true) {
			switch (save = next()) {
			// End of file
			case Scanner.EOF:
				return select(Tag.EOS);

			// White spaces
			case '\n':
			case '\t':
			case '\r':
			case ' ':
				break;

			// Single character operators
			case '(':
				return select(Tag.LPAREN);
			case ')':
				return select(Tag.RPAREN);
			case '[':
				return select(Tag.LBRACK);
			case ']':
				return select(Tag.RBRACK);
			case '{':
				return select(Tag.LBRACE);
			case '}':
				return select(Tag.RBRACE);
			case ':':
				return select(Tag.COLON);
			case ';':
				return select(Tag.SEMICOLON);
			case '?':
				return select(Tag.CONDITIONAL);
			case '~':
				return select(Tag.BIT_NOT);
			case ',':
				return select(Tag.COMMA);

			case '.': // . ... number-start-with-period
				if (match('.')) {
					if (match('.'))
						return select(Tag.ELLIPSIS);
					else
						throw new LexicalError(position(), 
								"expect a period instead of " + peek);
				} else if (isDigit())
					return scanNumber(next());
				else
					return select(Tag.PERIOD);
				
			case '!': // ! !=
				return select('=', Tag.NE, Tag.NOT);
				
			case '=': // = ==
				return select('=', Tag.EQ, Tag.ASSIGN);
				
			case '<': // < <= << <<=
				return match('<')
						? select('=', Tag.ASSIGN_SHL, Tag.SHL)
						: select('=', Tag.LTE, Tag.LT);
				
			case '>': // > >= >> >>= >>> >>>=
				if (match('>'))
					return match('>')
							? select('=', Tag.ASSIGN_SAR, Tag.SAR)
							: select('=', Tag.ASSIGN_SHR, Tag.SHR);
				else
					return select('=', Tag.GTE, Tag.GT);
				
			case '+': // + ++ +=
				return match('+')
						? select(Tag.INC)
						: select('=', Tag.ASSIGN_ADD, Tag.ADD);
				
			case '-': // - -- -=
				return match('-')
						? select(Tag.DEC)
						: select('=', Tag.ASSIGN_SUB, Tag.SUB);
				
			case '*': // * *=
				return select('=', Tag.ASSIGN_MUL, Tag.MUL);
			
			case '/': // / /= // /*
				if (match('='))
					return select(Tag.ASSIGN_DIV);
				else if (match('/'))
					skipSingleLineComment();
				else if (match('*'))
					skipMultipleLineComment();
				else
					return select(Tag.DIV);
				break;
				
			case '%': // % %=
				return select('=', Tag.ASSIGN_MOD, Tag.MOD);

			// Literals
			case '0':
				if (match('x'))
					return scanInteger(scanHexDigits(), 16);
				if (match('o'))
					return scanInteger(scanOctDigits(), 8);
				if (match('b'))
					return scanInteger(scanBinDigits(), 2);
				return scanIntegerOrNumber('0');
			case '"':
				return scanStringLiteral();
			case '\'':
				return scanCharLiteral();
			
			default:
				if (Character.isJavaIdentifierStart(save))
					return scanIdentifierOrKeyword(save);
				if (Character.isDigit(save))
					return scanIntegerOrNumber(save);
				throw new LexicalError(position(),
						String.format("unrecognized character '%c'", save));
			}
		}
	}

	// Comments

	private void skipSingleLineComment() {
		while (peek != '\n' && peek != Scanner.EOF) ignore();
		if (peek == '\n') ignore();
	}

	private void skipMultipleLineComment() throws LexicalError {
		int cascade = 1;
		while (cascade > 0) {
			if (peek == '/') {
				ignore();
				if (match('*')) cascade++;
			} else if (peek == '*') {
				ignore();
				if (match('/')) cascade--;
			} else if (peek == Scanner.EOF) {
				throw new LexicalError(position(), 
						String.format("unexpected EOF in multi-line comment"));
			} else {
				ignore();
			}
		}
	}

	// Integer or number literal
	
	private Token scanInteger(String literal, int base) throws LexicalError {
		try {
			return tokenFactory_.literal(Integer.parseInt(literal, base));
		} catch (NumberFormatException e) {
			throw new LexicalError(position(), e.getMessage());
		}
	}

	private Token scanIntegerOrNumber(char firstChar) throws LexicalError {
		String integer = scanDigits(firstChar), fraction = "", exponent = "";

		if (match('.'))
			fraction = scanDigits();

		if (match('e') || match('E')) {
			exponent = "E";
			if (peek == '+' || peek == '-')
				exponent += next() + scanDigits();
			else
				exponent += scanDigits();
		}

		if (fraction.equals("") && exponent.equals(""))
			return tokenFactory_.literal(Integer.parseInt(integer));
		return tokenFactory_.literal(Double.parseDouble(String.format("%s.%s%s", 
				integer, fraction, exponent)));
	}
	
	private Token scanNumber(char firstChar) throws LexicalError {		
		StringBuilder sb = new StringBuilder();
		sb.append(scanDigits(firstChar));
		if (match('e') || match('E')) {
			sb.append('E');
			if (peek == '+' || peek == '-')
				sb.append(next() + scanDigits());
			else
				sb.append(scanDigits());
		}
		return tokenFactory_.literal(Double.parseDouble(sb.toString()));
	}

	// String literal

	private Token scanStringLiteral() throws LexicalError {
		StringBuilder sb = new StringBuilder();
		while (!match('"')) {
			if (peek == '\\')
				scanStringEscape(sb);
			else
				sb.append(next());
		}
		return tokenFactory_.literal(sb.toString());
	}

	private void scanStringEscape(StringBuilder sb) throws LexicalError {
		// pre-condition: peek == '\\'
		expect('\\');
		char save = next();
		int codepoint;
		switch (save) {
		case 'n':
			sb.append('\n');
			break;
		case 'r':
			sb.append('\r');
			break;
		case 't':
			sb.append('\t');
			break;
		case '"':
			sb.append('"');
			break;
		case '\\':
			sb.append('\\');
			break;
		case 'u':
			// Scan Unicode string escape.
			// Range: [0,0x10FFFF]
			// Examples: \uC0DE \uDEAD \u10FFFF
			codepoint = Integer.parseInt(scanHexDigits(), 16);
			if (codepoint > 0xFFFF) {
				sb.append(Character.highSurrogate(codepoint));
				sb.append(Character.lowSurrogate(codepoint));
			} else {
				sb.append((char) codepoint);
			}
			break;
		default:
			throw new LexicalError(position(), String.format(
					"unrecognized character '%c' in character escape", save));
		}
	}

	// Character literal

	private Token scanCharLiteral() throws LexicalError {
		char ch = '\0';

		if (peek == '\\') // character escape
			ch = scanCharEscape();
		else if (peek == '\n' || peek == '\'') // illegal characters
			throw new LexicalError(position(), 
					String.format("invaild character literal"));
		else
			ch = next();

		expect('\'');
		return tokenFactory_.literal(ch);
	}

	private char scanCharEscape() throws LexicalError {
		// pre-condition: peek == '\\'
		int codepoint;
		expect('\\');
		char save = next();
		switch (save) {
		case 'n':
			return '\n';
		case 'r':
			return '\r';
		case 't':
			return '\t';
		case '\'':
			return '\'';
		case '\\':
			return '\\';
		case 'u':
			// Scan Unicode character escape.
			// Range: [0,0xFFFF]
			// Examples: \uC0DE \uDEAD
			codepoint = Integer.parseInt(scanHexDigits(), 16);
			if (codepoint > 0xFFFF)
				throw new LexicalError(position(), String.format(
						"character escapee exceeds the limit of UTF16"));
			else
				return (char) codepoint;
		default:
			throw new LexicalError(position(), String.format(
					"unrecognized character '%c' in character escape", save));
		}
	}

	private Token scanIdentifierOrKeyword(char firstChar) {
		String id = scanIdentifier(firstChar);
//		System.out.println(TokenFactory.isKeyword("true"));
//		System.out.println(TokenFactory.isKeyword(id.toString()));
//		System.out.println("id: \"" + id + '"');
//		System.out.println("id == \"true\":" + (id.equals("true")));
		return TokenFactory.isKeyword(id) ?
				tokenFactory_.keyword(id) : tokenFactory_.identifier(id);
	}

}