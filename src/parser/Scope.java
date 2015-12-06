package parser;

import type.Type;

public class Scope {

	public Scope() {
		current_ = new SymbolTable(null);
	}
	
	public void enterScope() {
		current_ = new SymbolTable(current_);
	}
	
	public void exitScope() {
		if (current_.getParent() == null) {
			// raise error
		} else {
			current_ = current_.getParent();
		}
	}
	
	public void defineConstant(String id, Type t) {
		current_.insert(id, t);
	}
	
	public void defineVariable(String id, Type t) {
		current_.insert(id, t);
	}
	
	public void defineType(String typename, Type t) {
		current_.insert(typename, t);
	}
	
	private SymbolTable current_;

}
