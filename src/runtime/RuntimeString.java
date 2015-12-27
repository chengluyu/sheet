package runtime;

public class RuntimeString extends RuntimeObject {
	
	public RuntimeString() {
		this("");
	}

	public RuntimeString(String value) {
		value_ = value;
	}
	
	private final String value_;

	@Override
	public String toString() {
		return value_;
	}
	
}
