package parser;

import utils.SyntaxError;

public class FunctionScope extends Scope {

	public FunctionScope(ScopeManager sm, Scope parent) {
		super(sm, parent);
		arguments_ = new SymbolTable();
		locals_ = new SymbolTable();
	}
	
	private final SymbolTable arguments_;
	private final SymbolTable locals_;
	
	@Override
	public Symbol findInThisScope(String name) {
		Symbol symb = locals_.find(name);
		if (symb == null) {
			symb = arguments_.find(name);
		}
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
	
	@Override
	public Symbol defineConstant(String constantName) throws SyntaxError {
		return defineLocal(constantName, SymbolType.CONSTANT);
	}
	
	@Override
	public Symbol defineVariable(String varName) throws SyntaxError {
		return defineLocal(varName, SymbolType.VARIABLE);
	}
	
	private Symbol defineLocal(String name, SymbolType type) throws SyntaxError {
		if (locals_.contains(name))
			throw new SyntaxError(super.manager().parser().position(),
					"duplicate local variable: " + name);
		Symbol symb = new Symbol(name, type);
		locals_.insert(name, symb);
		return symb;
	}

}
