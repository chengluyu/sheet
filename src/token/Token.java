package token;

public class Token {
	
	protected Token(Tag tag, String literal) {
		tag_ = tag;
		literal_ = literal;
	}
	
	public final String getLiteral() {
		return literal_;
	}
	
	public boolean isPunctuator() {
		return false;
	}
	
	public boolean isAssignmentOp() {
		return false;
	}
	
	public boolean isBinaryOp() {
		return false;
	}
	
	public boolean isCompareOp() {
		return false;
	}
	
	public boolean isUnaryOp() {
		return false;
	}
	
	public boolean isOperator() {
		return false;
	}
	
	public boolean isIdentifier() {
		return false;
	}
	
	public Tag getTag() {
		return tag_;
	}
	
	
	@Override
	public String toString() {
		return literal_;
	}
	
	private final Tag tag_;
	private final String literal_;
	
	public static final Token EOS = new Token(Tag.EOS, "end of source");
	public static final Token WHITESPACE = new Token(Tag.WHITESPACE, "whitespace");
	public static final Token SINGLE_LINE_COMMENT = new Token(Tag.SINGLE_LINE_COMMENT, "single line comment");
	public static final Token MULTIPLE_LINE_COMMENT = new Token(Tag.MULTIPLE_LINE_COMMENT, "multiple line comment");
	
}
