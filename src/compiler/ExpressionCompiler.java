package compiler;

import java.util.HashMap;

import lexer.Tag;

public class ExpressionCompiler extends Compiler {

	public ExpressionCompiler(CodeGenerator gen) {
		super(gen);
	}
	
	public boolean emitByTag(Tag tag) {
		if (correspond_.containsKey(tag)) {
			emit(correspond_.get(tag));
			return true;
		} else {
			return false;
		}
	}
	
	public void call(int index) {
		emit(OpCode.CALL, index);
	}
	
	public void loadArgument(int index) {
		emit(OpCode.LDARG, index);
	}
	
	public void loadLocal(int index) {
		emit(OpCode.LDLOC, index);
	}
	
	public void loadGlobal(int index) {
		emit(OpCode.LDGLOB, index);
	}
	
	public void loadStatic(int index) {
		emit(OpCode.LDSTATIC, index);
	}
	
	public void loadElement() {
		emit(OpCode.LDELEM);
	}
	
	public void storeArgument(int index) {
		emit(OpCode.STARG, index);
	}
	
	public void storeLocal(int index) {
		emit(OpCode.STLOC, index);
	}
	
	public void storeGlobal(int index) {
		emit(OpCode.STGLOB, index);
	}
	
	public void storeElement() {
		emit(OpCode.STELEM);
	}
	
	public Instruction branchFalse() {
		return emit(OpCode.BRFALSE);
	}
	
	private static final HashMap<Tag, OpCode> correspond_ =
			new HashMap<Tag, OpCode>();
	
	static {
		correspond_.put(Tag.OR, OpCode.OR);
		correspond_.put(Tag.AND, OpCode.AND);
		correspond_.put(Tag.BIT_OR, OpCode.BIT_OR);
		correspond_.put(Tag.BIT_XOR, OpCode.BIT_XOR);
		correspond_.put(Tag.BIT_AND, OpCode.BIT_AND);
		correspond_.put(Tag.SHL, OpCode.SHL);
		correspond_.put(Tag.SHR, OpCode.SHR);
		correspond_.put(Tag.SAR, OpCode.SAR);
		correspond_.put(Tag.ADD, OpCode.ADD);
		correspond_.put(Tag.SUB, OpCode.SUB);
		correspond_.put(Tag.MUL, OpCode.MUL);
		correspond_.put(Tag.DIV, OpCode.DIV);
		correspond_.put(Tag.MOD, OpCode.MOD);
		correspond_.put(Tag.EQ, OpCode.EQ);
		correspond_.put(Tag.NE, OpCode.NE);
		correspond_.put(Tag.LT, OpCode.LT);
		correspond_.put(Tag.GT, OpCode.GT);
		correspond_.put(Tag.LTE, OpCode.LTE);
		correspond_.put(Tag.GTE, OpCode.GTE);
		correspond_.put(Tag.NOT, OpCode.NOT);
		correspond_.put(Tag.BIT_NOT, OpCode.BIT_NOT);
	}

}
