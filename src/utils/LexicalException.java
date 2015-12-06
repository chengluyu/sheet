package utils;

public class LexicalException extends Exception {

	private static final long serialVersionUID = -1928864996412528026L;

	public LexicalException() {
	}

	public LexicalException(String message) {
		super(message);
	}

	public LexicalException(Throwable cause) {
		super(cause);
	}

	public LexicalException(String message, Throwable cause) {
		super(message, cause);
	}

	public LexicalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
