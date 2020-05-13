package Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Entity.Author;

public class RequestPayLoadGenerator {
	
	private static RequestPayLoadGenerator con;
	
	private RequestPayLoadGenerator () {
		
	}
	
	public static RequestPayLoadGenerator getConnection ( ) {
		if (con == null) {
			con = new RequestPayLoadGenerator();
		}
		return con;
	}
	
	public byte[] textPayLoadGenerator (Object obj) throws IOException  {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    ObjectOutputStream oos = new ObjectOutputStream(bos);
	    oos.writeObject(obj);
	    oos.flush();
	    byte [] requestBody = bos.toByteArray();
	    return requestBody;
	}
	
	public String jsonPayLoadGenerator (Object obj) {
		return null;
	}
	
	public String xmlPayLoadGenerator (Object obj) {
		return null;
	}

}
