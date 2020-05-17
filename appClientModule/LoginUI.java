import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Scanner;

import Connections.LoginConnection;
import Entity.LoginDetails;
import Util.GeneralClientResponseMsgs;
import Util.GeneralServerResponseMsgs;
import Util.ObjectGeneratorFromPayLoad;
import Util.RequestPayLoadGenerator;
import Util.ResponseMsgs;

public class LoginUI {

	private LoginConnection loginCon;
	private Scanner scn;
	private ArrayList <String> cookies;
	
	public ArrayList getCookies () {
		return cookies;
	}
	
	
	public ResponseMsgs loginInterface () {
		cookies = null;
		String userName = "", passWord = "", reqContentType = "", resContentType = "";
		byte [] payload = null;
		byte [] response = null;
		ResponseMsgs serverRes = null;
		
		scn =  new Scanner (System.in);
		System.out.println("\f");
		System.out.flush();
		System.out.println ("------------------ Online Book Store ------------------");
		System.out.println ("\n------------- Login -------------");
		
		try {
			System.out.print ("User Name : ");
			userName = scn.next();
			System.out.print ("\nPassWord : ");
			passWord = scn.next();		
			System.out.println ("\nRequest Content Type [text/json/xml] :");
			reqContentType = scn.next();
			System.out.println ("\nResponse Content Type [text/json/xml] :");
			resContentType = scn.next();
		
			LoginDetails loginObj = new LoginDetails (userName,passWord);
		
			RequestPayLoadGenerator payLoadGenCon = RequestPayLoadGenerator.getConnection();			
		
			if (reqContentType.equals("text")) {
				payload = payLoadGenCon.textPayLoadGenerator(loginObj);
				reqContentType = reqContentType +"/plain; charset=utf-8";
			}
			else if (reqContentType.equals("json")) {
				payload = payLoadGenCon.jsonPayLoadGenerator(loginObj);
				reqContentType = "application/json; utf-8";
			} 
			else if (reqContentType.equals("xml")) {
				payload = payLoadGenCon.xmlPayLoadGenerator(loginObj);
				reqContentType = "application/xml";
			}
			else {
				System.out.println ("\n");
				System.out.print("\033[H\033[2J");
				System.out.flush();
				System.out.println ("\n\n Wrong Request Type Entered");
			
				serverRes = GeneralClientResponseMsgs.getConnection ();
				serverRes.setMsg("\nWrong Request Type");
				//return serverRes;				
			}
		
			if (resContentType.equals("text")) {
				resContentType = resContentType +"/plain; charset=utf-8";
			}
			else if (resContentType.equals("json")) {
				resContentType = "application/json; utf-8";
			} 
			else if (resContentType.equals("xml")) {
				resContentType = "application/xml";
			} 
			else {
				System.out.println ("\n");
				System.out.print("\033[H\033[2J");
				System.out.flush();
				System.out.println ("\n\n Wrong Request Type Entered");
			
				serverRes = GeneralClientResponseMsgs.getConnection ();
				serverRes.setMsg("\nWrong Request Type");
				//return serverRes;			
			}
			
			try {
				loginCon = LoginConnection.getConnection();
				response = loginCon.login(payload, reqContentType,resContentType );	
				ObjectGeneratorFromPayLoad objFromPayLoad = ObjectGeneratorFromPayLoad.getConnection();				
				
				if (resContentType.contentEquals("text/plain; charset=utf-8")) {
					serverRes = (GeneralServerResponseMsgs)objFromPayLoad.getObjectFromText(response);
				}
				
				if (resContentType.contentEquals("application/json; utf-8")) {
					serverRes = (GeneralServerResponseMsgs)objFromPayLoad.getObjectFromJson(response);
				}
				if (resContentType.contentEquals("application/xml")) {
					serverRes = (GeneralServerResponseMsgs)objFromPayLoad.getObjectFromXML(response);
				}
				
				if (((GeneralServerResponseMsgs) serverRes).getServerResponseCode().equals("200")) {
					cookies = loginCon.getCookies();
				}				
								
			}
			catch (MalformedURLException e) {
				serverRes = GeneralClientResponseMsgs.getConnection();
				serverRes.setMsg(e.getMessage());
			}
			catch (ProtocolException e) {
				serverRes = GeneralClientResponseMsgs.getConnection ();
				serverRes.setMsg(e.getMessage());
			}
			catch (IOException e) {
				serverRes = GeneralClientResponseMsgs.getConnection ();
				serverRes.setMsg("\nGeneral Connection Error Occured");
			}
			catch (Exception e) {
				GeneralClientResponseMsgs.getConnection ();
				serverRes.setMsg("\nGeneral Error Occured");
			}
		}
		catch (Exception e) {
			System.out.println ("\n");
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println ("\n\n Sorry. Something Went Worng");
			 
			 new java.util.Timer().schedule( 
				new java.util.TimerTask() {
					@Override
					public void run() {
						loginInterface();
					}
				}, 
				5000 
			 );
			
		}
		return serverRes;
	}
}
