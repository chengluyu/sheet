package ast;

import ast.symbol.Symbol;

public class SymbolReference extends Reference {

	public SymbolReference(Symbol symb) {
		refSymbol_ = symb;
	}
	
	private Symbol refSymbol_;
	
	public String name() {
		return refSymbol_.name();
	}
	
	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("symbol reference");
		printer.property("name", refSymbol_.name());
		printer.endBlock();
	}

}
