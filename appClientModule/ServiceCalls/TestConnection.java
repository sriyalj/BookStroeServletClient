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
	
	public String testConnection (String usrName, String passwd, String message) {
		System.out.println ("testConnection Called");
		
		try {
		
			URL obj = new URL(SERVICE_URL + "/test/test");
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);	
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);  
			con.addRequestProperty("userName", usrName);
			con.addRequestProperty("passWD", passwd);
			
			byte requestBody [] = usrName.getBytes();
		
			OutputStream os = con.getOutputStream();
			os.write(requestBody, 0, requestBody.length);
			
			int responseCode = con.getResponseCode();	
			System.out.println ("Server Response Code " + responseCode);
			
			String responseLine = null;
			
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			StringBuilder response = new StringBuilder();
			
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			
			System.out.print("Servlet Response " + response.toString());
			return response.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}	
	}
	
	public void testConnection2 (String usrID) {
		System.out.println ("testConnection Called");
		
		try {
		
			URL obj = new URL(SERVICE_URL + "/test/test2");
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);	
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000); 
			
			byte requestBody [] = usrID.getBytes();
		
			OutputStream os = con.getOutputStream();
			os.write(requestBody, 0, requestBody.length);
			
			int responseCode = con.getResponseCode();	
			System.out.println ("Server Response Code " + responseCode);
			
			String responseLine = null;
			
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			StringBuilder response = new StringBuilder();
			
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			
			System.out.print("Servlet Response " + response.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
