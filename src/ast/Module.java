package ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ast.symbol.FunctionSymbol;
import ast.symbol.Symbol;
import parser.Parser;
import scope.ModuleScope;

public class Module extends AstNode {

	public Module(Parser parser) {
		initials_ = new ArrayList<Assignment>();
		scope_ = new ModuleScope();
		parser_ = parser;
	}
	
	private ArrayList<Assignment> initials_;
	private final ModuleScope scope_;
	private final Parser parser_;
	
	public ArrayList<Assignment> initializations() {
		return initials_;
	}
	
	public ModuleScope scope() {
		return scope_;
	}
	
	public Parser parser() {
		return parser_;
	}
	

	@Override
	public void inspect(AstNodePrinter printer) {
		printer.beginBlock("module");
		Collection<Symbol> symbols = scope_.symbols();
		Iterator<Symbol> it = symbols.iterator();
		while (it.hasNext()) {
			Symbol symb = it.next();
			if (symb.isConstant()) {
				printer.property("constant", symb.name());
			} else if (symb.isVariable()) {
				printer.property("variable", symb.name());
			} else if (symb.isFunction()) {
				FunctionSymbol fs = (FunctionSymbol) symb;
				printer.child("function", fs.body());
			}
		}
		printer.endBlock();
	}

}
