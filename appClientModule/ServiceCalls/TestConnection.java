package ServiceCalls;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestConnection {
	
	private String SERVICE_URL = "http://localhost:8081/BookStoreServlet";	
	private static TestConnection singletonCon = null;
	
	private TestConnection ( ) {		
	}
	
	public static TestConnection getConnection () {
		if (singletonCon == null) {
			singletonCon = new TestConnection ();
		}		
		return singletonCon;
	}
	
	public void testConnection (String usrName, String passwd, String message) {
		System.out.println ("testConnection Called");
		
		try {
		
			URL obj = new URL(SERVICE_URL + "/test/test");
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);	
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);  
			con.setRequestProperty("userName", usrName);
			con.setRequestProperty("passWD", passwd);
			
			byte requestBody [] = message.getBytes();
		
			OutputStream os = con.getOutputStream();
			os.write(requestBody, 0, requestBody.length);
			System.out.println ("Calling done");
			
			int responseCode = con.getResponseCode();	
			System.out.println ("Response " + responseCode);
			
			String responseLine = null;
			
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			StringBuilder response = new StringBuilder();
			
			while ((responseLine = br.readLine()) != null) {
				response.append("\n" + responseLine.trim());
			}
			
			System.out.print("Servlet Response " + response.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
