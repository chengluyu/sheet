package scope;

import ast.symbol.VariableSymbol;

public class FunctionScope extends SubScope {

	public FunctionScope(ModuleScope parent) {
		super(parent);
	}
	
	public void defineArgument(VariableSymbol symb) {
		defineSymbol(symb);
	}

}
