package ast;

public class ExpressionStatement extends Statement {

	public ExpressionStatement(Expression expr) {
		expr_ = expr;
	}
	
	private Expression expr_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.title("expression statement");
		printer.begin();
		printer.child("expr", expr_);
		printer.end();
	}

}
