package ast;

import java.util.ArrayList;
import java.util.Iterator;

import ast.symbol.FunctionSymbol;
import ast.symbol.Symbol;
import parser.ModuleEnv;

public class Module extends AstNode {

	public Module(ArrayList<Assignment> inits, ModuleEnv env) {
		inits_ = inits;
		env_ = env;
	}
	
	private ArrayList<Assignment> inits_;
	private final ModuleEnv env_;
	
	public ArrayList<Assignment> initializations() {
		return inits_;
	}
	
	public ModuleEnv module() {
		return env_;
	}	

	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("module");
		printer.subBlock("functions");
		Iterator<FunctionSymbol> itf = env_.functions().iterator();
		while (itf.hasNext()) {
			FunctionSymbol fs = itf.next();
			printer.child(fs.name(), fs);
		}
		printer.endBlock();
		printer.subBlock("locals");
		Iterator<Symbol> itl = env_.globals().iterator();
		while (itl.hasNext()) {
			Symbol symb = itl.next();
			if (symb.isConstant())
				printer.property("constant", symb.name());
			else if (symb.isVariable())
				printer.property("variable", symb.name());
		}
		printer.endBlock();
		printer.endBlock();
	}

}
