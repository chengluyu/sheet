package ast.symbol;

import ast.AstNodePrinter;

public class LocalSymbol extends Symbol {

	public LocalSymbol(String name, int id, boolean isConst) {
		super(name, id);
		isConst_ = isConst;
	}
	
	private boolean isConst_;
	
	@Override
	public boolean isConstant() {
		return isConst_;
	}
	
	@Override
	public boolean isVariable() {
		return !isConst_;
	}
	
	@Override
	public boolean isLocal() {
		return true;
	}

	@Override
	public void inspect(AstNodePrinter printer) {
		// TODO Auto-generated method stub

	}

}
