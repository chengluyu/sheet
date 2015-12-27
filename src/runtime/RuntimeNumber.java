package runtime;

public class RuntimeNumber extends RuntimeObject {

	public RuntimeNumber() {
		
	}
	
	public RuntimeNumber(double value) {
		
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

}
