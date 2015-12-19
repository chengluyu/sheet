package lexer;

import java.util.HashMap;

public final class Token {

	public Token(Tag tokenTag) {
		tag_ = tokenTag;
		data_ = null;
	}

	public Token(Tag tokenTag, Object data) {
		tag_ = tokenTag;
		data_ = data;
	}

	private final Tag tag_;
	private final Object data_;

	// Properties

	public Tag tag() {
		return tag_;
	}

	public int lbp() {
		return tag_.lbp();
	}

	public String literal() {
		if (data_ == null)
			return tag_.literal();
		return String.format("%s (%s)", tag_.literal(), data_.toString());
	}

	public Object data() {
		return data_;
	}

	public boolean isEndOfSource() {
		return tag_.type() == TokenType.EOS;
	}

	public boolean isIdentifier() {
		return tag_.type() == TokenType.IDENTIFIER;
	}

	public boolean isKeyword() {
		return tag_.type() == TokenType.KEYWORD;
	}

	public boolean isLiteral() {
		return tag_.type() == TokenType.LITERAL;
	}

	public boolean isOperator() {
		return tag_.type() == TokenType.OPERATOR;
	}

	// Keywords

	private static final HashMap<String, Token> keywordMap_ = new HashMap<String, Token>();
	private static final HashMap<Tag, Token> tokenObjects_ = new HashMap<Tag, Token>();

	public static boolean isKeyword(String str) {
		return keywordMap_.containsKey(str);
	}

	public static Token getKeywordToken(String str) {
		return keywordMap_.get(str);
	}

	static {
		for (Tag tag : Tag.values()) {
			if (tag.type() != TokenType.LITERAL &&
				tag.type() != TokenType.IDENTIFIER) {

				Token token = new Token(tag);
				tokenObjects_.put(tag, token);
				if (tag.type() == TokenType.KEYWORD)
					keywordMap_.put(tag.literal(), token);
			}
		}
	}

	// Obtain token

	public static Token get(Tag tag) {
		return tokenObjects_.get(tag);
	}

	public static Token identifier(String id) {
		return new Token(Tag.IDENTIFIER, id);
	}

	public static Token literal(String str) {
		return new Token(Tag.STRING_LITERAL, str);
	}

	public static Token literal(int value) {
		return new Token(Tag.INTEGER, new Integer(value));
	}

	public static Token literal(double value) {
		return new Token(Tag.NUMBER, new Double(value));
	}

	public static Token literal(char ch) {
		return new Token(Tag.CHAR_LITERAL, new Character(ch));
	}

}