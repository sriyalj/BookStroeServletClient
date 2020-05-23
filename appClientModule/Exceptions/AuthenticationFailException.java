package Exceptions;

public class AuthenticationFailException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthenticationFailException (String msg) {
		super (msg);
	}

}
