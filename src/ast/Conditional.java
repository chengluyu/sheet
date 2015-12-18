package ast;

public class Conditional extends Expression {

	public Conditional(Expression cond, Expression then, Expression otherwise) {
		cond_ = cond;
		then_ = then;
		else_ = otherwise;
	}
	
	private Expression cond_;
	private Expression then_;
	private Expression else_;

}
