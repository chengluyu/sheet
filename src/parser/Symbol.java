package parser;

public class Symbol {

	public Symbol(String name, SymbolType type) {
		name_ = name;
		type_ = type;
	}
	
	private final String name_;
	private final SymbolType type_;
	
	// Properties
	
	public String name() {
		return name_;
	}
	
	public SymbolType type() {
		return type_;
	}

}
