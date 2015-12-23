package scope;

import ast.symbol.ClassSymbol;
import ast.symbol.FunctionSymbol;

public class ModuleScope extends LocalScope {

	public ModuleScope() {
		
	}
	
	public void defineFunction(FunctionSymbol symb) {
		defineSymbol(symb);
	}
	
	public void defineClass(ClassSymbol symb) {
		defineSymbol(symb);
	}

}
