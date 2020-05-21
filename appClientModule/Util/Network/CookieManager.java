package Util.Network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.net.HttpCookie;

public class CookieManager {
	
	private static HashMap <String,HashMap <String,ArrayList <HttpCookie> >> cookieStore;
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
	
	
	public void addCookie (String path, HttpCookie c, String time) {
		if (cookieStore.containsKey(path)) {			
			HashMap store = cookieStore.get(path);
			ArrayList <HttpCookie> cookies = (ArrayList<HttpCookie>) store.get(time);
			
			if (cookies == null) {
				cookies = new ArrayList();
			}
			cookies.add(c);
			store.put(time, cookies);
			cookieStore.put(path, store);			
		}
		else {
			HashMap store = new HashMap () ;
			ArrayList <HttpCookie> cookies = new ArrayList();
			cookies.add(c);
			store.put(time, cookies);
			cookieStore.put(path, store);
		}
		
		//getAllCookies();
	}
	
	public void getCookiesAllByPath (String path) {
		ArrayList <HttpCookie> cookieList = new ArrayList ();
		HashMap store = cookieStore.get(path);		
		System.out.println("PAth = " + path);		
		Iterator<Map.Entry<String, ArrayList>> entries = store.entrySet().iterator();
		
		
		while (entries.hasNext()) {
		    Map.Entry<String, ArrayList> entry = entries.next();
		    System.out.println("Key = " + entry.getKey());
		    ArrayList <HttpCookie >cookies = entry.getValue();
		    
		    for (HttpCookie c : cookies) {
		    	System.out.println (c.getName() + " " +  c.getValue());
		    	System.out.println (c.getMaxAge());
		    	
		    }
		    System.out.println (" ");
		}
	}
	
	public void getAllValidCookiesForPath (String path) {
		
	}
	
	public void getAllCookies () {
		
		for ( Entry<String, HashMap<String, ArrayList<HttpCookie>>> manager: cookieStore.entrySet()) {
			//System.out.println ("Path is " + manager.getKey());
			
			HashMap<String, ArrayList<HttpCookie>> stores = manager.getValue();
			
			for (Entry<String, ArrayList<HttpCookie>> store : stores.entrySet()) {
					//System.out.println ("Expiry Date " + store.getKey());
					
					ArrayList <HttpCookie> cookiesList = store.getValue();
					
					for (HttpCookie c : cookiesList) {
						System.out.println (c.getName() + " " +  c.getValue());
					}				
			}
			//System.out.println ("\n");
		}	
	}
	
	public void deleteCookie () {
		
	}

}
