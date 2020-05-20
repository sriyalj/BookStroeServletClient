package ServiceCalls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import Util.Network.Cookie;
import Util.Network.CookieManager;
import Util.Time;

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
		
		Map<String, List<String>> headerFields = con.getHeaderFields();
		List<String> cookiesHeader = headerFields.get("Set-Cookie");
		
		if (cookiesHeader != null) {
		
			for (String cookie : cookiesHeader) {
				System.out.println (cookie);
				HttpCookie httpCookie = HttpCookie.parse(cookie).get(0);	    	
			}
		}
		
		//System.out.println (Time.GetUTCdatetimeAsDate());
		
		CookieManager cookiemanager = CookieManager.getConnection();
		Cookie c = new Cookie ("ABC", "DEF");
		c.setDomain("localhost");
		cookiemanager.addCookie("/", c, "Hello");
		
		c = new Cookie ("QWERTY", "123456");
		c.setDomain("google.com");
		cookiemanager.addCookie("/", c, "Hello");		
		
		c = new Cookie ("asdfgh", "09876");
		c.setDomain("facebook.com");
		cookiemanager.addCookie("/delete", c, "Hello");
		
		c = new Cookie ("mnbvcx", "675849");
		c.setDomain("facebook.com");
		cookiemanager.addCookie("/delete", c, "Hello");
		
		cookiemanager.getCookies("/");
		cookiemanager.getCookies("/delete");
		
		
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
