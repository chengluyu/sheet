package utils;

public class TypeException extends Exception {

	public TypeException() {
	}

	public TypeException(String message) {
		super(message);
	}

	public TypeException(Throwable cause) {
		super(cause);
	}

	public TypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public TypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
