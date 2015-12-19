package ast;

public class Invoke extends Expression {

	public Invoke(Expression func, ExpressionGroup args) {
		func_ = func;
		args_ = args;
	}
	
	private Expression func_;
	private ExpressionGroup args_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.title("function invoke");
		printer.begin();
		printer.child("function", func_);
		printer.child("arguments", args_);
		printer.end();
	}

}
