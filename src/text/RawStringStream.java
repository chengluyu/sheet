package text;

public class RawStringStream extends RawStream {
	
	public RawStringStream(String src) {
		text_ = src == null ? "" : src;
		at_ = 0;
	}

	@Override
	public char advance() {
		if (at_ < text_.length())
			return text_.charAt(at_);
		return RawStream.EOF;
	}
	
	private final String text_;
	private int at_;

}
