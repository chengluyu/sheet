package symbol;

import type.Type;

public abstract class Symbol {
	
	public Symbol(String name) {
		symbolName_ = name;
	}
	
	private String symbolName_;
	
	public String getSymbolName() {
		return symbolName_;
	}
	
	public abstract Type getType();
	
	public boolean isConstant() {
		return false;
	}
	
	public boolean isFunction() {
		return false;
	}
	
	public boolean isType() {
		return false;
	}
	
	public boolean isVariable() {
		return false;
	}
	
}
