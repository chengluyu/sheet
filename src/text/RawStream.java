package text;

public abstract class RawStream {
	/**
	 * Extract next character in source code stream.
	 * @return the next character in source code stream, returns -1 if eof() is true
	 */
	public abstract char advance();
	
	public static final char EOF = (char) -1;
}
