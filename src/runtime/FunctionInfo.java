package runtime;

import java.util.Iterator;

import compiler.CodeSegment;
import compiler.Instruction;

public class FunctionInfo {

	public FunctionInfo(
			FieldInfo[] arguments,
			FieldInfo[] locals,
			CodeSegment code) {
		arguments_ = arguments;
		locals_ = locals;
		byteCode_ = code;
	}
	
	private FieldInfo[] arguments_;
	private FieldInfo[] locals_;
	private CodeSegment byteCode_;
	
	public int argumentCount() {
		return arguments_.length;
	}
	
	public int localCount() {
		return locals_.length;
	}
	
	public Iterator<Instruction> iterator() {
		return byteCode_.iterator();
	}

}
