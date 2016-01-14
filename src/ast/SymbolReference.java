package ast;

import compiler.ExpressionCompiler;
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
		return refSymbol_ != null;
	}
	
	@Override
	public void inspect(AstNodePrinter printer) {
		if (resolved()) {
			printer.beginBlock("symbol reference");
			printer.property("name", refSymbol_.name());
			printer.endBlock();
		} else {
			printer.beginBlock("unresolved symbol reference");
			printer.property("name", refName_);
			printer.endBlock();
		}
	}

	@Override
	public void compile(ExpressionCompiler compiler) throws CompileError {
//		if (!resolved()) resolve();
//		
//		if (refSymbol_.isArgument()) {
//			compiler.loadArgument(refSymbol_.id());
//		} else if (refSymbol_.isLocal()) {
//			compiler.loadLocal(refSymbol_.id());
//		} else if (refSymbol_.isGlobal()) {
//			compiler.loadGlobal(refSymbol_.id());
//		} else if (refSymbol_.isFunction()) {
//			throw new CompileError("does not support function as object");
//		} else {
//			throw new CompileError("unreachable");
//			// TODO add handler here
//		}
	}

}
