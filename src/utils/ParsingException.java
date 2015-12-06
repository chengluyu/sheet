package utils;

public class ParsingException extends Exception {

	private static final long serialVersionUID = -3520241692994365385L;

	public ParsingException() {
	}

	public ParsingException(String message) {
		super(message);
	}

	public ParsingException(Throwable cause) {
		super(cause);
	}

	public ParsingException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
