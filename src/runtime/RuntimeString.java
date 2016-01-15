package runtime;

import utils.RuntimeError;

public class RuntimeString extends RuntimeObject {

	public RuntimeString(String value) {
		value_ = value;
	}
	
	private String value_;

	@Override
	public String toString() {
		return value_;
	}

	@Override
	public boolean isTruly() {
		return value_.length() > 0;
	}

	@Override
	public boolean isFalsy() {
		return value_.length() == 0;
	}

	@Override
	public RuntimeObject copy() {
		return new RuntimeString(value_);
	}

	@Override
	public RuntimeObjectType getType() {
		return RuntimeObjectType.STRING;
	}

	@Override
	public void requireType(RuntimeObjectType type) throws RuntimeError {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean equals(RuntimeObject rhs) {
		if (rhs instanceof RuntimeString) {
			return value_.equals(((RuntimeString) rhs).value_);
		}
		return false;
	}

	@Override
	public boolean lessThan(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeString) {
			return value_.compareTo(((RuntimeString) rhs).value_) < 0;
		} else {
			throw new RuntimeError(String.format(
					"cannot perform comparision between string and %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public boolean greatThan(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeString) {
			return value_.compareTo(((RuntimeString) rhs).value_) > 0;
		} else {
			throw new RuntimeError(String.format(
					"cannot perform comparision between string and %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public boolean lessThanOrEqual(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeString) {
			return value_.compareTo(((RuntimeString) rhs).value_) <= 0;
		} else {
			throw new RuntimeError(String.format(
					"cannot perform comparision between string and %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public boolean greatThanOrEqual(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeString) {
			return value_.compareTo(((RuntimeString) rhs).value_) >= 0;
		} else {
			throw new RuntimeError(String.format(
					"cannot perform comparision between string and %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject add(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeString) {
			return new RuntimeString(value_ + ((RuntimeString) rhs).value_);
		} else {
			throw new RuntimeError(String.format(
					"cannot perform concatention between string and %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject subtract(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform substract on a string");
	}

	@Override
	public RuntimeObject multiply(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform multiplication on a string");
	}

	@Override
	public RuntimeObject divide(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform division on a string");
	}

	@Override
	public RuntimeObject modulus(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform modulus on a string");
	}

	@Override
	public RuntimeObject shl(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform left-shifting on a string");
	}

	@Override
	public RuntimeObject shr(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform right-shifting on a string");
	}

	@Override
	public RuntimeObject sar(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError(
				"cannot perform unsigned right-shifting on a string");
	}

	@Override
	public RuntimeObject and(RuntimeObject rhs) throws RuntimeError {
		return new RuntimeBoolean(isTruly() && rhs.isTruly());
	}

	@Override
	public RuntimeObject or(RuntimeObject rhs) throws RuntimeError {
		return new RuntimeBoolean(isTruly() || rhs.isTruly());
	}

	@Override
	public RuntimeObject xor(RuntimeObject rhs) throws RuntimeError {
		return new RuntimeBoolean(isTruly() ^ rhs.isTruly());
	}

	@Override
	public RuntimeObject negative() throws RuntimeError {
		throw new RuntimeError("cannot perform negation on a string");
	}

	@Override
	public RuntimeObject not() throws RuntimeError {
		throw new RuntimeError("cannot perform not on a string");
	}

	@Override
	public RuntimeObject increase() throws RuntimeError {
		throw new RuntimeError("cannot perform increment on a string");
	}

	@Override
	public RuntimeObject decrease() throws RuntimeError {
		throw new RuntimeError("cannot perform decrement on a string");
	}
	
}
