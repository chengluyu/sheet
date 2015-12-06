package token;

public class BoolValue extends Value {

	private BoolValue(Tag tag) {
		super(tag, tag == Tag.TRUE_LITERAL ? "true" : "false");
		bool_ = new Boolean(tag == Tag.TRUE_LITERAL);
	}
	
	private Boolean bool_;
	
	@Override
	public Object getNativeObject() {
		return bool_;
	}
	
	@Override
	public String toString() {
		return String.format("boolean [value=%s]", super.getLiteral());
	}
	
	public static final BoolValue TRUE = new BoolValue(Tag.TRUE_LITERAL);
	public static final BoolValue FALSE = new BoolValue(Tag.FALSE_LITERAL);
	
}
