package Connections;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
		
		
	public String addNewAuthor (byte [] requestBody, String reqContentType, String resContentType ) throws IOException, ClassNotFoundException  {
		
		URL obj = new URL(SERVICE_URL + "/authors/addBook");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.addRequestProperty ("Accept", resContentType);
		con.addRequestProperty ("Content-Type", reqContentType);
		con.setDoOutput(true);	
		
	   	OutputStream os = con.getOutputStream();
		os.write(requestBody, 0, requestBody.length); 
		
		String responseLine = null;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
		StringBuilder response = new StringBuilder();
		
		while ((responseLine = br.readLine()) != null) {
			response.append("\n" + responseLine.trim());
		}		
		return response.toString();
		
	}
	
	
	
	

}
