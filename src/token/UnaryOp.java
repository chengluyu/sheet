package token;

public class UnaryOp extends Token {

	private UnaryOp(Tag tag, String literal) {
		super(tag, literal);
	}
	
	@Override
	public String toString() {
		return String.format("unary operator [literal = '%s']", super.getLiteral());
	}
	
	public static final UnaryOp NOT = new UnaryOp(Tag.NOT, "!");
	public static final UnaryOp BIT_NOT = new UnaryOp(Tag.BIT_NOT, "~");
	public static final UnaryOp TYPEOF = new UnaryOp(Tag.TYPEOF, "typeof");
	
}
