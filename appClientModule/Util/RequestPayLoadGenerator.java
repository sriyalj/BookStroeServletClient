package Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;



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
	
	public byte [] jsonPayLoadGenerator (Object obj) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
	    String jsonString = mapper.writeValueAsString(obj);
	    
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    ObjectOutputStream oos = new ObjectOutputStream(bos);
	    oos.writeObject(jsonString);
	    oos.flush();
	    byte [] requestBody = bos.toByteArray();
	    return requestBody;
	}
	
	public String xmlPayLoadGenerator (Object obj) {
		return null;
	}

}
