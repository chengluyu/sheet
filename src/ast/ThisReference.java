package ast;

import ast.symbol.ClassSymbol;

public class ThisReference extends Reference {

	public ThisReference(ClassSymbol classSymb) {
		classRef_ = classSymb;
	}
	
	private ClassSymbol classRef_;

	@Override
	public void inspect(AstNodePrinter printer) {
		// TODO Auto-generated method stub

	}

}
