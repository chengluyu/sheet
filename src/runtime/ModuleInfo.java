package runtime;

import java.util.Iterator;

import compiler.ByteCode;
import compiler.Instruction;
import compiler.StaticPool;

public class ModuleInfo {

	public ModuleInfo(
			StaticPool staticPool,
			FieldInfo[] globals,
			ByteCode prologue,
			FunctionInfo[] functions,
			FunctionInfo entryPoint
			) {
		functions_ = functions;
		globals_ = globals;
		entryPoint_ = entryPoint;
		prologue_ = prologue;
		staticPool_ = staticPool;
	}
	
	private FunctionInfo[] functions_;
	private FieldInfo[] globals_;
	
	private FunctionInfo entryPoint_;
	private ByteCode prologue_;
	private StaticPool staticPool_;
	
	public FunctionInfo entryPoint() {
		return entryPoint_;
	}
	
	public ByteCode prologue() {
		return prologue_;
	}
	
	public StaticPool staticPool() {
		return staticPool_;
	}
	
	public FunctionInfo getFunctionByIndex(int i) {
		return functions_[i];
	}
	
	public int getGlobalFieldCount() {
		return globals_.length;
	}
	
	public String inspect() {
		StringBuilder sb = new StringBuilder();
		sb.append("globals (\n");
		for (int i = 0; i < globals_.length; i++)
			sb.append(globals_[i].inspect() + '\n');
		sb.append(")\n");
		sb.append("prelogue (\n");
		Iterator<Instruction> it = prologue_.iterator();
		for (int i = 0; it.hasNext(); i++) {
			Instruction ins = it.next();
			sb.append(i);
			sb.append(": ");
			sb.append(ins.toString());
			sb.append('\n');
		}				
		sb.append(")\n");		
		sb.append("functions {\n");
		for (int i = 0; i < functions_.length; i++)
			sb.append(functions_[i].inspect() + '\n');
		sb.append("}\n");
		return sb.toString();
	}

}
