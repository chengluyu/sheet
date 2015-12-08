package ast;

import type.PrimitiveType;
import type.Type;

public class Float32ValueNode extends ValueNode {

	public Float32ValueNode(float value) {
		data_ = new Float(value);
	}
	
	private Float data_;

	@Override
	public Type getType() {
		return PrimitiveType.FLOAT;
	}

	@Override
	public Object getNativeValue() {
		return data_;
	}

}
