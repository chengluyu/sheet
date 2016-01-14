package parser.scope;

import parser.Context;
import parser.symbol.ArgumentSymbol;
import parser.symbol.ConstantSymbol;
import parser.symbol.FunctionSymbol;
import parser.symbol.Symbol;
import parser.symbol.VariableSymbol;
import utils.SyntaxError;

public abstract class Scope {

	public Scope(Context context, Scope parent) {
		context_ = context;
		parent_ = parent;
	}
	
	private final Context context_;
	private final Scope parent_;
	
	public Context context() {
		return context_;
	}
	
	public Scope parent() {
		return parent_;
	}
	
	public abstract int allocateSymbolID();
	public abstract ArgumentSymbol defineArgument(String t) throws SyntaxError;
	public abstract ConstantSymbol defineConstant(String t) throws SyntaxError;
	public abstract FunctionSymbol defineFunction(String t) throws SyntaxError;
	public abstract VariableSymbol defineVariable(String t) throws SyntaxError;
	public abstract void registerSymbol(Symbol symbol);
	public abstract Symbol lookup(String t);
	public abstract boolean isGlobalScope();
	
	protected void reportError(String message) throws SyntaxError {
		throw new SyntaxError(context_.parser().currentPosition(), message);
	}

}
