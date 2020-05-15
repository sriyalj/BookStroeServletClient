package Util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import Entity.Author;

public class ObjectGeneratorFromPayLoad {
	
	private static ObjectGeneratorFromPayLoad con;
	
	private ObjectGeneratorFromPayLoad () {
		
	}
	
	public static ObjectGeneratorFromPayLoad getConnection () {
		
		if (con == null) {
			con = new ObjectGeneratorFromPayLoad ();
		}
		
		return con;
	}
	
	public Object getObjectFromText (byte [] response) throws ClassNotFoundException, IOException  {
		Object reterivedObj = null;
		
		ByteArrayInputStream in = new ByteArrayInputStream(response);
		ObjectInputStream ois = new ObjectInputStream(in);
		reterivedObj = ois.readObject();
		in.close();
		ois.close();
			
		return reterivedObj;
		
	}
	

	public Object getObjectFromJson (byte [] response) throws ClassNotFoundException, IOException  {
		String reterivedObj = null;
				
		ByteArrayInputStream in = new ByteArrayInputStream(response);		
		ObjectInputStream ois = new ObjectInputStream(in);		
		reterivedObj = (String) ois.readObject();		
		in.close();
		ois.close();
		
		ObjectMapper mapper = new ObjectMapper();
		GeneralServerResponseMsgs serverMsg = mapper.readValue(reterivedObj, GeneralServerResponseMsgs.class);
		return serverMsg;
		
	}
	
	public Object getObjectFromXML (byte [] response) throws ClassNotFoundException, IOException  {
		String reterivedObj = null;
				
		ByteArrayInputStream in = new ByteArrayInputStream(response);		
		ObjectInputStream ois = new ObjectInputStream(in);		
		reterivedObj = (String) ois.readObject();		
		in.close();
		ois.close();
		
		XmlMapper xmlMapper = new XmlMapper();;
		GeneralServerResponseMsgs serverMsg = xmlMapper.readValue(reterivedObj, GeneralServerResponseMsgs.class);
		return serverMsg;
		
	}

}
