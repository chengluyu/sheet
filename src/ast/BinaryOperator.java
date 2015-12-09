package ast;

public enum BinaryOperator {
	
	// Logical
	AND,
	OR,
	
	// Bitwise
	BIT_AND,
	BIT_OR,
	BIT_XOR,
	SHL,
	SHR,
	
	// Arithmetic
	ADD,
	SUB,
	MUL,
	DIV,
	MOD,
	
	// Comparative
	EQ,
	NE,
	LT,
	GT,
	LTE,
	GTE,
	
	// Assignment
	ASSIGN,
	ASSIGN_BIT_OR,
	ASSIGN_BIT_XOR,
	ASSIGN_BIT_AND,
	ASSIGN_SHL,
	ASSIGN_SHR,
	ASSIGN_ADD,
	ASSIGN_SUB,
	ASSIGN_MUL,
	ASSIGN_DIV,
	ASSIGN_MOD
	
}
