package compiler;

import java.util.ArrayList;

public class FillBack {

	public FillBack() {
		blanks_ = new ArrayList<Instruction>();
	}
	
	private ArrayList<Instruction> blanks_;
	
	public void clear() {
		blanks_.clear();
	}
	
	public void add(Instruction ins) {
		blanks_.add(ins);
	}
	
	public void fill(int operand) {
		blanks_.forEach(x -> {
			x.setOperand(operand);
		});
	}

}
