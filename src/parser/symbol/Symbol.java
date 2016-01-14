package parser.symbol;

import parser.scope.Scope;

public class Symbol {

	public Symbol(Scope scope, int id, String name) {
		scope_ = scope;
		id_ = id;
		name_ = name;
	}
	
	private final Scope scope_;
	private final int id_;
	private final String name_;
	
	public int id() {
		return id_;
	}
	
	public String name() {
		return name_;
	}
	
	public boolean isGlobal() {
		return scope_.isGlobalScope();
	}
	
	public boolean isFunction() {
		return false;
	}
	
	public boolean isContant() {
		return false;
	}
	
	public boolean isArgument() {
		return false;
	}

}
