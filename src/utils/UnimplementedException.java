package utils;

public class UnimplementedException extends Exception {

	private static final long serialVersionUID = 1206466408433899554L;

	public UnimplementedException() {
	}

	public UnimplementedException(String message) {
		super(message);
	}

	public UnimplementedException(Throwable cause) {
		super(cause);
	}

	public UnimplementedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnimplementedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
