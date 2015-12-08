package ast;

import type.PrimitiveType;
import type.Type;

public class CharValueNode extends ValueNode {

	public CharValueNode(char ch) {
		data_ = ch;
	}
	
	private char data_;

	@Override
	public Type getType() {
		return PrimitiveType.CHAR;
	}

	@Override
	public Object getNativeValue() {
		return new Character(data_);
	}

}
