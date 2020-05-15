package Util;

import java.io.Serializable;

public class GeneralServerResponseMsgs extends ResponseMsgs implements Serializable{
	
	private String serverResponseCode;
	
	public GeneralServerResponseMsgs () {
		
	}
	
	public GeneralServerResponseMsgs (String serverResponseCode, String serverMsg) {
		super (serverMsg);
		this.serverResponseCode = serverResponseCode;
	}

	public String getServerResponseCode() {
		return serverResponseCode;
	}

	public void setServerResponseCode(String serverResponseCode) {
		this.serverResponseCode = serverResponseCode;
	}	

}
