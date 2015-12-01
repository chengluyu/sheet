package text;

public abstract class SourceCode {
	/**
	 * Extract next character in source code stream.
	 * @return the next character in source code stream, returns -1 if eof() is true
	 */
	public abstract char advance();
	
	/**
	 * Test if there is no more characters in source code stream.
	 * @return true if the stream is in the end
	 */
	public abstract boolean eof();
}
