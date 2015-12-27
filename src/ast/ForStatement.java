package ast;

public class ForStatement extends IterationStatement {

	public ForStatement() {
		init_ = null;
		cond_ = null;
		incr_ = null;
		body_ = null;
	}
	
	private Expression init_;
	private Expression cond_;
	private Expression incr_;
	private Statement body_;
	
	public void setup(Expression init, Expression cond, Expression incr,
			Statement body) {
		init_ = init;
		cond_ = cond;
		incr_ = incr;
		body_ = body;
	}
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("for loop");
		printer.child("initial", init_);
		printer.child("condition", cond_);
		printer.child("incremental", incr_);
		printer.child("body", body_);
		printer.endBlock();
	}

}
