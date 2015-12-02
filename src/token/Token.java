package token;

public class Token {
	
	protected Token(Tag tag, String literal) {
		tag_ = tag;
		literal_ = literal;
	}
	
	public Tag getTag() {
		return tag_;
	}
	
	public String getLiteral() {
		return literal_;
	}
	
	@Override
	public String toString() {
		return literal_;
	}
	
	private final Tag tag_;
	private final String literal_;
	
	public static final Token EOS = new Token(Tag.EOS, "end of source");
	public static final Token WHITESPACE = new Token(Tag.WHITESPACE, "whitespace");
	
}
