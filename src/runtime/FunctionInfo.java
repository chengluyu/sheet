package runtime;

import java.util.ArrayList;
import java.util.Iterator;

import compiler.ByteCode;
import compiler.Instruction;

public class FunctionInfo {

	public FunctionInfo(
			int id,
			String name,
			FieldInfo[] arguments,
			FieldInfo[] locals,
			ByteCode code) {
		id_ = id;
		name_ = name;
		arguments_ = arguments;
		locals_ = locals;
		byteCode_ = code;
	}
	
	private int id_;
	private String name_;
	private FieldInfo[] arguments_;
	private FieldInfo[] locals_;
	private ByteCode byteCode_;
	
	public int argumentCount() {
		return arguments_.length;
	}
	
	public int localCount() {
		return locals_.length;
	}
	
	public Iterator<Instruction> iterator() {
		return byteCode_.iterator();
	}
	
	public String inspect() {
		StringBuilder sb = new StringBuilder();
		sb.append("function " + name_ + ":\n");
		sb.append("arguments (\n");
		for (int i = 0; i < arguments_.length; i++)
			sb.append(arguments_[i].inspect() + '\n');
		sb.append(")\n");
		sb.append("locals (\n");
		for (int i = 0; i < locals_.length; i++)
			sb.append(locals_[i].inspect() + '\n');
		sb.append(")\n");
		sb.append("instructions (\n");
		Iterator<Instruction> it = byteCode_.iterator();
		for (int i = 0; it.hasNext(); i++) {
			Instruction ins = it.next();
			sb.append(i);
			sb.append(": ");
			sb.append(ins.toString());
			sb.append('\n');
		}				
		sb.append(")\n");		
		return sb.toString();
	}

}
