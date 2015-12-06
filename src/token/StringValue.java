package token;

public class StringValue extends Value {

	public StringValue(String text) {
		super(Tag.STRING_LITERAL, text.length() > 10 ? text.substring(0, 9) : text);
		text_ = text;
		excerpt_ = super.getLiteral();
	}
	
	private String excerpt_;
	private String text_;

	@Override
	public Object getNativeObject() {
		return text_;
	}
	
	@Override
	public String toString() {
		return String.format("string literal [value=\"%s...\"", excerpt_);
	}

}
