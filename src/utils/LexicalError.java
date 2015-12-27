package utils;

public class LexicalError extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = -460213305692924689L;

	public LexicalError(Position pos) {
		pos_ = pos;
	}

	public LexicalError(Position pos, String message) {
		super(message);
		pos_ = pos;
	}

	public LexicalError(Position pos, Throwable cause) {
		super(cause);
		pos_ = pos;
	}

	public LexicalError(Position pos, String message,
			Throwable cause) {
		super(message, cause);
		pos_ = pos;
	}

	public LexicalError(Position pos, String message,
			Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		pos_ = pos;
	}

	public Position position() {
		return pos_;
	}
	
	public String getMessage() {
		return String.format("%s %s", pos_.toString(), super.getMessage());
	}

	private Position pos_;

}