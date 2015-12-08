package ast;

import type.Type;
import symbol.ValueSymbol;

public class SymbolRefNode extends ValueNode {

	public SymbolRefNode(ValueSymbol symb) {
		refSymbol_ = symb;
	}
	
	private ValueSymbol refSymbol_;

	@Override
	public Type getType() {
		return refSymbol_.getType();
	}

	@Override
	public Object getNativeValue() {
		return null;
	}

}
