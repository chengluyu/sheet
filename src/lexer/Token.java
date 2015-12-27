package lexer;

import utils.Position;

public final class Token {

	public Token(Position pos, Tag tokenTag) {
		pos_ = pos;
		tag_ = tokenTag;
		data_ = null;
	}

	public Token(Position pos, Tag tokenTag, Object data) {
		pos_ = pos;
		tag_ = tokenTag;
		data_ = data;
	}

	private final Position pos_;
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
	
	public Position position() {
		return pos_;
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

}