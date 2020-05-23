package Util.Messages;

public class GeneralClientResponseMsgs extends ResponseMsgs {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static GeneralClientResponseMsgs con;
	
	private GeneralClientResponseMsgs () {		
	}
	
	public static GeneralClientResponseMsgs getConnection () {
		if (con == null) {
			con = new GeneralClientResponseMsgs ();
		}
		return con;
	}		
	
}
