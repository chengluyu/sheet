package compiler;

import java.util.ArrayList;

import runtime.RuntimeObject;

public class CodeGenerator {

	public CodeGenerator() {
		byteCodes_ = new ArrayList<Instruction>();
		statics_ = new ArrayList<RuntimeObject>();
	}
	
	private ArrayList<Instruction> byteCodes_;
	private ArrayList<RuntimeObject> statics_;
	
	public void add(Instruction ins) {
		byteCodes_.add(ins);
	}
	
	public int newStatic(RuntimeObject obj) {
		int id = statics_.size();
		statics_.add(obj);
		return id;
	}
	
	public int nextPosition() {
		return byteCodes_.size();
	}
	
	public CodeSegment code() {
		return new CodeSegment(byteCodes_, statics_);
	}
	
	public ArrayList<RuntimeObject> statics() {
		return statics_;
	}

}
