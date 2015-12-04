package parser;

import java.util.HashMap;

public class SymbolTable {

	public SymbolTable(SymbolTable parent) {
		symbols_ = new HashMap<String, String>();
		parent_ = parent;
	}
	
	public SymbolTable getParent() {
		return parent_;
	}
	
	public void insert(String id, String type) {
		symbols_.put(id, type);
	}
	
	public void contains(String id) {
		SymbolTable tab = this;
		while (tab != null) {
			
		}
	}
	
	private HashMap<String, String> symbols_;
	private SymbolTable parent_;

}
