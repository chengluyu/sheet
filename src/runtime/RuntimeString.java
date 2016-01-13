package runtime;

public class RuntimeString extends RuntimeObject {

	public RuntimeString(String value) {
		value_ = value;
	}
	
	private String value_;

	@Override
	public String toString() {
		return value_;
	}
	
}
