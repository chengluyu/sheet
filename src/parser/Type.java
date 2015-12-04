package parser;

public abstract class Type {
	
	public abstract String getTypeName();
	
	public boolean isFunction() {
		return false;
	}
	
	public boolean isPrimitive() {
		return false;
	}
	
	public boolean isUserDefined() {
		return false;
	}
	
	public boolean isArray() {
		return false;
	}
	
	public boolean isNull() {
		return false;
	}
	
	public boolean isTuple() {
		return false;
	}

}
