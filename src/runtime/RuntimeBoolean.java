package runtime;

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

}
