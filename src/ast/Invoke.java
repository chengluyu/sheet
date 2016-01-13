package ast;

import ast.symbol.Symbol;
import compiler.ExpressionCompiler;
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
	public void compile(ExpressionCompiler compiler) throws CompileError {
		if (!(func_ instanceof SymbolReference))
			throw new CompileError("callee expression must be a function");
		SymbolReference sr = (SymbolReference) func_;
		if (!sr.resolved()) sr.resolve();
		Symbol symb = sr.symbol();
		if (!symb.isFunction())
			throw new CompileError("callee object must be a function");
		compiler.call(symb.id());
	}
	
}
