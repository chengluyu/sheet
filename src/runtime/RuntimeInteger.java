package runtime;

public class RuntimeInteger extends RuntimeObject {

	public RuntimeInteger(int value) {
		value_ = value;
	}
	
	private int value_;
	
	@Override
	public boolean isInteger() {
		return true;
	}

}
