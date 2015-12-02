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
	
	public static AssignmentOp ASSIGN = new AssignmentOp(Tag.ASSIGN, "=");
	public static AssignmentOp BIT_OR = new AssignmentOp(Tag.ASSIGN_BIT_OR, "|=");
	public static AssignmentOp BIT_XOR = new AssignmentOp(Tag.ASSIGN_BIT_XOR, "^=");
	public static AssignmentOp BIT_AND = new AssignmentOp(Tag.ASSIGN_BIT_AND, "&=");
	public static AssignmentOp SHL = new AssignmentOp(Tag.ASSIGN_SHL, "<<=");
	public static AssignmentOp SHR = new AssignmentOp(Tag.ASSIGN_SHR, ">>=");
	public static AssignmentOp ADD = new AssignmentOp(Tag.ASSIGN_ADD, "+=");
	public static AssignmentOp SUB = new AssignmentOp(Tag.ASSIGN_SUB, "-=");
	public static AssignmentOp MUL = new AssignmentOp(Tag.ASSIGN_MUL, "*=");
	public static AssignmentOp DIV = new AssignmentOp(Tag.ASSIGN_DIV, "/=");
	public static AssignmentOp MOD = new AssignmentOp(Tag.ASSIGN_MOD, "%=");

}
