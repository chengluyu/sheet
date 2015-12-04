package ast;

import type.Type;

public abstract class ValueNode extends Expression {
	
	public abstract Type getType();
	public abstract Object getNativeValue();

}
