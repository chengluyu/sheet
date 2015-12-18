package parser;

import utils.SyntaxError;

public class LocalScope {

	public LocalScope(ScopeManager sm, LocalScope parent) {
		manager_ = sm;
		parent_ = parent;
		locals_ = new SymbolTable();
	}
	
	private final ScopeManager manager_;
	private final LocalScope parent_;
	private final SymbolTable locals_;
	
	// Properties
	
	public final ScopeManager manager() {
		return manager_;
	}
	
	public final LocalScope parent() {
		return parent_;
	}
	
	// Look-ups
	
	public Symbol findInThisScope(String name) {
		return locals_.find(name);
	}
	
	public final Symbol find(String name) {
		LocalScope scope = this;
		Symbol symb = null;
		while (scope.parent_ != null) {
			symb = scope.findInThisScope(name);
			if (symb != null)
				break;
			else
				scope = scope.parent_;
		}
		return symb;
	}
	
	// Insertion
	
	public Symbol defineConstant(String constantName) throws SyntaxError {
		return defineLocal(constantName, SymbolType.CONSTANT);
	}
	
	public Symbol defineVariable(String varName) throws SyntaxError {
		return defineLocal(varName, SymbolType.VARIABLE);
	}
	
	private Symbol defineLocal(String name, SymbolType type) throws SyntaxError {
		if (locals_.contains(name))
			throw new SyntaxError(manager_.parser().position(),
					"duplicate local name: " + name);
		Symbol symb = new Symbol(name, type);
		locals_.insert(name, symb);
		return symb;
	}

}
