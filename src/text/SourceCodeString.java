package text;

public class SourceCodeString extends SourceCode {
	
	public SourceCodeString(String src) {
		if (src == null)
			src = "";
		text_ = src;
	}

	@Override
	public char advance() {
		if (at_ < text_.length())
			return text_.charAt(at_);
		return (char) -1;
	}

	@Override
	public boolean eof() {
		return at_ >= text_.length();
	}
	
	private final String text_;
	private int at_;

}
