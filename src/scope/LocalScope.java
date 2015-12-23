package scope;

import java.util.Collection;

import ast.symbol.ConstantSymbol;
import ast.symbol.Symbol;
import ast.symbol.SymbolTable;
import ast.symbol.VariableSymbol;

public class LocalScope {
	
	protected LocalScope() {
		// no parental SymbolTable
		locals_ = new SymbolTable();
	}

	public LocalScope(LocalScope upper) {
		locals_ = new SymbolTable(upper.locals_);
	}
	
	private SymbolTable locals_;

	protected void defineSymbol(Symbol symb) {
		locals_.insert(symb);
	}

	public void defineConstant(ConstantSymbol symb) {
		defineSymbol(symb);
	}

	public void defineVariable(VariableSymbol symb) {
		defineSymbol(symb);
	}
	
	public Symbol find(String id) {
		return locals_.find(id);
	}
	
	public Collection<Symbol> symbols() {
		return locals_.symbols();
	}

}
