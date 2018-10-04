package pt.com.hugodias.customer.exception;

public class InvalidHeadQuarters extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidHeadQuarters() {
	}

	public InvalidHeadQuarters(String message) {
		super(message);
	}

	public InvalidHeadQuarters(Throwable cause) {
		super(cause);
	}

	public InvalidHeadQuarters(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidHeadQuarters(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
