package token;

public class AssignmentOp extends BinaryOp {

	private AssignmentOp(Tag tag, String literal) {
		super(tag, literal, BinaryOp.PRECEDENCE_ASSIGNMENT);
	}
	
	@Override
	public String toString() {
		return String.format("assignment op [literal='%s', precedence=%d]", 
				super.getLiteral(), super.getPrecedence());
	}
	
	public static final AssignmentOp ASSIGN = new AssignmentOp(Tag.ASSIGN, "=");
	public static final AssignmentOp BIT_OR = new AssignmentOp(Tag.ASSIGN_BIT_OR, "|=");
	public static final AssignmentOp BIT_XOR = new AssignmentOp(Tag.ASSIGN_BIT_XOR, "^=");
	public static final AssignmentOp BIT_AND = new AssignmentOp(Tag.ASSIGN_BIT_AND, "&=");
	public static final AssignmentOp SHL = new AssignmentOp(Tag.ASSIGN_SHL, "<<=");
	public static final AssignmentOp SHR = new AssignmentOp(Tag.ASSIGN_SHR, ">>=");
	public static final AssignmentOp ADD = new AssignmentOp(Tag.ASSIGN_ADD, "+=");
	public static final AssignmentOp SUB = new AssignmentOp(Tag.ASSIGN_SUB, "-=");
	public static final AssignmentOp MUL = new AssignmentOp(Tag.ASSIGN_MUL, "*=");
	public static final AssignmentOp DIV = new AssignmentOp(Tag.ASSIGN_DIV, "/=");
	public static final AssignmentOp MOD = new AssignmentOp(Tag.ASSIGN_MOD, "%=");

}
