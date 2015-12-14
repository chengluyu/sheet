package utils;

public class ParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5958375279676071976L;

	public ParseException(Position pos) {
		pos_ = pos;
	}

	public ParseException(Position pos, String message) {
		super(message);
		pos_ = pos;
	}

	public ParseException(Position pos, Throwable cause) {
		super(cause);
		pos_ = pos;
	}

	public ParseException(Position pos, String message,
			Throwable cause) {
		super(message, cause);
		pos_ = pos;
	}

	public ParseException(Position pos, String message,
			Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		pos_ = pos;
	}

	public Position position() {
		return pos_;
	}

	private Position pos_;

}