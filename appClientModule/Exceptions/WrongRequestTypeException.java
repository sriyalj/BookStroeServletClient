package Exceptions;

public class WrongRequestTypeException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongRequestTypeException (String msg) {
		super (msg);
	}

}
