package Connections;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
