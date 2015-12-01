package token;

public enum Tag {
	// End of source indicator
	EOS,
	
	// Illegal token
	ILLEGAL,
	
	// Identifier
	IDENTIFIER,
	
	// Reserved words
	AS,
	CLASS,
	ENUM,
	EXPORT,
	FROM,
	IMPORT,
	MODULE,
	OF,
	PRIVATE,
	PROTECTED,
	PUBLIC,
	TYPE
}
