package runtime;

public class RuntimeNull extends RuntimeObject {

	public RuntimeNull() {
		
	}

	@Override
	public RuntimeString toRuntimeString() {
		return new RuntimeString("null");
	}
	
	@Override
	public boolean isNull() {
		return true;
	}

}
