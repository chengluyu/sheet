package parser.symbol;

import parser.scope.Scope;

public class ConstantSymbol extends Symbol {

	public ConstantSymbol(Scope scope, int id, String name) {
		super(scope, id, name);
	}
	
	@Override
	public boolean isContant() {
		return false;
	}

}
