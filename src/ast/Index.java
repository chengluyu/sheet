package ast;

public class Index extends Expression {
	
	public Index(Expression left, Expression right) {
		value_ = left;
		refinement_ = right;
	}
	
	private Expression value_;
	private Expression refinement_;
	
}
