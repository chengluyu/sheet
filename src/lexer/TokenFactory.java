package lexer;

import java.util.HashMap;

public class TokenFactory {

	public TokenFactory(Lexer lexer) {
		lexer_ = lexer;
	}
	
	private final Lexer lexer_;
	
	public Token get(Tag tag) {
		return new Token(lexer_.position(), tag);
	}

	public Token identifier(String id) {
		return new Token(lexer_.position(), Tag.IDENTIFIER, id);
	}

	public Token literal(String str) {
		return new Token(lexer_.position(), Tag.STRING_LITERAL, str);
	}

	public Token literal(int value) {
		return new Token(lexer_.position(), Tag.INTEGER, new Integer(value));
	}

	public Token literal(double value) {
		return new Token(lexer_.position(), Tag.NUMBER, new Double(value));
	}

	public Token literal(char ch) {
		return new Token(lexer_.position(), Tag.CHAR_LITERAL, new Character(ch));
	}
	
	public Token keyword(String str) {
		return new Token(lexer_.position(), keywordMap_.get(str));
	}
	
	// Keywords
	
	private static final HashMap<String, Tag> keywordMap_ = new HashMap<String, Tag>();

	public static boolean isKeyword(String str) {
		return keywordMap_.containsKey(str);
	}

	static {
		for (Tag tag : Tag.values()) {
			if (tag.type() == TokenType.KEYWORD) {
				keywordMap_.put(tag.literal(), tag);
//				System.out.println("put " + tag.name() + ' ' + tag.literal());
			}
		}
	}

}
