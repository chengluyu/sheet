package ast;

public class DoWhileStatement extends IterationStatement {

	public DoWhileStatement() {
		
	}
	
	private Expression cond_;
	private Statement body_;
	
	public void setup(Expression cond, Statement body) {
		cond_ = cond;
		body_ = body;
	}
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.title("do-while loop");
		printer.begin();
		printer.child("condition", cond_);
		printer.child("body", body_);
		printer.end();
	}

}
