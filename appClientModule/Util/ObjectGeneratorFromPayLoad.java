package Util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import javax.servlet.http.HttpServletRequest;

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

}
