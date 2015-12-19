package parser;

import ast.StatementBlock;

public class FunctionSymbol extends Symbol {

	public FunctionSymbol(String name, StatementBlock funcBody) {
		super(name, SymbolType.FUNCTION);
		stmts_ = funcBody;
	}
	
	private StatementBlock stmts_;

}
