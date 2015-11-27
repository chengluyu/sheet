package text;

public class Scanner {
	
	public Scanner(SourceCode src) {
		src_ = src;
		peek_ = src_.advance();
	}
	
	private char peek_;
	private SourceCode src_;
}
