package compiler;

public class Instruction {

	public Instruction(OpCode opc) {
		this(opc, 0);
	}
	
	public Instruction(OpCode opc, int opr) {
		opCode_ = opc;
		operand_ = opr;
	}
	
	private final OpCode opCode_;
	private int operand_;
	
	public OpCode opcode() {
		return opCode_;
	}
	
	public int operand() {
		return operand_;
	}
	
	public void setOperand(int i) {
		operand_ = i;
	}
	
	@Override
	public String toString() {
		if (opCode_.hasOperand())
			return opCode_.name() + ' ' + Integer.toString(operand_);
		return opCode_.name();
	}

}
