package ast;

public class ExpressionStatement extends Statement {

	public ExpressionStatement(Expression expr) {
		expr_ = expr;
	}
	
	private Expression expr_;

}
