package ast;

public class ContinueStatement extends Statement {

	public ContinueStatement(IterationStatement target) {
		target_ = target;
	}
	
	private IterationStatement target_;

}
