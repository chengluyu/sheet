package ast;

import compiler.ByteCodeCompiler;
import parser.symbol.Symbol;
import utils.CompileError;

public class SymbolReference extends Literal {

	public SymbolReference(Symbol symb) {
		refSymbol_ = symb;
		refName_ = null;
	}
	
	public SymbolReference(String name) {
		refSymbol_ = null;
		refName_ = name;
	}
	
	private Symbol refSymbol_;
	private String refName_;
	
	public Symbol symbol() {
		return refSymbol_;
	}
	
	public boolean resolved() {
		return refName_ == null;
	}
	
	public void resolve(ByteCodeCompiler compiler) throws CompileError {
		refSymbol_ = compiler.module().scope().lookup(refName_);
		if (refSymbol_ == null)
			throw new CompileError(String.format(
					"undefined reference to \"%s\"", refName_));
		else
			refName_ = null;
	}
	
	@Override
	public void inspect(AstNodePrinter printer) {
		if (resolved()) {
			printer.beginBlock("symbol reference");
			printer.property("name", refSymbol_.name());
			printer.endBlock();
		} else {
			printer.beginBlock("unresolved symbol reference");
			printer.property("name", refSymbol_.name());
			printer.endBlock();
		}
	}

	@Override
	public void compile(ByteCodeCompiler compiler) throws CompileError {
		if (!resolved())
			resolve(compiler);
		
		if (refSymbol_.isArgument())
			compiler.loadArgument(refSymbol_.id());
		else if (refSymbol_.isGlobal())
			compiler.loadArgument(refSymbol_.id());
		else if (refSymbol_.isFunction())
			throw new CompileError(String.format(
					"cannot reference the function \"%s\" as a value",
					refSymbol_.name()));
		else
			compiler.loadLocal(refSymbol_.id());
	}

}
