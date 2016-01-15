package parser.symbol;

import parser.scope.Scope;

public class FunctionSymbol extends Symbol {

	public FunctionSymbol(Scope scope, int id, String name) {
		super(scope, id, name);
	}
	
	@Override
	public boolean isFunction() {
		return true;
	}

}
