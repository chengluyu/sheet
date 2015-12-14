package utils;

public class SyntaxError extends ParseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3986880002887599354L;

	public SyntaxError(Position pos) {
		super(pos);
	}

	public SyntaxError(Position pos, String message) {
		super(pos, message);
	}

	public SyntaxError(Position pos, Throwable cause) {
		super(pos, cause);
	}

	public SyntaxError(Position pos, String message,
			Throwable cause) {
		super(pos, message, cause);
	}

	public SyntaxError(Position pos, String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(pos, message, cause, enableSuppression, writableStackTrace);
	}

}