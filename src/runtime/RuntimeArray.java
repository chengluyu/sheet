package runtime;

import java.util.ArrayList;
import java.util.Iterator;

import utils.RuntimeError;

public class RuntimeArray extends RuntimeObject {

	public RuntimeArray() {
		elems_ = null;
	}
	
	public RuntimeArray(ArrayList<RuntimeObject> elems) {
		elems_ = new ArrayList<RuntimeObject>();
		elems_.addAll(elems);
	}
	
	private ArrayList<RuntimeObject> elems_;
	
	public RuntimeObject get(RuntimeObject index) throws RuntimeError {
		if (index instanceof RuntimeInteger) {
			int i = ((RuntimeInteger) index).value();
			return elems_.get(i);
		} else {
			throw new RuntimeError("index must be an integer");
		}
	}
	
	public void set(RuntimeObject index, RuntimeObject value)
			throws RuntimeError {
		if (index instanceof RuntimeInteger) {
			int i = ((RuntimeInteger) index).value();
			elems_.set(i, value);
		} else {
			throw new RuntimeError("index must be an integer");
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		Iterator<? extends RuntimeObject> it = elems_.iterator();
		if (it.hasNext()) {
			sb.append(it.next().toString());
			while (it.hasNext()) {
				sb.append(", ");
				sb.append(it.next().toString());
			}
		}
		sb.append(']');
		return sb.toString();
	}
	
	@Override
	public boolean isArray() {
		return true;
	}

	@Override
	public boolean isTruly() {
		return true;
	}

	@Override
	public boolean isFalsy() {
		return false;
	}

	@Override
	public boolean equals(RuntimeObject that) {
		if (that instanceof RuntimeArray) {
			RuntimeArray rhs = (RuntimeArray) that; 
			if (elems_.size() != rhs.elems_.size())
				return false;
			for (int i = 0; i < elems_.size(); i++) {
				if (!elems_.get(i).equals(rhs.elems_.get(i)))
					return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean lessThan(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError(String.format(
				"cannot compare array with %s", rhs.getType().toString()));
	}

	@Override
	public boolean greatThan(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError(String.format(
				"cannot compare array with %s", rhs.getType().toString()));
	}

	@Override
	public boolean lessThanOrEqual(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError(String.format(
				"cannot compare array with %s", rhs.getType().toString()));
	}

	@Override
	public boolean greatThanOrEqual(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError(String.format(
				"cannot compare array with %s", rhs.getType().toString()));
	}

	@Override
	public RuntimeObjectType getType() {
		return RuntimeObjectType.ARRAY;
	}

	@SuppressWarnings("unchecked")
	@Override
	public RuntimeObject copy() {
		return new RuntimeArray((ArrayList<RuntimeObject>) elems_.clone());
	}

	@Override
	public void requireType(RuntimeObjectType type) throws RuntimeError {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RuntimeObject add(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform add on null value");
	}

	@Override
	public RuntimeObject subtract(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform subtract on null value");
	}

	@Override
	public RuntimeObject multiply(RuntimeObject rhs) throws RuntimeError {
		throw new RuntimeError("cannot perform throws on null value");
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
