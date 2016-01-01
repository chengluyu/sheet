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
	// Branch (with one operand)
	BR,
	BRTRUE,
	BRFALSE,
	BREQ,
	BRNE,
	BRLT,
	BRGT,
	BRLTE,
	BRGTE,
	// Control flow
	CALL,
	RET,
	// Load
	LDARG,
	LDGLOB,
	LDLOC,
	LDSTATIC,
	// Store
	STARG,
	STGLOB,
	STLOC,
	// Stack control
	POP,
}
