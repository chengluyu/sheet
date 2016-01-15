package runtime;

import utils.RuntimeError;

public class RuntimeNumber extends RuntimeObject {
	
	public RuntimeNumber(double value) {
		value_ = value;
	}
	
	private double value_;
	
	@Override
	public String toString() {
		return Double.toString(value_);
	}

	@Override
	public RuntimeString toRuntimeString() {
		return new RuntimeString(toString());
	}
	
	@Override
	public boolean isNumber() {
		return true;
	}

	@Override
	public boolean isTruly() {
		return value_ != 0.0;
	}

	@Override
	public boolean isFalsy() {
		return value_ == 0.0;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuntimeObject subtract(RuntimeObject rhs) throws RuntimeError {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuntimeObject multiply(RuntimeObject rhs) throws RuntimeError {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuntimeObject divide(RuntimeObject rhs) throws RuntimeError {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuntimeObject modulus(RuntimeObject rhs) throws RuntimeError {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuntimeObject shl(RuntimeObject rhs) throws RuntimeError {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuntimeObject shr(RuntimeObject rhs) throws RuntimeError {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuntimeObject sar(RuntimeObject rhs) throws RuntimeError {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuntimeObject and(RuntimeObject rhs) throws RuntimeError {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuntimeObject or(RuntimeObject rhs) throws RuntimeError {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuntimeObject xor(RuntimeObject rhs) throws RuntimeError {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuntimeObject negative() throws RuntimeError {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuntimeObject not() throws RuntimeError {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuntimeObject increase() throws RuntimeError {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuntimeObject decrease() throws RuntimeError {
		// TODO Auto-generated method stub
		return null;
	}

}
