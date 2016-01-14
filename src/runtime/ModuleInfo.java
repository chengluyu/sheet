package runtime;

import compiler.CodeSegment;

public class ModuleInfo {

	public ModuleInfo(FunctionInfo[] functions,
			FieldInfo[] globals,
			FunctionInfo entryPoint,
			CodeSegment prelogue) {
		functions_ = functions;
		globals_ = globals;
		entryPoint_ = entryPoint;
		prelogue_ = prelogue;
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
