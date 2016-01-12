package parser;

import ast.symbol.LocalSymbol;
import ast.symbol.Symbol;

public abstract class Environment {
	
	public abstract LocalSymbol defineConstant(String name);
	public abstract LocalSymbol defineVariable(String name);
	public abstract Symbol lookup(String name);
	
}
