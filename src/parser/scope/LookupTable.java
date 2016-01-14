package parser.scope;

import java.util.HashMap;

import parser.symbol.Symbol;

public class LookupTable<T extends Symbol> {

	public LookupTable() {
		table_ = new HashMap<String, T>();
	}
	
	private HashMap<String, T> table_;
	
	public boolean contains(String name) {
		return table_.containsKey(name);
	}
	
	public T lookup(String name) {
		return table_.get(name);
	}
	
	public void insert(T symbol) {
		table_.put(symbol.name(), symbol);
	}

}
