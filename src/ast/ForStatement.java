package ast;

import parser.LocalScope;

public class ForStatement extends IterationStatement {

	public ForStatement() {
		init_ = null;
		cond_ = null;
		incr_ = null;
		body_ = null;
		boundScope_ = null;
	}
	
	private Expression init_;
	private Expression cond_;
	private Expression incr_;
	private Statement body_;
	private LocalScope boundScope_;
	
	public void setup(Expression init, Expression cond, Expression incr,
			Statement body, LocalScope boundScope) {
		init_ = init;
		cond_ = cond;
		incr_ = incr;
		body_ = body;
		boundScope_ = boundScope;
	}
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.title("for loop");
		printer.begin();
		printer.child("initial", init_);
		printer.child("condition", cond_);
		printer.child("incremental", incr_);
		printer.child("body", body_);
		printer.end();
	}

}
