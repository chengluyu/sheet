package ast;

public class BreakStatement extends Statement {

	public BreakStatement(BreakableStatement target) {
		target_ = target;
	}
	
	private BreakableStatement target_;
	

}
