package runtime;

import utils.RuntimeError;

public class RuntimeCharacter extends RuntimeObject {

	public RuntimeCharacter(char value) {
		value_ = value;
	}
	
	private char value_;
	
	@Override
	public boolean isCharacter() {
		return true;
	}

	@Override
	public boolean isTruly() {
		return value_ != '\0';
	}

	@Override
	public boolean isFalsy() {
		// TODO Auto-generated method stub
		return value_ == '\0';
	}

	@Override
	public RuntimeObject copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuntimeObjectType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void requireType(RuntimeObjectType type) throws RuntimeError {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean equals(RuntimeObject rhs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lessThan(RuntimeObject rhs) throws RuntimeError {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean greatThan(RuntimeObject rhs) throws RuntimeError {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lessThanOrEqual(RuntimeObject rhs) throws RuntimeError {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean greatThanOrEqual(RuntimeObject rhs) throws RuntimeError {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RuntimeObject add(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform add on null value");
	}

	@Override
	public RuntimeObject subtract(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform substract on null value");
	}

	@Override
	public RuntimeObject multiply(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform multiply on null value");
	}

	@Override
	public RuntimeObject divide(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform divide on null value");
	}

	@Override
	public RuntimeObject modulus(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform modulus on null value");
	}

	@Override
	public RuntimeObject shl(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform shl on null value");
	}

	@Override
	public RuntimeObject shr(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform shr on null value");
	}

	@Override
	public RuntimeObject sar(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform sar on null value");
	}

	@Override
	public RuntimeObject and(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform and on null value");
	}

	@Override
	public RuntimeObject or(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform or on null value");
	}

	@Override
	public RuntimeObject xor(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform xor on null value");
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
