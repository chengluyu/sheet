package ast;

import ast.symbol.Symbol;
import parser.ModuleEnv;
import compiler.ExpressionCompiler;
import utils.CompileError;

public class SymbolReference extends Literal {
	
	private class UnresolvedInfo {
		
		public UnresolvedInfo(String n, ModuleEnv ss) {
			name = n;
			snapshot = ss;
		}
		
		public Symbol resolve() {
			return snapshot.lookup(name);
		}
		
		public String name;
		public ModuleEnv snapshot;
		
	}

	public SymbolReference(Symbol symb) {
		refSymbol_ = symb;
		resolved_ = null;
	}
	
	public SymbolReference(String name, ModuleEnv snapshot) {
		refSymbol_ = null;
		resolved_ = new UnresolvedInfo(name, snapshot);
	}
	
	private Symbol refSymbol_;
	private UnresolvedInfo resolved_;
	
	public Symbol symbol() {
		return refSymbol_;
	}
	
	public void resolve() throws CompileError {
		if (resolved()) return;
		
		Symbol symb = resolved_.resolve();
		if (symb == null) {
			throw new CompileError("unresolved symbol: " + resolved_.name);
		} else {
			resolved_ = null;
			refSymbol_ = symb;
		}
	}
	
	public boolean resolved() {
		return resolved_ == null;
	}
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("symbol reference");
		printer.property("name", refSymbol_.name());
		printer.endBlock();
	}

	@Override
	public void compile(ExpressionCompiler compiler) throws CompileError {
		if (!resolved()) resolve();
		
		if (refSymbol_.isArgument()) {
			compiler.loadArgument(refSymbol_.id());
		} else if (refSymbol_.isLocal()) {
			compiler.loadLocal(refSymbol_.id());
		} else if (refSymbol_.isGlobal()) {
			compiler.loadGlobal(refSymbol_.id());
		} else if (refSymbol_.isFunction()) {
			throw new CompileError("does not support function as object");
		} else {
			throw new CompileError("unreachable");
			// TODO add handler here
		}
	}

}
