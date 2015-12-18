package parser;

import utils.SyntaxError;

public class FunctionScope extends LocalScope {

	public FunctionScope(ScopeManager sm, LocalScope parent) {
		super(sm, parent);
		arguments_ = new SymbolTable();
	}
	
	private final SymbolTable arguments_;
	
	@Override
	public Symbol findInThisScope(String name) {
		Symbol symb = super.findInThisScope(name);
		if (symb == null)
			symb = arguments_.find(name);
		return symb;
	}
	
	public Symbol defineArgument(String argName) throws SyntaxError {
		if (arguments_.contains(argName))
			throw new SyntaxError(super.manager().parser().position(),
					"duplicated argument: " + argName);
		Symbol symb = new Symbol(argName, SymbolType.ARGUMENT);
		arguments_.insert(argName, symb);
		return symb;
	}
	


}
