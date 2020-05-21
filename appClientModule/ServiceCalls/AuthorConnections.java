package ServiceCalls;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import Entity.Author;
import Util.Messages.GeneralServerResponseMsgs;

import Util.Network.CookieManager;

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
		
		
	public byte [] addNewAuthor (byte [] requestBody, String reqContentType, String resContentType ) throws IOException, ClassNotFoundException  {
		
		URL obj = new URL(SERVICE_URL + "/authors/addBook");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.addRequestProperty ("Accept", resContentType);
		con.addRequestProperty ("Content-Type", reqContentType);
		con.setDoOutput(true);	
		con.setConnectTimeout(5000);
        con.setReadTimeout(5000); 
        
        //CookieManager.
        
		
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
}
