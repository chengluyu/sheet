package parser;

import ast.symbol.ConstantSymbol;
import ast.symbol.Symbol;
import ast.symbol.VariableSymbol;

public abstract class Environment {
	
	public abstract ConstantSymbol defineConstant(String name);
	public abstract VariableSymbol defineVariable(String name);
	public abstract Symbol lookup(String name);
	
}
