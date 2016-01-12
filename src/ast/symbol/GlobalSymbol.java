package ast.symbol;

import ast.AstNodePrinter;

public class GlobalSymbol extends LocalSymbol {

	public GlobalSymbol(String name, int id, boolean isConst) {
		super(name, id, isConst);
	}
	
	@Override
	public boolean isGlobal() {
		return true;
	}
	
	@Override
	public boolean isLocal() {
		return false;
	}

	@Override
	public void inspect(AstNodePrinter printer) {
		
	}

}
