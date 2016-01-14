package compiler;

public class Blank {

	public Blank(Instruction ins) {
		ins_ = ins;
	}
	
	private Instruction ins_;
	
	public void fill(int operand) {
		ins_.setOperand(operand);
	}

}
