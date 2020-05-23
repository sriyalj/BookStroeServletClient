package PersistentObjects;

import Entity.AutheticationData;

public class PersistentObjectList {
	
	private static PersistentObjectList con;
	private AutheticationData UP;
	
	private PersistentObjectList () {
		
	}
	
	public static PersistentObjectList getConnection () {
		if (con == null) {
			con = new PersistentObjectList();
		}
		return con;
	}

	public AutheticationData getUserProfile() {
		return UP;
	}

	public void setUserProfile(AutheticationData uP) {
		UP = uP;
	}
	
	

}
