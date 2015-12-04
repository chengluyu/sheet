package parser;

import java.util.HashMap;

import type.Type;

public class SymbolTable {

	public SymbolTable(SymbolTable parent) {
		symbols_ = new HashMap<String, Type>();
		parent_ = parent;
	}
	
	public SymbolTable getParent() {
		return parent_;
	}
	
	public void insert(String id, Type type) {
		symbols_.put(id, type);
	}
	
	public boolean contains(String id) {
		SymbolTable table = this;
		while (table != null) {
			if (table.contains(id))
				return true;
			table = table.getParent();
		}
		return false;
	}
	
	private HashMap<String, Type> symbols_;
	private SymbolTable parent_;

}
