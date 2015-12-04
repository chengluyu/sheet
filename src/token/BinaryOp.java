package token;

public class BinaryOp extends Token {

	protected BinaryOp(Tag tag, String literal, int precedence) {
		super(tag, literal);
		precedence_ = precedence;
	}
	
	private final int precedence_;
	
	@Override
	public boolean isBinaryOp() {
		return true;
	}
	
	public int getPrecedence() {
		return precedence_;
	}
	
	@Override
	public String toString() {
		return String.format("binary op [literal='%s', precedence=%d]", 
				super.getLiteral(), getPrecedence());
	}
	
	protected static final int PRECEDENCE_COMMA = 1;
	protected static final int PRECEDENCE_ASSIGNMENT = 2;
	protected static final int PRECEDENCE_CONDITIONAL = 3;
	protected static final int PRECEDENCE_OR = 4;
	protected static final int PRECEDENCE_AND = 5;
	protected static final int PRECEDENCE_BIT_OR = 6;
	protected static final int PRECEDENCE_BIT_XOR = 7;
	protected static final int PRECEDENCE_BIT_AND = 8;
	protected static final int PRECEDENCE_EQUALITY = 9;
	protected static final int PRECEDENCE_INEQUALITY = 10;
	protected static final int PRECEDENCE_BITWISE = 11;
	protected static final int PRECEDENCE_ADD_SUB = 12;
	protected static final int PRECEDENCE_MUL_DIV_MOD = 13;

	public static final BinaryOp COMMA = new BinaryOp(Tag.COMMA, ",", PRECEDENCE_COMMA);
	public static final BinaryOp OR = new BinaryOp(Tag.OR, "||", PRECEDENCE_OR);
	public static final BinaryOp AND = new BinaryOp(Tag.AND, "&&", PRECEDENCE_AND);
	public static final BinaryOp BIT_OR = new BinaryOp(Tag.BIT_OR, "|", PRECEDENCE_BIT_OR);
	public static final BinaryOp BIT_XOR = new BinaryOp(Tag.BIT_XOR, "^", PRECEDENCE_BIT_XOR);
	public static final BinaryOp BIT_AND = new BinaryOp(Tag.BIT_AND, "&", PRECEDENCE_BIT_AND);
	public static final BinaryOp SHL = new BinaryOp(Tag.SHL, "<<", PRECEDENCE_BITWISE);
	public static final BinaryOp SHR = new BinaryOp(Tag.SHR, ">>", PRECEDENCE_BITWISE);
	public static final BinaryOp ADD = new BinaryOp(Tag.ADD, "+", PRECEDENCE_ADD_SUB);
	public static final BinaryOp SUB = new BinaryOp(Tag.SUB, "-", PRECEDENCE_ADD_SUB);
	public static final BinaryOp MUL = new BinaryOp(Tag.MUL, "*", PRECEDENCE_MUL_DIV_MOD);
	public static final BinaryOp DIV = new BinaryOp(Tag.DIV, "/", PRECEDENCE_MUL_DIV_MOD);
	public static final BinaryOp MOD = new BinaryOp(Tag.MOD, "%", PRECEDENCE_MUL_DIV_MOD);	

}
