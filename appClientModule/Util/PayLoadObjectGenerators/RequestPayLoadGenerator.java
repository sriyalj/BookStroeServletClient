package Util.PayLoadObjectGenerators;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StringWriter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;



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
	
	public byte [] xmlPayLoadGenerator (Object obj) throws IOException  {	    
		XmlMapper xmlMapper = new XmlMapper();
		String xmlString = xmlMapper.writeValueAsString(obj);
		
        
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    ObjectOutputStream oos = new ObjectOutputStream(bos);
	    oos.writeObject(xmlString);
	    oos.flush();
	    byte [] requestBody = bos.toByteArray();
	    return requestBody;
	}

}
