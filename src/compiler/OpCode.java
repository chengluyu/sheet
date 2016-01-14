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
	LDELEM,
	LDGLOB,
	LDLOC,
	LDSTATIC,
	LDNULL,
	// Store
	STARG,
	STGLOB,
	STLOC,
	STELEM,
	// Stack control
	POP
}
