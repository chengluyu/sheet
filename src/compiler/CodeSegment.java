package compiler;

import java.util.ArrayList;
import java.util.Iterator;

public class CodeSegment {

	public CodeSegment(ArrayList<Instruction> code) {
		byteCodes_ = code;
	}
	
	private ArrayList<Instruction> byteCodes_;
	
	public Iterator<Instruction> iterator() {
		return byteCodes_.iterator();
	}

}
