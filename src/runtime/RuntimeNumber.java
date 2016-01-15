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
		return new RuntimeNumber(value_);
	}

	@Override
	public RuntimeObjectType getType() {
		return RuntimeObjectType.NUMBER;
	}

	@Override
	public void requireType(RuntimeObjectType type) throws RuntimeError {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean equals(RuntimeObject rhs) {
		if (rhs instanceof RuntimeNumber) {
			return value_ == ((RuntimeNumber) rhs).value_;
		} else if (rhs instanceof RuntimeInteger) {
			return value_ == ((RuntimeInteger) rhs).value();
		}
		return false;
	}

	@Override
	public boolean lessThan(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			return Double.compare(value_, ((RuntimeNumber) rhs).value_) < 0;
		} else if (rhs instanceof RuntimeInteger) {
			return Double.compare(value_, ((RuntimeInteger) rhs).value()) < 0;
		} else {
			throw new RuntimeError(String.format(
					"cannot compare a number with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public boolean greatThan(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			return Double.compare(value_, ((RuntimeNumber) rhs).value_) > 0;
		} else if (rhs instanceof RuntimeInteger) {
			return Double.compare(value_, ((RuntimeInteger) rhs).value()) > 0;
		} else {
			throw new RuntimeError(String.format(
					"cannot compare a number with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public boolean lessThanOrEqual(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			return Double.compare(value_, ((RuntimeNumber) rhs).value_) <= 0;
		} else if (rhs instanceof RuntimeInteger) {
			return Double.compare(value_, ((RuntimeInteger) rhs).value()) <= 0;
		} else {
			throw new RuntimeError(String.format(
					"cannot compare a number with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public boolean greatThanOrEqual(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			return Double.compare(value_, ((RuntimeNumber) rhs).value_) >= 0;
		} else if (rhs instanceof RuntimeInteger) {
			return Double.compare(value_, ((RuntimeInteger) rhs).value()) >= 0;
		} else {
			throw new RuntimeError(String.format(
					"cannot compare a number with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject add(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			double value = ((RuntimeNumber) rhs).value_;
			return new RuntimeNumber(value_ + value);
		} else if (rhs instanceof RuntimeInteger) {
			double value = ((RuntimeInteger) rhs).value();
			return new RuntimeNumber(value_ + value);
		} else {
			throw new RuntimeError(String.format(
					"cannot add a number with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject subtract(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			double value = ((RuntimeNumber) rhs).value_;
			return new RuntimeNumber(value_ - value);
		} else if (rhs instanceof RuntimeInteger) {
			double value = ((RuntimeInteger) rhs).value();
			return new RuntimeNumber(value_ - value);
		} else {
			throw new RuntimeError(String.format(
					"cannot subtract a number with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject multiply(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			double value = ((RuntimeNumber) rhs).value_;
			return new RuntimeNumber(value_ * value);
		} else if (rhs instanceof RuntimeInteger) {
			double value = ((RuntimeInteger) rhs).value();
			return new RuntimeNumber(value_ * value);
		} else {
			throw new RuntimeError(String.format(
					"cannot multiply a number with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject divide(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			double value = ((RuntimeNumber) rhs).value_;
			return new RuntimeNumber(value_ / value);
		} else if (rhs instanceof RuntimeInteger) {
			double value = ((RuntimeInteger) rhs).value();
			return new RuntimeNumber(value_ / value);
		} else {
			throw new RuntimeError(String.format(
					"cannot divide a number with a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject modulus(RuntimeObject rhs) throws RuntimeError {
		if (rhs instanceof RuntimeNumber) {
			double value = ((RuntimeNumber) rhs).value_;
			return new RuntimeNumber(value_ % value);
		} else if (rhs instanceof RuntimeInteger) {
			double value = ((RuntimeInteger) rhs).value();
			return new RuntimeNumber(value_ % value);
		} else {
			throw new RuntimeError(String.format(
					"cannot compute modulus between a number and a %s",
					rhs.getType().toString()));
		}
	}

	@Override
	public RuntimeObject shl(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError(
				"cannot perform left-shifting on a floating-point number");
	}

	@Override
	public RuntimeObject shr(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError(
				"cannot perform right-shifting on a floating-point number");
	}

	@Override
	public RuntimeObject sar(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError(
				"cannot perform unsigned right-shifting on a floating-point number");
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
		return new RuntimeNumber(-value_);
	}

	@Override
	public RuntimeObject not() throws RuntimeError {
		throw new RuntimeError(
				"cannot perform not on a floating-point number");
	}

	@Override
	public RuntimeObject increase() throws RuntimeError {
		return new RuntimeNumber(value_ + 1.0);
	}

	@Override
	public RuntimeObject decrease() throws RuntimeError {
		return new RuntimeNumber(value_ - 1.0);
	}

	public double value() {
		return value_;
	}

}
