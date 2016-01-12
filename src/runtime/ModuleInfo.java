package runtime;

import compiler.CodeSegment;

public class ModuleInfo {

	public ModuleInfo() {
		// TODO Auto-generated constructor stub
	}
	
	private FunctionInfo[] functions_;
	private FieldInfo[] globals_;
	
	private FunctionInfo entryPoint_;
	private CodeSegment prelogue_;
	
	public FunctionInfo entryPoint() {
		return entryPoint_;
	}
	
	public CodeSegment prelogue() {
		return prelogue_;
	}
	
	public FunctionInfo getFunctionByIndex(int i) {
		return functions_[i];
	}
	
	public int getGlobalFieldCount() {
		return globals_.length;
	}

}
