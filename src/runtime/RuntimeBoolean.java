package runtime;

import utils.RuntimeError;

public class RuntimeBoolean extends RuntimeObject {

	public RuntimeBoolean(boolean value) {
		value_ = value;
	}
	
	private boolean value_;
	
	@Override
	public boolean isBoolean() {
		return true;
	}
	
	@Override
	public String toString() {
		return Boolean.toString(value_);
	}

	@Override
	public boolean isTruly() {
		return value_;
	}

	@Override
	public boolean isFalsy() {
		return value_;
	}

	@Override
	public RuntimeObjectType getType() {
		return RuntimeObjectType.BOOLEAN;
	}

	@Override
	public boolean equals(RuntimeObject rhs) {
		if (rhs instanceof RuntimeBoolean) {
			return value_ == ((RuntimeBoolean) rhs).value_;
		}
		return false;
	}

	@Override
	public boolean lessThan(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform comparison on a boolean");
	}

	@Override
	public boolean greatThan(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform comparison on a boolean");
	}

	@Override
	public boolean lessThanOrEqual(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform comparison on a boolean");
	}

	@Override
	public boolean greatThanOrEqual(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform comparison on a boolean");
	}

	@Override
	public RuntimeObject copy() {
		return new RuntimeBoolean(value_);
	}

	@Override
	public void requireType(RuntimeObjectType type) throws RuntimeError {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RuntimeObject add(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform addition on a boolean");
	}

	@Override
	public RuntimeObject subtract(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform subtraction on a boolean");
	}

	@Override
	public RuntimeObject multiply(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform multiplication on a boolean");
	}

	@Override
	public RuntimeObject divide(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform division on a boolean");
	}

	@Override
	public RuntimeObject modulus(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform modulus on a boolean");
	}

	@Override
	public RuntimeObject shl(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform left-shifting on a boolean");
	}

	@Override
	public RuntimeObject shr(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform right-shifting on a boolean");
	}

	@Override
	public RuntimeObject sar(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError(
				"cannot perform unsigned right-shifting on a boolean");
	}

	@Override
	public RuntimeObject and(RuntimeObject rhs) throws RuntimeError {
		return new RuntimeBoolean(value_ && rhs.isTruly());
	}

	@Override
	public RuntimeObject or(RuntimeObject rhs) throws RuntimeError {
		return new RuntimeBoolean(value_ || rhs.isTruly());
	}

	@Override
	public RuntimeObject xor(RuntimeObject rhs) throws RuntimeError {
		return new RuntimeBoolean(value_ ^ rhs.isTruly());
	}

	@Override
	public RuntimeObject negative() throws RuntimeError {
		return new RuntimeBoolean(!value_);
	}

	@Override
	public RuntimeObject not() throws RuntimeError {
		return new RuntimeBoolean(!value_);
	}

	@Override
	public RuntimeObject increase() throws RuntimeError {
		throw new RuntimeError("cannot perform increment on a boolean");
	}

	@Override
	public RuntimeObject decrease() throws RuntimeError {
		throw new RuntimeError("cannot perform decrement on a boolean");
	}

}
