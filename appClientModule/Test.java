import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {
	
	public void testConnection (String payLoad) throws IOException {
		String SERVICE_URL = "http://localhost:8081/BookStoreServlet";
		URL obj = new URL(SERVICE_URL + "/test/test");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		//con.addRequestProperty ("Accept", resContentType);
		//con.addRequestProperty ("Content-Type", reqContentType);
		con.setDoOutput(true);	
		con.setConnectTimeout(5000);
        con.setReadTimeout(5000); 
        
	    byte arr [] = payLoad.getBytes();
	   	OutputStream os = con.getOutputStream();
		os.write(arr, 0, arr.length); 
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		try (InputStream inputStream = con.getInputStream()) {
		  int n = 0;
		  byte[] buffer = new byte[1024];
		  while (-1 != (n = inputStream.read(buffer))) {
		    output.write(buffer, 0, n);
		  }
		  byte[] serverRes = output.toByteArray();
		  System.out.println (new String (serverRes));
		}
	}

}
