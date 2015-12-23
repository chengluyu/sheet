package ast.symbol;

import ast.AstNode;
import ast.SymbolReference;

public abstract class Symbol extends AstNode {

	public Symbol(String name) {
		name_ = name;
		ref_ = new SymbolReference(this);
		refCount_ = 0;
	}
	
	private int refCount_;
	private SymbolReference ref_;
	private String name_;
	
	// Properties
	
	public int refCount() {
		return refCount_;
	}
	
	public SymbolReference reference() {
		refCount_++;
		return ref_;
	}
	
	public String name() {
		return name_;
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

}
