package ast;

import compiler.FillBack;
import compiler.Instruction;

public abstract class BreakableStatement extends Statement {

	public BreakableStatement() {
		
	}
	
	private FillBack break_;
	
	public void addBreakFillBack(Instruction ins) {
		break_.add(ins);
	}
	
	public void fillBreak(int pos) {
		break_.fill(pos);
		break_.clear();
	}

}
