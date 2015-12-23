package ast.symbol;

import ast.AstNodePrinter;
import ast.StatementBlock;
import scope.FunctionScope;

public class FunctionSymbol extends Symbol {

	public FunctionSymbol(String name, FunctionScope bind,
			StatementBlock body) {
		super(name);
		bindScope_ = bind;
		funcBody_ = body;
	}
	
	private FunctionScope bindScope_;
	private StatementBlock funcBody_;
	
	@Override
	public boolean isFunction() {
		return true;
	}

	public StatementBlock body() {
		return funcBody_;
	}
	
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("function");
		printer.child("statements", funcBody_);
		printer.endBlock();
	}

}
