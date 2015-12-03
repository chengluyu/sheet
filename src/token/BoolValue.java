package token;

public class BoolValue extends Value {

	private BoolValue(Tag tag, String literal) {
		super(tag, literal);
	}
	
	@Override
	public String toString() {
		return String.format("boolean [value=%s]", super.getLiteral());
	}
	
	public static final BoolValue TRUE = new BoolValue(Tag.TRUE_LITERAL, "true");
	public static final BoolValue FALSE = new BoolValue(Tag.FALSE_LITERAL, "false");
	
}
