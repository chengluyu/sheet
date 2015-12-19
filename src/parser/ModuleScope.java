package parser;

import utils.SyntaxError;

public class ModuleScope extends LocalScope {

	public ModuleScope(ScopeManager sm) {
		super(sm, null);
	}
	
	public FunctionSymbol defineFunction(String name, FunctionSymbol symb)
			throws SyntaxError {
		return (FunctionSymbol) defineLocal(name, symb);
	}

}
