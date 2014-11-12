package assignment3;

/**
 * ExchangeFailedException is thrown when an exchange response contains an error
 */
public class ExchangeFailedException extends Exception {

	public ExchangeFailedException() {}

	public ExchangeFailedException(String message) {
		super(message);
	}

	public ExchangeFailedException(Throwable cause) {
		super(cause);
	}

	public ExchangeFailedException(String message, Throwable cause) {
		super(message, cause);
	}

}
