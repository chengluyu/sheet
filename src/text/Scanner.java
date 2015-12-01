package text;

public class Scanner {
	
	public Scanner(SourceCode src) {
		src_ = src;
		peek_ = src_.advance();
	}
	
	public boolean end() {
		return end_;
	}
	
	public char peek() {
		return peek_;
	}
	
	/**
	 * Extract the 
	 * @return
	 */
	public char next() {
		if ()
	}
	
	/**
	 * Simply ignores the next character.
	 */
	public void ignore() {
		peek_ = src_.advance();
	}
	
	/**
	 * If next character in the stream is
	 * @param ch
	 * @return
	 */
	public boolean match(char ch) {
		if (peek_ == ch) {
			ignore();
			return true;
		}
		return false;
	}
	
	public int ignore(int v) {
		return v;
	}
	
	private void advance() {
		if (src_.eof()) {
			end_ = true;
		}
	}
	
	private boolean end_;
	private char peek_;
	private SourceCode src_;
	
	private static final char EOF = (char) -1;
}
