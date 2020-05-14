package Util;

public class GeneralClientResponseMsgs extends ResponseMsgs {	
	
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
