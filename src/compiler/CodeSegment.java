package compiler;

import java.util.ArrayList;
import java.util.Iterator;

import runtime.RuntimeObject;

public class CodeSegment {

	public CodeSegment(ArrayList<Instruction> code,
			ArrayList<RuntimeObject> statics) {
		byteCodes_ = code;
		statics_ = statics;
	}
	
	private ArrayList<Instruction> byteCodes_;
	private ArrayList<RuntimeObject> statics_;
	
	public Iterator<Instruction> iterator() {
		return byteCodes_.iterator();
	}
	
	public ArrayList<RuntimeObject> statics() {
		return statics_;
	}

}
