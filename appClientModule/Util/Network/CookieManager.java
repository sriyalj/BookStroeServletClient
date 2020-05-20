package Util.Network;

import java.util.ArrayList;
import java.util.HashMap;

public class CookieManager {
	
	private HashMap <String,HashMap <String,ArrayList <CookieStore> >> cookieStore;
	
	public CookieManager() {
		cookieStore = new HashMap();
	}
	
	public void setCookStore (String path, Cookie c, String Time ) {
		if (cookieStore.containsKey(path)) {
			HashMap Store = cookieStore.get(path);
			Store
		}
	}

}
