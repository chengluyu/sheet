package ast;

public class ForStatement extends IterationStatement {

	public ForStatement(Expression init,
			Expression cond,
			Expression incr,
			Statement body) {
		initExpr_ = init;
		condExpr_ = cond;
		incrExpr_ = incr;
		body_ = body;
	}
	
	private Expression initExpr_;
	private Expression condExpr_;
	private Expression incrExpr_;
	private Statement body_;

}
