package compiler;

public enum OpCode {
	NOP,
	// Arithmetic
	OR,
	AND,
	BIT_OR,
	BIT_XOR,
	BIT_AND,
	SHL,
	SHR,
	SAR,
	ADD,
	SUB,
	MUL,
	DIV,
	MOD,
	EQ,
	NE,
	LT,
	GT,
	LTE,
	GTE,
	NOT,
	BIT_NOT,
	// Branch (with one operand)
	BR(true),
	BRTRUE(true),
	BRFALSE(true),
	// Control flow
	CALL(true),
	RET,
	// Load
	LDARG(true),
	LDELEM,
	LDGLOB(true),
	LDLOC(true),
	LDSTATIC(true),
	LDNULL,
	// Store
	STARG(true),
	STGLOB(true),
	STLOC(true),
	STELEM,
	// Stack control
	POP;
	
	private OpCode() {
		this(false);
	}
	
	private OpCode(boolean hasOperand) {
		hasOperand_ = hasOperand;
	}
	
	private boolean hasOperand_;

	public boolean hasOperand() {
		return hasOperand_;
	}
}
