package compiler;

public enum OpCode {
	NOP,
	// Arithmetic
	OR,
	AND,
	XOR,
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
	INC,
	DEC,
	NEG,
	// Branch (with one operand)
	BR(true),
	BRTRUE(true),
	BRFALSE(true),
	// Control flow
	CALL(true),
	RET,
	RETNULL,
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
	COPY,
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
