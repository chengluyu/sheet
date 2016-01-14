package parser.symbol;

import parser.scope.Scope;

public class VariableSymbol extends Symbol {

	public VariableSymbol(Scope scope, int id, String name) {
		super(scope, id, name);
	}

}
