package text;

public class Scanner {
	
	public Scanner(RawStream stream) {
		stream_ = stream;
		advance();
	}
	
	public char peek() {
		return peek_;
	}
	
	/**
	 * Extract the 
	 * @return
	 */
	public char next() {
		char c = peek_;
		advance();
		return c;
	}
	
	/**
	 * Simply ignores the next character.
	 */
	public void ignore() {
		advance();
	}
	
	/**
	 * If next character in the stream is
	 * @param ch
	 * @return
	 */
	public boolean match(char ch) {
		if (peek_ == ch) {
			advance();
			return true;
		}
		return false;
	}
	
	/**
	 * Extract next character and set end flag if the stream meets an EOF.
	 */
	private void advance() {
		if (!end_) {
			peek_ = stream_.advance();
			if (peek_ == RawStream.EOF)
				end_ = true;
		}
	}
	
	private boolean end_;
	private char peek_;
	private RawStream stream_;
	
	public static final char EOF = RawStream.EOF;
}
