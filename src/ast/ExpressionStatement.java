package ast;

public class ExpressionStatement extends Statement {

	public ExpressionStatement(Expression expr) {
		expr_ = expr;
	}
	
	private Expression expr_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("expression statement");
		printer.child("expr", expr_);
		printer.endBlock();
	}

}
