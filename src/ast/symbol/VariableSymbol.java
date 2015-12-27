package ast.symbol;

import ast.AstNodePrinter;

public class VariableSymbol extends Symbol {

	public VariableSymbol(String name, int id) {
		super(name, id);
	}
	
	@Override
	public boolean isVariable() {
		return true;
	}

	@Override
	public void inspect(AstNodePrinter printer) {
		printer.text(super.name());
	}

}
