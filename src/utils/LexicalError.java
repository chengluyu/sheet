package utils;

public class LexicalError extends ParseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7804242313312015440L;

	public LexicalError(Position pos) {
		super(pos);
	}

	public LexicalError(Position pos, String message) {
		super(pos, message);
	}

	public LexicalError(Position pos, Throwable cause) {
		super(pos, cause);
	}

	public LexicalError(Position pos, String message,
			Throwable cause) {
		super(pos, message, cause);
	}

	public LexicalError(Position pos, String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(pos, message, cause, enableSuppression, writableStackTrace);
	}

}