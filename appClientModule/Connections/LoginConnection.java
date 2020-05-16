package Connections;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class LoginConnection {
	
	private String SERVICE_URL = "http://localhost:8081/BookStoreServlet";	
	private static LoginConnection singletonCon = null;
	
	private LoginConnection ( ) {		
	}
	
	public static LoginConnection getConnection () {
		if (singletonCon == null) {
			singletonCon = new LoginConnection ();
		}		
		return singletonCon;
	}
	
	public byte [] login (byte [] requestBody, String reqContentType, String resContentType ) throws IOException, ClassNotFoundException  {
		
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
		
		Map<String, List<String>> headerFields = con.getHeaderFields();
		 
        Set<String> headerFieldsSet = headerFields.keySet();
        Iterator<String> hearerFieldsIter = headerFieldsSet.iterator();
        
        System.out.println ("Printing Headers");
        while (hearerFieldsIter.hasNext()) {
            
            String headerFieldKey = hearerFieldsIter.next();
             
            if ("Set-Cookie".equalsIgnoreCase(headerFieldKey)) {
                 
                List<String> headerFieldValue = headerFields.get(headerFieldKey);
                 
                for (String headerValue : headerFieldValue) {
                     
                   System.out.println("Cookie Found...");
                   System.out.println (headerValue);
                    
                   String[] fields = headerValue.split("=",1);

                   String cookieValue = fields[0];
                   String expires = null;
                   String path = null;
                   String domain = null;
                   boolean secure = false;
                    
                   // Parse each field
                   for (int j = 1; j < fields.length; j++) {
                       if ("secure".equalsIgnoreCase(fields[j])) {
                           secure = true;
                       }
                       else if (fields[j].indexOf('=') > 0) {
                           String[] f = fields[j].split("=");
                           if ("expires".equalsIgnoreCase(f[0])) {
                               expires = f[1];
                           }
                           else if ("domain".equalsIgnoreCase(f[0])) {
                               domain = f[1];
                           }
                           else if ("path".equalsIgnoreCase(f[0])) {
                               path = f[1];
                           }
                       }
                        
                   }
                    
                   System.out.println("cookieValue:" + cookieValue);
                   System.out.println("expires:" + expires);
                   System.out.println("path:" + path);
                   System.out.println("domain:" + domain);
                   System.out.println("secure:" + secure);
                     
                   System.out.println("*****************************************");  
                }                 
            }        	
	    }
        CookieManager cookieManager = new CookieManager();
		CookieHandler.setDefault(cookieManager);		

		List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
		System.out.println (cookies.isEmpty());
		
		for (HttpCookie cookie : cookies) {
			System.out.println ("Iterating Cookies");
		    System.out.println(cookie.getDomain());
		    System.out.println(cookie);
		}
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
}
