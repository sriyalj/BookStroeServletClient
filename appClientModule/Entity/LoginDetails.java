package Entity;

import java.io.Serializable;

public class LoginDetails implements Serializable{
	private String userName;
	private String passWord;
	
	public LoginDetails (String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public LoginDetails () {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
