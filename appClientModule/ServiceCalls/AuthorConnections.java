package ServiceCalls;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;


import Exceptions.AuthenticationFailException;
import PersistentObjects.PersistentObjectList;
import Util.Messages.GeneralServerResponseMsgs;

import Util.Network.CookieManager;
import Util.PayLoadObjectGenerators.ObjectGeneratorFromPayLoad;
import Util.PayLoadObjectGenerators.RequestPayLoadGenerator;

public class AuthorConnections{
	
	private String SERVICE_URL = "http://localhost:8081/BookStoreServlet";	
	private static AuthorConnections singletonCon = null;
	
	private AuthorConnections ( ) {		
	}
	
	public static AuthorConnections getConnection () {
		if (singletonCon == null) {
			singletonCon = new AuthorConnections ();
		}		
		return singletonCon;
	}
		
		
	public byte [] addNewAuthor (byte [] requestBody, String reqContentType, String resContentType ) throws IOException, ClassNotFoundException, ParseException, AuthenticationFailException  {
				       
		URL obj = new URL(SERVICE_URL + "/authors/addBook");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.addRequestProperty ("Accept", resContentType);
		con.addRequestProperty ("Content-Type", reqContentType);
		con.setDoOutput(true);	
		con.setConnectTimeout(5000);
        con.setReadTimeout(5000); 
        
        boolean authCheck = true;
		int authCheckCnt = 0;
		while (authCheck) {
			authCheck = !(getAuthenticated ("/"));
			authCheckCnt++;
			
			if (authCheckCnt > 3) {
				throw new AuthenticationFailException ("\nThere Was A Problem In Authenticating. Please Try Again Later");
			}
		}
        
		ArrayList <HttpCookie> cookieList = CookieManager.getConnection().getAllValidCookiesForPath("/");
		
		for (HttpCookie c : cookieList) {
			String cookie = c.toString()+";";
			//cookie = cookie + " Max-Age="+c.getMaxAge();
			System.out.println (cookie);
			con.addRequestProperty("Cookie", cookie);
		}
		
        
		
	   	OutputStream os = con.getOutputStream();
		os.write(requestBody, 0, requestBody.length); 
		
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
	
	private boolean getAuthenticated (String path)  {
		try {
			ArrayList <HttpCookie> cookieList = CookieManager.getConnection().getAllValidCookiesForPath(path);
			
			if (cookieList.isEmpty()) {	
				
				PersistentObjectList persistentObjList = PersistentObjectList.getConnection ();
				RequestPayLoadGenerator payLoadGenCon = RequestPayLoadGenerator.getConnection();
				
				byte payload [] = payLoadGenCon.textPayLoadGenerator(persistentObjList.getUserProfile());
				
				LoginConnection loginCon = LoginConnection.getConnection();	
				byte [] response = loginCon.login(payload, "text/plain; charset=utf-8", "text/plain; charset=utf-8");
				ObjectGeneratorFromPayLoad objFromPayLoad = ObjectGeneratorFromPayLoad.getConnection();
				GeneralServerResponseMsgs serverRes = (GeneralServerResponseMsgs)objFromPayLoad.getObjectFromText(response);
					
				if (serverRes.getServerResponseCode().equals("200")) {
					return true;
				}
				else {
					return false;
				}
			}
			else {				
				return true;
			}
		}
		catch (Exception e) {
			return false;
		}	
	}
}
