package Entity;

import java.io.Serializable;

public class UserProfile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String passWord;
	
	public UserProfile (String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public UserProfile () {
		
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
