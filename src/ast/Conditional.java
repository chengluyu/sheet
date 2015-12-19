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
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.title("conditional operation");
		printer.begin();
		printer.child("condition", cond_);
		printer.child("then", then_);
		printer.child("else", else_);
		printer.end();
	}

}
