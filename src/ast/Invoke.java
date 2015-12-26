package ast;

import java.util.Collection;
import java.util.Iterator;

public class Invoke extends Expression {

	public Invoke(Expression func, ExpressionGroup args) {
		func_ = func;
		args_ = args;
	}
	
	private Expression func_;
	private ExpressionGroup args_;
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("function invoke");
		printer.child("function", func_);
		printer.child("arguments", args_);
		printer.endBlock();
	}
	
}
