package utils;

public class UnimplementedError extends ParseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1623085571300712528L;

	public UnimplementedError(Position pos) {
		super(pos);
	}

	public UnimplementedError(Position pos, String message) {
		super(pos, message);
	}

	public UnimplementedError(Position pos, Throwable cause) {
		super(pos, cause);
	}

	public UnimplementedError(Position pos, String message, Throwable cause) {
		super(pos, message, cause);
	}

	public UnimplementedError(Position pos, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(pos, message, cause, enableSuppression, writableStackTrace);
	}

}
