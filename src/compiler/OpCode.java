package compiler;

public enum OpCode {
	// placeholder
	NOP,
	// arithmetic opcodes
	ADD,
	SUB,
	MUL,
	DIV,
	MOD,
	// bitwise operators
	SHL,
	SHR,
	SAR,
	BIT_AND,
	BIT_OR,
	BIT_XOR,
	// branch operators
	BR,
	// compare operators
	EQ,
	NE,
	GT,
	LT,
	GTE,
	LTE,
	// stack operations
	LDARG,
	LDLOC,
	STARG,
	STLOC,
	
	
	;
	
	private OpCode() {
		literal_ = this.name().toLowerCase();
	}
	
	private OpCode(String literal) {
		literal_ = literal;
	}
	
	private String literal_;
	
	// Properties
	
	public int code() {
		return this.ordinal();
	}
	
	public String literal() {
		return literal_;
	}
	
}
