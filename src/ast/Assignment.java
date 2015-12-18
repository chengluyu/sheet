package ast;

public class Assignment extends Expression {

	public Assignment(Expression left, Expression right) {
		left_ = left;
		right_ = right;
	}
	
	private Expression left_;
	private Expression right_;

}
