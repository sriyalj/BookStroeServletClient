package PersistentObjects;

import Entity.UserProfile;

public class PersistentObjectList {
	
	private static PersistentObjectList con;
	private UserProfile UP;
	
	private PersistentObjectList () {
		
	}
	
	public static PersistentObjectList getConnection () {
		if (con == null) {
			con = new PersistentObjectList();
		}
		return con;
	}

	public UserProfile getUserProfile() {
		return UP;
	}

	public void setUserProfile(UserProfile uP) {
		UP = uP;
	}
	
	

}
