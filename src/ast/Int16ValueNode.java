package ast;

import type.PrimitiveType;
import type.Type;

public class Int16ValueNode extends ValueNode {

	public Int16ValueNode(short value) {
		data_ = new Short(value);
	}
	
	private final Short data_;

	@Override
	public Type getType() {
		return PrimitiveType.SHORT;
	}

	@Override
	public Object getNativeValue() {
		return data_;
	}

}
