import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

import Entity.AutheticationData;
import Exceptions.WrongRequestTypeException;
import PersistentObjects.PersistentObjectList;
import ServiceCalls.LoginConnection;
import Util.Messages.GeneralClientResponseMsgs;
import Util.Messages.GeneralServerResponseMsgs;
import Util.Messages.ResponseMsgs;
import Util.PayLoadObjectGenerators.ObjectGeneratorFromPayLoad;
import Util.PayLoadObjectGenerators.RequestPayLoadGenerator;

public class LoginUI {

	private LoginConnection loginCon;
	private Scanner scn;
	private ArrayList <String> cookies;
	
	public ArrayList<String> getCookies () {
		return cookies;
	}
	
	
	public ResponseMsgs login () {
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
			System.out.println ("");
			AutheticationData loginObj = new AutheticationData (userName,passWord); 
			
		
			RequestPayLoadGenerator payLoadGenCon = RequestPayLoadGenerator.getConnection();			
		
			if (reqContentType.equalsIgnoreCase("text")) {
				payload = payLoadGenCon.textPayLoadGenerator(loginObj);
				reqContentType = reqContentType +"/plain; charset=utf-8";
			}
			else if (reqContentType.equalsIgnoreCase("json")) {
				payload = payLoadGenCon.jsonPayLoadGenerator(loginObj);
				reqContentType = "application/json; utf-8";
			} 
			else if (reqContentType.equalsIgnoreCase("xml")) {
				payload = payLoadGenCon.xmlPayLoadGenerator(loginObj);
				reqContentType = "application/xml";
			}
			else {						
				throw new WrongRequestTypeException("\nWrong Request Type Entered. Return Type Can Be Text/Json/XML");
			}
		
			if (resContentType.equalsIgnoreCase("text")) {
				resContentType = resContentType +"/plain; charset=utf-8";
			}
			else if (resContentType.equalsIgnoreCase("json")) {
				resContentType = "application/json; utf-8";
			} 
			else if (resContentType.equalsIgnoreCase("xml")) {
				resContentType = "application/xml";
			} 
			else {	
				throw new WrongRequestTypeException("\nWrong Request Type Entered. Return Type Can Be Text/Json/XML");
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
				PersistentObjectList persistentObjList = PersistentObjectList.getConnection ();
				persistentObjList.setUserProfile(loginObj);
								
			}
			catch (MalformedURLException e) {
				serverRes = GeneralClientResponseMsgs.getConnection();
				serverRes.setMsg(e.getMessage());
				Logger lgr = Logger.getLogger(LoginUI.class.getName());
	            lgr.log(Level.SEVERE, e.getMessage(), e);
			}
			catch (ProtocolException e) {
				serverRes = GeneralClientResponseMsgs.getConnection ();
				serverRes.setMsg(e.getMessage());
				Logger lgr = Logger.getLogger(LoginUI.class.getName());
	            lgr.log(Level.SEVERE, e.getMessage(), e);
			}
			catch (IOException e) {
				serverRes = GeneralClientResponseMsgs.getConnection ();
				serverRes.setMsg("\nConnection Error Occured\n");
				Logger lgr = Logger.getLogger(LoginUI.class.getName());
	            lgr.log(Level.SEVERE, e.getMessage(), e);
			}
			catch (Exception e) {
				serverRes = GeneralClientResponseMsgs.getConnection ();
				serverRes.setMsg("\nGeneral Error Occured");
				Logger lgr = Logger.getLogger(LoginUI.class.getName());
	            lgr.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		catch (WrongRequestTypeException e) {
			serverRes = GeneralClientResponseMsgs.getConnection ();
			serverRes.setMsg(e.getMessage());
			Logger lgr = Logger.getLogger(LoginUI.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
		}
		catch (JsonProcessingException e) {
			serverRes = GeneralClientResponseMsgs.getConnection ();
			serverRes.setMsg(e.getMessage());
			Logger lgr = Logger.getLogger(LoginUI.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
		}
		catch (IOException e) {
			serverRes = GeneralClientResponseMsgs.getConnection ();
			serverRes.setMsg(e.getMessage());
			Logger lgr = Logger.getLogger(LoginUI.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
		}		
		catch (Exception e) {
			serverRes = GeneralClientResponseMsgs.getConnection ();
			serverRes.setMsg("\nSorry. Something Went Worng");
			Logger lgr = Logger.getLogger(LoginUI.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);			 
		}
		return serverRes;
	}
}
