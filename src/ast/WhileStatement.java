package ast;

public class WhileStatement extends IterationStatement {

	public WhileStatement() {
		
	}
	
	private Expression cond_;
	private Statement body_;
	
	public void setup(Expression cond, Statement body) {
		cond_ = cond;
		body_ = body;
	}
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("while loop");
		printer.child("condition", cond_);
		printer.child("body", body_);
		printer.endBlock();
	}

}
