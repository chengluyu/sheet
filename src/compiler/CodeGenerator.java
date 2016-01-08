package compiler;

import java.util.ArrayList;

public class CodeGenerator {

	public CodeGenerator() {
		
	}
	
	private ArrayList<Instruction> byteCodes_;
	
	
	
	private CodeSegment code() {
		return new CodeSegment(byteCodes_);
	}

}
