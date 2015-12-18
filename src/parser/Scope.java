package parser;

import utils.SyntaxError;

public abstract class Scope {

	public Scope(ScopeManager sm, Scope parent) {
		manager_ = sm;
		parent_ = parent;
	}
	
	private final ScopeManager manager_;
	private final Scope parent_;
	
	// Properties
	
	public final ScopeManager manager() {
		return manager_;
	}
	
	public final Scope parent() {
		return parent_;
	}
	
	// Look-ups
	
	public abstract Symbol findInThisScope(String name);
	
	public final Symbol find(String name) {
		Scope scope = this;
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
	
	public abstract Symbol defineConstant(String name) throws SyntaxError;
	public abstract Symbol defineVariable(String name) throws SyntaxError;

}
