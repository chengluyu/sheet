package parser;

import java.util.HashMap;

public class SymbolTable {

	public SymbolTable() {
		symbols_ = new HashMap<String, Symbol>();
	}
	
	private HashMap<String, Symbol> symbols_;
	
	public boolean contains(String name) {
		return symbols_.containsKey(name);
	}
	
	public void insert(String name, Symbol symb) {
		symbols_.put(name, symb);
	}
	
	public Symbol find(String name) {
		return symbols_.get(name);
	}

}
