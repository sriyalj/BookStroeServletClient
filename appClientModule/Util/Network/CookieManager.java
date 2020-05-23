package Util.Network;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import Util.DateTime;

import java.net.HttpCookie;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	}
	
	public ArrayList <HttpCookie> getAllCookiesForPath (String path) {
		ArrayList <HttpCookie> cookieList = new ArrayList ();
		HashMap store = cookieStore.get(path);		
		Iterator<Map.Entry<String, ArrayList>> entries = store.entrySet().iterator();		
		
		while (entries.hasNext()) {
		    Map.Entry<String, ArrayList> entry = entries.next();
		    ArrayList <HttpCookie >cookies = entry.getValue();
		    
		    for (HttpCookie c : cookies) {
		    	cookieList.add(c);		    	
		    }
		}		
		return cookieList;
	}
	
	public ArrayList <HttpCookie> getAllValidCookiesForPath (String path) throws ParseException {
		
		ArrayList <HttpCookie> cookieList = new ArrayList ();
		HashMap store = cookieStore.get(path);		
		Iterator<Map.Entry<String, ArrayList>> entries = store.entrySet().iterator();		
		
		while (entries.hasNext()) {
		    Map.Entry<String, ArrayList> entry = entries.next();
		    String time = entry.getKey();
		    
		    if (time.equals("Session")) {
		    	ArrayList <HttpCookie >cookies = entry.getValue();
			    
			    for (HttpCookie c : cookies) {
			    	cookieList.add(c);		    	
			    }
		    }
		    else {		    
		    	Date currentDateTime = DateTime.getUTCdatetimeAsDate();
		    	SimpleDateFormat dateFormatter =new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		    	Date cookieTime = dateFormatter.parse(time);
		    
		    	long diff = (cookieTime.getTime()-currentDateTime.getTime())/1000;
		    
		    	if (diff > 0) {
		    		ArrayList <HttpCookie >cookies = entry.getValue();
			    
		    		for (HttpCookie c : cookies) {
		    			cookieList.add(c);		    	
		    		}		    	
		    	}	
		    }
		}		
		return cookieList; 
	}
	
	public void printAllCookies () {
		
		for ( Entry<String, HashMap<String, ArrayList<HttpCookie>>> manager: cookieStore.entrySet()) {
			System.out.println ("Path is " + manager.getKey());
			
			HashMap<String, ArrayList<HttpCookie>> stores = manager.getValue();
			
			for (Entry<String, ArrayList<HttpCookie>> store : stores.entrySet()) {
					System.out.println ("Expiry Date " + store.getKey());
					
					ArrayList <HttpCookie> cookiesList = store.getValue();
					
					for (HttpCookie c : cookiesList) {
						System.out.println (c.getName() + " " +  c.getValue());
					}				
			}
			System.out.println ("\n");
		}	
	}
	
	public void deleteCookie (String path, HttpCookie cookie) {
		
	}

}
