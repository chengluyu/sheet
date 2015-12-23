package ast;

public class IfStatement extends Statement {

	public IfStatement(Expression cond, Statement then, Statement otherwise) {
		cond_ = cond;
		then_ = then;
		else_ = otherwise;
	}
	
	private Expression cond_;
	private Statement then_;
	private Statement else_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("if statement");
		printer.child("condition", cond_);
		printer.child("then", then_);
		if (else_ != null)
			printer.child("else", else_);
		printer.endBlock();
	}

}
