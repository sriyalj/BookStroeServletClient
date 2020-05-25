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
	
	public void addCookie (String path, HttpCookie cookie, String time) {
		
		System.out.print(cookie.getMaxAge());
		
		if (cookie.getMaxAge() == 0) {
			path = cookie.getPath()!=null ? cookie.getPath() : "/";
			System.out.println ("Found Cookie To Delete");
			deleteCookie(path, cookie);
			return;
		}
		
		if (cookie.getMaxAge() <0 ) {
			time = "Session";
		}
		if (cookieStore.containsKey(path)) {			
			HashMap store = cookieStore.get(path);
			ArrayList <HttpCookie> cookiesListForPath = (ArrayList<HttpCookie>) store.get(time);
			
			if (cookiesListForPath == null) {
				cookiesListForPath = new ArrayList();
			}
			cookiesListForPath.add(cookie);
			store.put(time, cookiesListForPath);
			cookieStore.put(path, store);			
		}
		else {
			HashMap store = new HashMap () ;
			ArrayList <HttpCookie> cookiesListForPath = new ArrayList();
			cookiesListForPath.add(cookie);
			store.put(time, cookiesListForPath);
			cookieStore.put(path, store);
		}
	}
	
	public ArrayList <HttpCookie> getAllCookiesForPath (String path) {
		ArrayList <HttpCookie> allCookieList = new ArrayList ();
		
		HashMap store = cookieStore.get(path);		
		Iterator<Map.Entry<String, ArrayList>> entries = store.entrySet().iterator();		
		
		while (entries.hasNext()) {
		    Map.Entry<String, ArrayList> entry = entries.next();
		    ArrayList <HttpCookie >cookiesList = entry.getValue();
		    
		    for (HttpCookie cookie : cookiesList) {
		    	allCookieList.add(cookie);		    	
		    }
		}		
		return allCookieList;
	}
	
	public ArrayList <HttpCookie> getAllValidCookiesForPath (String path) throws ParseException {
		
		ArrayList <HttpCookie> allValidCookieList = new ArrayList ();
		HashMap store = cookieStore.get(path);
		
		if (store == null) {
			return allValidCookieList;
		}
		Iterator<Map.Entry<String, ArrayList>> entries = store.entrySet().iterator();		
		
		while (entries.hasNext()) {
		    Map.Entry<String, ArrayList> entry = entries.next();
		    String time = entry.getKey();
		    
		    if (time.equals("Session")) {
		    	ArrayList <HttpCookie >cookiesList = entry.getValue();
			    
			    for (HttpCookie cookie : cookiesList) {
			    	allValidCookieList.add(cookie);		    	
			    }
		    }
		    else {		    
		    	Date currentDateTime = DateTime.getUTCdatetimeAsDate();
		    	SimpleDateFormat dateFormatter =new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		    	Date cookieTime = dateFormatter.parse(time);
		    
		    	long diff = (cookieTime.getTime()-currentDateTime.getTime())/1000;
		    
		    	if (diff > 0) {
		    		ArrayList <HttpCookie >cookiesList = entry.getValue();
			    
		    		for (HttpCookie cookie : cookiesList) {
		    			allValidCookieList.add(cookie);		    	
		    		}		    	
		    	}
		    	else {
		    		deleteAllCookiesForPath(path, time);
		    	}
		    }
		}		
		return allValidCookieList; 
	}
	
	public void printAllCookies () {
		System.out.println ("\n Printing All Cookies");
		for ( Entry<String, HashMap<String, ArrayList<HttpCookie>>> manager: cookieStore.entrySet()) {
						
			HashMap<String, ArrayList<HttpCookie>> stores = manager.getValue();
			
			for (Entry<String, ArrayList<HttpCookie>> store : stores.entrySet()) {
					System.out.println ("Expiry Date " + store.getKey());
					
					ArrayList <HttpCookie> cookiesList = store.getValue();
					
					for (HttpCookie cookie : cookiesList) {
						System.out.println (cookie.getName() + " " +  cookie.getValue());
					}				
			}
			System.out.println ("\n");
		}	
	}
	
	public void deleteCookie (String path, HttpCookie delCookie) {
		
		String receivedCookieName = delCookie.getName();
		String receivedCookieValue = delCookie.getValue();
		
		HashMap<String, ArrayList<HttpCookie>> store = cookieStore.get(path);
		
		if (store != null) {
			Iterator<Entry<String, ArrayList<HttpCookie>>> entries = store.entrySet().iterator();		
		
			while (entries.hasNext()) {
				Entry<String, ArrayList<HttpCookie>> entry = entries.next();
				String time = entry.getKey();	
		    
				ArrayList <HttpCookie> cookiesList = entry.getValue();
		    
				for (HttpCookie cookie : cookiesList) {
					if ((receivedCookieName.equals(cookie.getName())) && (receivedCookieValue.equals(cookie.getValue()))) {
						System.out.println (receivedCookieName + " " + cookie.getName());
						System.out.println (receivedCookieValue + " " + cookie.getValue());
						cookiesList.remove(cookie);
					}
				}		    
				store.put(time, cookiesList);
			}
		}
	}
	
	private void deleteAllCookiesForPath (String path, String timeStamp) {
		
		HashMap <String, ArrayList<HttpCookie>> store = cookieStore.get(path);
		
		if (store != null) {
			if (store.containsKey(timeStamp)) {
				store.remove(timeStamp);
			}
		}		
	}
}
