package Util;

public class ResponseMsgs {
	
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
