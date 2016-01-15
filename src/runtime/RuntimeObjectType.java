package runtime;

public enum RuntimeObjectType {
	ARRAY,
	BOOLEAN,
	CHARACTER,
	INTEGER,
	NULL,
	NUMBER,
	STRING;
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}
	
}
