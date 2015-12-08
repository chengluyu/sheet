package ast;

public class WhileStatement extends Statement {

	public WhileStatement(Expression cond, Statement body) {
		cond_ = cond;
		body_ = body;
	}
	
	private Expression cond_;
	private Statement body_;

}
