package token;

public class ComparisionOp extends BinaryOp {

	private ComparisionOp(Tag tag, String literal, int precedence) {
		super(tag, literal, precedence);
	}
	
	@Override
	public String toString() {
		return String.format("comparision op [literal='%s', precedence=%d]", 
				super.getLiteral(), super.getPrecedence());
	}
	
	public static final ComparisionOp EQ = new ComparisionOp(Tag.EQ, "==", BinaryOp.PRECEDENCE_EQUALITY);
	public static final ComparisionOp NE = new ComparisionOp(Tag.NE, "!=", BinaryOp.PRECEDENCE_EQUALITY);
	public static final ComparisionOp LT = new ComparisionOp(Tag.LT, "<", BinaryOp.PRECEDENCE_INEQUALITY);
	public static final ComparisionOp GT = new ComparisionOp(Tag.GT, ">", BinaryOp.PRECEDENCE_INEQUALITY);
	public static final ComparisionOp LTE = new ComparisionOp(Tag.LTE, "<=", BinaryOp.PRECEDENCE_INEQUALITY);
	public static final ComparisionOp GTE = new ComparisionOp(Tag.GTE, ">=", BinaryOp.PRECEDENCE_INEQUALITY);

}
