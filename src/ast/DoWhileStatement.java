package ast;

public class DoWhileStatement extends Statement {

	public DoWhileStatement(Expression cond, Statement body) {
		cond_ = cond;
		body_ = body;
	}
	
	private Expression cond_;
	private Statement body_;
	
}
