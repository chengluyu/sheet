package compiler;

import java.util.ArrayList;
import java.util.Iterator;

public class ByteCode {

	public ByteCode(ArrayList<Instruction> codes) {
		byteCodes_ = codes;
	}
	
	private ArrayList<Instruction> byteCodes_;
	
	public Iterator<Instruction> iterator() {
		return byteCodes_.iterator();
	}
	
	public Instruction fetch(int i) {
		return byteCodes_.get(i);
	}
	
	public int instructionCount() {
		return byteCodes_.size();
	}

}
