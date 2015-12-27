package runtime;

public class RuntimeNull extends RuntimeObject {

	public RuntimeNull() {
		// TODO Auto-generated constructor stub
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
