package runtime;

import utils.RuntimeError;

public class RuntimeInteger extends RuntimeObject {

	public RuntimeInteger(int value) {
		value_ = value;
	}
	
	private int value_;
	
	public int value() {
		return value_;
	}
	
	@Override
	public boolean isInteger() {
		return true;
	}

	@Override
	public boolean isTruly() {
		return value_ != 0;
	}

	@Override
	public boolean isFalsy() {
		return value_ == 0;
	}

	@Override
	public RuntimeObject copy() {
		return new RuntimeInteger(value_);
	}

	@Override
	public RuntimeObjectType getType() {
		return RuntimeObjectType.INTEGER;
	}

	@Override
	public void requireType(RuntimeObjectType type) throws RuntimeError {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean equals(RuntimeObject rhs) {
		if (rhs instanceof RuntimeNumber) {
			return value_ == (int) ((RuntimeNumber) rhs).value();
		} else if (rhs instanceof RuntimeInteger) {
			return value_ == ((RuntimeInteger) rhs).value();
		}
		return false;
	}

	@Override
	public boolean lessThan(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			return Double.compare(value_, ((RuntimeNumber) rhs).value()) < 0;
		} else if (rhs instanceof RuntimeInteger) {
			return value_ < ((RuntimeInteger) rhs).value();
		} else {
			throw new RuntimeError(String.format(
					"cannot compare a integer with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public boolean greatThan(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			return Double.compare(value_, ((RuntimeNumber) rhs).value()) > 0;
		} else if (rhs instanceof RuntimeInteger) {
			return value_ > ((RuntimeInteger) rhs).value();
		} else {
			throw new RuntimeError(String.format(
					"cannot compare a integer with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public boolean lessThanOrEqual(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			return Double.compare(value_, ((RuntimeNumber) rhs).value()) <= 0;
		} else if (rhs instanceof RuntimeInteger) {
			return value_ <= ((RuntimeInteger) rhs).value();
		} else {
			throw new RuntimeError(String.format(
					"cannot compare a integer with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public boolean greatThanOrEqual(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			return Double.compare(value_, ((RuntimeNumber) rhs).value()) > 0;
		} else if (rhs instanceof RuntimeInteger) {
			return value_ >= ((RuntimeInteger) rhs).value();
		} else {
			throw new RuntimeError(String.format(
					"cannot compare a integer with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject add(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			return new RuntimeNumber(value_ + ((RuntimeNumber) rhs).value());
		} else if (rhs instanceof RuntimeInteger) {
			return new RuntimeInteger(value_ + ((RuntimeInteger) rhs).value());
		} else {
			throw new RuntimeError(String.format(
					"cannot compare a integer with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject subtract(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			return new RuntimeNumber(value_ - ((RuntimeNumber) rhs).value());
		} else if (rhs instanceof RuntimeInteger) {
			return new RuntimeInteger(value_ - ((RuntimeInteger) rhs).value());
		} else {
			throw new RuntimeError(String.format(
					"cannot compare a integer with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject multiply(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			return new RuntimeNumber(value_ * ((RuntimeNumber) rhs).value());
		} else if (rhs instanceof RuntimeInteger) {
			return new RuntimeInteger(value_ * ((RuntimeInteger) rhs).value());
		} else {
			throw new RuntimeError(String.format(
					"cannot compare a integer with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject divide(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			return new RuntimeNumber(value_ / ((RuntimeNumber) rhs).value());
		} else if (rhs instanceof RuntimeInteger) {
			return new RuntimeInteger(value_ / ((RuntimeInteger) rhs).value());
		} else {
			throw new RuntimeError(String.format(
					"cannot compare a integer with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject modulus(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			return new RuntimeNumber(value_ % ((RuntimeNumber) rhs).value());
		} else if (rhs instanceof RuntimeInteger) {
			return new RuntimeInteger(value_ % ((RuntimeInteger) rhs).value());
		} else {
			throw new RuntimeError(String.format(
					"cannot compare a integer with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject shl(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			return new RuntimeInteger(value_ << (int) ((RuntimeNumber) rhs).value());
		} else if (rhs instanceof RuntimeInteger) {
			return new RuntimeInteger(value_ << ((RuntimeInteger) rhs).value());
		} else {
			throw new RuntimeError(String.format(
					"cannot perform left-shifting a integer with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject shr(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			return new RuntimeInteger(value_ >> (int) ((RuntimeNumber) rhs).value());
		} else if (rhs instanceof RuntimeInteger) {
			return new RuntimeInteger(value_ >> ((RuntimeInteger) rhs).value());
		} else {
			throw new RuntimeError(String.format(
					"cannot perform right-shifting a integer with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject sar(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			return new RuntimeInteger(value_ >>> (int) ((RuntimeNumber) rhs).value());
		} else if (rhs instanceof RuntimeInteger) {
			return new RuntimeInteger(value_ >>> ((RuntimeInteger) rhs).value());
		} else {
			throw new RuntimeError(String.format(
					"cannot perform unsigned right-shifting a integer with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject and(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			return new RuntimeInteger(value_ & (int) ((RuntimeNumber) rhs).value());
		} else if (rhs instanceof RuntimeInteger) {
			return new RuntimeInteger(value_ & ((RuntimeInteger) rhs).value());
		} else {
			throw new RuntimeError(String.format(
					"cannot perform and between a integer with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject or(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			return new RuntimeInteger(value_ | (int) ((RuntimeNumber) rhs).value());
		} else if (rhs instanceof RuntimeInteger) {
			return new RuntimeInteger(value_ | ((RuntimeInteger) rhs).value());
		} else {
			throw new RuntimeError(String.format(
					"cannot perform or between a integer with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject xor(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			return new RuntimeInteger(value_ ^ (int) ((RuntimeNumber) rhs).value());
		} else if (rhs instanceof RuntimeInteger) {
			return new RuntimeInteger(value_ ^ ((RuntimeInteger) rhs).value());
		} else {
			throw new RuntimeError(String.format(
					"cannot perform exclusive-or between a integer with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject negative() throws RuntimeError {
		return new RuntimeInteger(-value_);
	}

	@Override
	public RuntimeObject not() throws RuntimeError {
		return new RuntimeInteger(~value_);
	}

	@Override
	public RuntimeObject increase() throws RuntimeError {
		return new RuntimeInteger(value_ + 1);
	}

	@Override
	public RuntimeObject decrease() throws RuntimeError {
		return new RuntimeInteger(value_ - 1);
	}

	@Override
	public String toString() {
		return Integer.toString(value_);
	}

}
