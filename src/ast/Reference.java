package ast;

import parser.Symbol;

public class Reference extends Literal {
	
	public Reference(Symbol symb) {
		refSymbol_ = symb;
	}
	
	private Symbol refSymbol_;
	
	public String name() {
		return refSymbol_.name();
	}
	
}