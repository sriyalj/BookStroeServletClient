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
	
	public void addNewAuthor (Author newAuthor) throws IOException, ClassNotFoundException  {
		
		URL obj = new URL(SERVICE_URL + "/authors/addBook");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "text/plain; charset=utf-8"); 
		con.setDoOutput(true);
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    ObjectOutputStream oos = new ObjectOutputStream(bos);
	    oos.writeObject(newAuthor);
	    oos.flush();
	    byte [] requestBody = bos.toByteArray();
		
	    ByteArrayInputStream in = new ByteArrayInputStream(requestBody);
	    ObjectInputStream ois = new ObjectInputStream(in);
	    Author authorObj = (Author)ois.readObject();
	    	    
		OutputStream os = con.getOutputStream();
		os.write(requestBody, 0, requestBody.length);           
		int responseCode = con.getResponseCode();
		
	}
	
	
	
	

}
