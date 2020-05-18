package Connections;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static java.net.CookiePolicy.ACCEPT_ORIGINAL_SERVER;



public class LoginConnection {
	
	private String SERVICE_URL = "http://localhost:8081/BookStoreServlet";	
	private static LoginConnection singletonCon = null;
	private ArrayList <String> cookies = null;
	
	private LoginConnection ( ) {		
	}
	
	public static LoginConnection getConnection () {
		if (singletonCon == null) {
			singletonCon = new LoginConnection ();
		}		
		return singletonCon;
	}
	
	public byte [] login (byte [] requestBody, String reqContentType, String resContentType ) throws IOException, ClassNotFoundException  {
		cookies = new ArrayList ();
		URL obj = new URL(SERVICE_URL + "/login");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.addRequestProperty ("Accept", resContentType);
		con.addRequestProperty ("Content-Type", reqContentType);
		con.setDoOutput(true);	
		con.setConnectTimeout(5000);
        con.setReadTimeout(5000);         
		
	   	OutputStream os = con.getOutputStream();
		os.write(requestBody, 0, requestBody.length);
		/*
		Map<String, List<String>> headerFields = con.getHeaderFields();		 
        Set<String> headerFieldsSet = headerFields.keySet();
        Iterator<String> hearerFieldsIter = headerFieldsSet.iterator();
        
        while (hearerFieldsIter.hasNext()){	 
            String headerFieldKey = hearerFieldsIter.next();
 
            if ("Set-Cookie".equalsIgnoreCase(headerFieldKey)){
            	System.out.println (headerFieldKey);            	
 
                List<String> headerFieldValue = headerFields.get(headerFieldKey);
 
                for (String headerValue : headerFieldValue){
                    System.out.println("Cookie Found...");
                    System.out.println (headerValue);
                    cookies.add(headerValue)   ;             
                }    
            } 
        }  
        */
		System.out.println ("Strinting Cookie Count");
		Map<String, List<String>> headerFields = con.getHeaderFields();
		List<String> cookiesHeader = headerFields.get("Set-Cookie");
		
		for (String cookie : cookiesHeader) {
			HttpCookie httpCookie = HttpCookie.parse(cookie).get(0);
		    String name = httpCookie.getName(); 
		    String value = httpCookie.getValue();
		    String domain = httpCookie.getDomain();
		    
		    System.out.println (name);
		    System.out.println (value);
		    System.out.println (domain);
		    System.out.println (httpCookie.hasExpired());
		}
	      
		
		System.out.println ("End Cookie Count");
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		try (InputStream inputStream = con.getInputStream()) {
		  int n = 0;
		  byte[] buffer = new byte[1024];
		  while (-1 != (n = inputStream.read(buffer))) {
		    output.write(buffer, 0, n);
		  }
		}
		byte[] serverRes = output.toByteArray();		
		return serverRes;		
	}
	
	public ArrayList getCookies () {
		return cookies;
	}
}
