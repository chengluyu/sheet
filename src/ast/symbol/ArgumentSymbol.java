package ast.symbol;

import ast.AstNodePrinter;

public class ArgumentSymbol extends Symbol {

	public ArgumentSymbol(String name, int id) {
		super(name, id);
	}

	@Override
	public boolean isArgument() {
		return true;
	}

	@Override
	public void inspect(AstNodePrinter printer) {
		printer.text(super.name());
	}

}
