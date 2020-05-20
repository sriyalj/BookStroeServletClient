package Util.Network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CookieManager {
	
	private static HashMap <String,HashMap <String,ArrayList <Cookie> >> cookieStore;
	private static CookieManager con;
	
	private CookieManager() {
		cookieStore = new HashMap();		
	}
	
	public static CookieManager getConnection () {
		if (con == null) {
			con = new CookieManager();
		}
		
		return con;
	}
	
	
	public void addCookie (String path, Cookie c, String time) {
		if (!cookieStore.containsKey(path)) {
			HashMap store = new HashMap () ;
			ArrayList <Cookie> cookies = new ArrayList();
			cookies.add(c);
			store.put(time, cookies);
			cookieStore.put(path, store);
		}
		else {
			HashMap store = cookieStore.get(path);
			ArrayList <Cookie> cookies = (ArrayList<Cookie>) store.get(time);
			cookies.add(c);
			store.put(time, cookies);
			cookieStore.put(path, store);
		}
	}
	
	public void getCookies (String path) {
		HashMap store = cookieStore.get(path);		
				
		Iterator<Map.Entry<String, ArrayList>> entries = store.entrySet().iterator();
		while (entries.hasNext()) {
		    Map.Entry<String, ArrayList> entry = entries.next();
		    System.out.println("Key = " + entry.getKey());
		    ArrayList <Cookie >cookies = entry.getValue();
		    
		    for (Cookie c : cookies) {
		    	System.out.println (c.getName());
		    	System.out.println (c.getValue());
		    	System.out.println (c.getMaxAge());
		    	
		    }
		}
	}

}
