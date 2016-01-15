package ast;

import parser.symbol.Symbol;

import java.util.Iterator;

import compiler.ByteCodeCompiler;
import utils.CompileError;

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

	@Override
	public void compile(ByteCodeCompiler compiler) throws CompileError {
		Iterator<? extends Expression> it = args_.expressions().iterator();
		while (it.hasNext()) {
			it.next().compile(compiler);
		}
		if (!(func_ instanceof SymbolReference))
			throw new CompileError("callee expression must be a function");
		SymbolReference ref = (SymbolReference) func_;
		if (!ref.resolved())
			ref.resolve(compiler);
		Symbol symbol = ref.symbol();
		if (!symbol.isFunction())
			throw new CompileError("callee object must be a function");
		compiler.call(symbol.id());
	}
	
}
