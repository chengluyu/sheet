package ast;

public class BinaryOperation extends Expression {

	public BinaryOperation(Expression left, Expression right) {
		left_ = left;
		right_ = right;
	}
	
	private Expression left_;
	private Expression right_;

}
