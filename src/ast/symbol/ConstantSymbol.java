package ast.symbol;

import ast.AstNodePrinter;

public class ConstantSymbol extends Symbol {

	public ConstantSymbol(String name) {
		super(name);
	}
	
	@Override
	public boolean isConstant() {
		return true;
	}

	@Override
	public void inspect(AstNodePrinter printer) {
		printer.text(super.name());
	}

}
