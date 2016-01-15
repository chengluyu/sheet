package runtime;

import utils.RuntimeError;

public abstract class RuntimeObject {
	
	// Properties
	
	public boolean isArray() {
		return false;
	}
	
	public boolean isBoolean() {
		return false;
	}
	
	public boolean isCharacter() {
		return false;
	}
	
	public boolean isInteger() {
		return false;
	}
	
	public boolean isNull() {
		return false;
	}
	
	public boolean isNumber() {
		return false;
	}
	
	public boolean isString() {
		return false;
	}
	
	public abstract RuntimeObject copy();
	public abstract RuntimeObjectType getType();
	public abstract void requireType(RuntimeObjectType type)
			throws RuntimeError;
	
	public abstract boolean isTruly();
	public abstract boolean isFalsy();
	
	public abstract boolean equals(RuntimeObject rhs);
	public abstract boolean lessThan(RuntimeObject rhs) throws RuntimeError;
	public abstract boolean greatThan(RuntimeObject rhs) throws RuntimeError;
	public abstract boolean lessThanOrEqual(RuntimeObject rhs)
			throws RuntimeError;
	public abstract boolean greatThanOrEqual(RuntimeObject rhs)
			throws RuntimeError;
	
	public abstract RuntimeObject add(RuntimeObject rhs) throws RuntimeError;
	public abstract RuntimeObject subtract(RuntimeObject rhs)
			throws RuntimeError;
	public abstract RuntimeObject multiply(RuntimeObject rhs)
			throws RuntimeError;
	public abstract RuntimeObject divide(RuntimeObject rhs)
			throws RuntimeError;
	public abstract RuntimeObject modulus(RuntimeObject rhs)
			throws RuntimeError;
	public abstract RuntimeObject shl(RuntimeObject rhs) throws RuntimeError;
	public abstract RuntimeObject shr(RuntimeObject rhs) throws RuntimeError;
	public abstract RuntimeObject sar(RuntimeObject rhs) throws RuntimeError;
	public abstract RuntimeObject and(RuntimeObject rhs) throws RuntimeError;
	public abstract RuntimeObject or(RuntimeObject rhs) throws RuntimeError;
	public abstract RuntimeObject xor(RuntimeObject rhs) throws RuntimeError;
	public abstract RuntimeObject negative() throws RuntimeError;
	public abstract RuntimeObject not() throws RuntimeError;
	public abstract RuntimeObject increase() throws RuntimeError;
	public abstract RuntimeObject decrease() throws RuntimeError;
	
	// Methods
	
	public RuntimeString toRuntimeString() {
		return new RuntimeString(this.toString());
	}

}
