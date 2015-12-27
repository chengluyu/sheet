package runtime;

public class RuntimeObject {
	
	// Properties
	
	public boolean isArray() {
		return false;
	}
	
	public boolean isBoolean() {
		return false;
	}
	
	public boolean isCharacter() {
		return false;
	}
	
	public boolean isInteger() {
		return false;
	}
	
	public boolean isNull() {
		return false;
	}
	
	public boolean isNumber() {
		return false;
	}
	
	public boolean isString() {
		return false;
	}
	
	// Methods
	
	public RuntimeString toRuntimeString() {
		return new RuntimeString(this.toString());
	}

}
