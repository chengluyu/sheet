package runtime;

import utils.RuntimeError;

public class RuntimeNull extends RuntimeObject {

	public RuntimeNull() {
		
	}

	@Override
	public RuntimeString toRuntimeString() {
		return new RuntimeString("null");
	}
	
	@Override
	public boolean isNull() {
		return true;
	}

	@Override
	public boolean isTruly() {
		return false;
	}

	@Override
	public boolean isFalsy() {
		return true;
	}

	@Override
	public RuntimeObject copy() {
		return new RuntimeNull();
	}

	@Override
	public RuntimeObjectType getType() {
		return RuntimeObjectType.NULL;
	}

	@Override
	public void requireType(RuntimeObjectType type) throws RuntimeError {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean equals(RuntimeObject rhs) {
		return rhs instanceof RuntimeNull;
	}

	@Override
	public boolean lessThan(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform comparison on null value");
	}

	@Override
	public boolean greatThan(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform comparison on null value");
	}

	@Override
	public boolean lessThanOrEqual(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform comparison on null value");
	}

	@Override
	public boolean greatThanOrEqual(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform comparison on null value");
	}

	@Override
	public RuntimeObject add(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform addision on null value");
	}

	@Override
	public RuntimeObject subtract(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform substraction on null value");
	}

	@Override
	public RuntimeObject multiply(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform multiplication on null value");
	}

	@Override
	public RuntimeObject divide(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform division on null value");
	}

	@Override
	public RuntimeObject modulus(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform modulus on null value");
	}

	@Override
	public RuntimeObject shl(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform left-shifting on null value");
	}

	@Override
	public RuntimeObject shr(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform right-shifting on null value");
	}

	@Override
	public RuntimeObject sar(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError(
				"cannot perform unsigned right-shifting on null value");
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
		throw new RuntimeError("cannot perform negative on null value");
	}

	@Override
	public RuntimeObject not() throws RuntimeError {
		throw new RuntimeError("cannot perform not on null value");
	}

	@Override
	public RuntimeObject increase() throws RuntimeError {
		throw new RuntimeError("cannot perform increase on null value");
	}

	@Override
	public RuntimeObject decrease() throws RuntimeError {
		throw new RuntimeError("cannot perform decrease on null value");
	}

}
