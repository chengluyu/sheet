package parser.scope;

import parser.Context;
import parser.symbol.ArgumentSymbol;
import parser.symbol.ConstantSymbol;
import parser.symbol.FunctionSymbol;
import parser.symbol.Symbol;
import parser.symbol.VariableSymbol;
import utils.SyntaxError;

public class ReferenceScope extends Scope {

	public ReferenceScope(Context context, Scope parent) {
		super(context, parent);
		locals_ = new LookupTable<Symbol>();
	}
	
	private LookupTable<Symbol> locals_;

	@Override
	public int allocateSymbolID() {
		return parent().allocateSymbolID();
	}

	@Override
	public ArgumentSymbol defineArgument(String t) throws SyntaxError {
		reportError(String.format(
				"cannot define an argument \"%s\" in a function scope", t));
		return null; // UNREACHABLE code, make compiler happy
	}

	@Override
	public ConstantSymbol defineConstant(String t) throws SyntaxError {
		if (locals_.contains(t)) {
			reportError(String.format("duplicated constant name: \"%s\"", t));
			return null;
		} else {
			ConstantSymbol symb =
					new ConstantSymbol(this, allocateSymbolID(), t);
			locals_.insert(symb);
			registerSymbol(symb);
			return symb;
		}
	}

	@Override
	public FunctionSymbol defineFunction(String t) throws SyntaxError {
		reportError(String.format(
				"cannot define an function \"%s\" in a function scope", t));
		return null; // UNREACHABLE code, make compiler happy
	}

	@Override
	public VariableSymbol defineVariable(String t) throws SyntaxError {
		if (locals_.contains(t)) {
			reportError(String.format("duplicated variable name: \"%s\"", t));
			return null;
		} else {
			VariableSymbol symb =
					new VariableSymbol(this, allocateSymbolID(), t);
			locals_.insert(symb);
			registerSymbol(symb);
			return symb;
		}
	}
	
	@Override
	public Symbol lookup(String t) {
		return locals_.lookup(t);
	}

	@Override
	public boolean isGlobalScope() {
		return false;
	}

	@Override
	public void registerSymbol(Symbol symbol) {
		parent().registerSymbol(symbol);
	}

}
