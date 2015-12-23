package ast.symbol;

import java.util.Collection;
import java.util.HashMap;

public class SymbolTable {
	
	public SymbolTable() {
		this(null);
	}
	
	public SymbolTable(SymbolTable parent) {
		parent_ = parent;
		symbols_ = new HashMap<String, Symbol>();
	}
	
	private final SymbolTable parent_;
	private HashMap<String, Symbol> symbols_;

	public boolean contains(String name) {
		return symbols_.containsKey(name);
	}
	
	public void insert(Symbol symb) {
		symbols_.put(symb.name(), symb);
	}
	
	public Symbol findThis(String name) {
		return symbols_.get(name);
	}
	
	public Symbol find(String name) {
		SymbolTable st = this;
		Symbol symb = null;
		while (st != null) {
			if ((symb = st.symbols_.get(name)) != null)
				return symb;
			st = st.parent_;
		}
		return symb;
	}
	
	// Properties
	
	public Collection<Symbol> symbols() {
		return symbols_.values();
	}

}
