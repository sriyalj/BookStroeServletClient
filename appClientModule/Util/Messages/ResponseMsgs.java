package Util.Messages;

import java.io.Serializable;

public class ResponseMsgs implements Serializable{
	
	private String Msg;
	
	ResponseMsgs () {		
	}
	
	ResponseMsgs (String Msg){
		this.Msg = Msg;
	}
		
	public String getMsg() {
		return Msg;
	}

	public void setMsg(String Msg) {
		this.Msg = Msg;
	}

}
