package ast.symbol;

import ast.AstNode;
import ast.SymbolReference;

public abstract class Symbol extends AstNode {

	public Symbol(String name, int id) {
		name_ = name;
		ref_ = new SymbolReference(this);
		id_ = id;
	}
	
	private SymbolReference ref_;
	private String name_;
	private int id_;
	
	// Properties
	
	public SymbolReference reference() {
		return ref_;
	}
	
	public String name() {
		return name_;
	}
	
	public int id() {
		return id_;
	}
	
	public boolean isConstant() {
		return false;
	}
	
	public boolean isFunction() {
		return false;
	}
	
	public boolean isVariable() {
		return false;
	}
	
	public boolean isGlobal() {
		return false;
	}
	
	public boolean isLocal() {
		return false;
	}
	
	public boolean isArgument() {
		return false;
	}

}
