package text;

public class StringScanner extends Scanner {

	public StringScanner(String text) {
		text_ = text;
		at_ = 0;
	}

	private final String text_;
	private int at_ = 0;

	@Override
	public char advance() {
		return at_ >= text_.length() ? Scanner.EOF : text_.charAt(at_);
	}

}