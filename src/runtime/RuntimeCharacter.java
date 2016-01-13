package runtime;

public class RuntimeCharacter extends RuntimeObject {

	public RuntimeCharacter(char value) {
		value_ = value;
	}
	
	private char value_;
	
	@Override
	public boolean isCharacter() {
		return true;
	}

}
