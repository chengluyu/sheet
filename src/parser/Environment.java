package parser;

import type.Type;

public class Environment {

	public Environment() {
		symbols_ = new SymbolTable(null);
	}
	
	public void createScope() {
		
	}
	
	public void destroyScope() {
		if (symbols_.getParent() == null) {
			// TODO: error: try to destroy top scope
		}
		symbols_ = symbols_.getParent();
	}
	
	public void define(String id, Type type) {
		
	}
	
	public void find(String id) {
		
	}
	
	private SymbolTable symbols_;

}
