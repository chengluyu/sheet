package parser.symbol;

import parser.scope.Scope;

public class ArgumentSymbol extends Symbol {

	public ArgumentSymbol(Scope scope, int id, String name) {
		super(scope, id, name);
	}
	
	@Override
	public boolean isArgument() {
		return true;
	}

}
