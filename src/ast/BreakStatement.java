package ast;

public class BreakStatement extends Statement {

	public BreakStatement() {
		// TODO Auto-generated constructor stub
	}
	
	public void setBreakTarget(BreakableStatement target) {
		target_ = target;
	}
	
	private BreakableStatement target_;
	

}
