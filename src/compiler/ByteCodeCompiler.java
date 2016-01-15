package compiler;

import java.util.ArrayList;

import ast.Module;
import runtime.RuntimeObject;

public class ByteCodeCompiler {

	public ByteCodeCompiler(Compiler compiler) {
		compiler_ = compiler;
		codes_ = new ArrayList<Instruction>();
	}
	
	private Compiler compiler_;
	private ArrayList<Instruction> codes_;
	
	public Module module() {
		return compiler_.module();
	}

	public ByteCode getByteCode() {
		return new ByteCode(codes_);
	}
	
	public void emit(OpCode opcode) {
		codes_.add(new Instruction(opcode));
	}
	
	public int position() {
		return codes_.size();
	}
	
	public void call(int index) {
		codes_.add(new Instruction(OpCode.CALL, index));
	}
	
	// branch
	
	public Blank branch() {
		Instruction ins = new Instruction(OpCode.BR);
		codes_.add(ins);
		return new Blank(ins);
	}
	
	public void branch(int operand) {
		codes_.add(new Instruction(OpCode.BR, operand));
	}
	
	public Blank branchTrue() {
		Instruction ins = new Instruction(OpCode.BRTRUE);
		codes_.add(ins);
		return new Blank(ins);
	}
	
	public Blank branchFalse() {
		Instruction ins = new Instruction(OpCode.BRFALSE);
		codes_.add(ins);
		return new Blank(ins);
	}
	
	// load
	
	public void loadArgument(int index) {
		codes_.add(new Instruction(OpCode.LDARG, index));
	}
	
	public void loadGlobal(int index) {
		codes_.add(new Instruction(OpCode.LDGLOB, index));
	}
	
	public void loadLocal(int index) {
		codes_.add(new Instruction(OpCode.LDLOC, index));
	}
	
	public void loadStatic(int index) {
		codes_.add(new Instruction(OpCode.LDSTATIC, index));
	}
	
	public void loadElement() {
		codes_.add(new Instruction(OpCode.LDELEM));
	}
	
	// store
	
	public void storeArgument(int index) {
		codes_.add(new Instruction(OpCode.STARG, index));
	}
	
	public void storeGlobal(int index) {
		codes_.add(new Instruction(OpCode.STGLOB, index));
	}
	
	public void storeLocal(int index) {
		codes_.add(new Instruction(OpCode.STLOC, index));
	}
	
	public void storeElement() {
		codes_.add(new Instruction(OpCode.STELEM));
	}

	public int addStatic(RuntimeObject obj) {
		return compiler_.addStatic(obj);
	}

	public void addEpilogueRet() {
		Instruction last = codes_.get(codes_.size() - 1);
		if (last.opcode() != OpCode.RET && last.opcode() != OpCode.RETNULL)
			codes_.add(new Instruction(OpCode.RETNULL));
	}

}
