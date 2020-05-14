import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Connections.AuthorConnections;
import Entity.Author;
import Util.GeneralClientResponseMsgs;
import Util.GeneralServerResponseMsgs;
import Util.ObjectGeneratorFromPayLoad;
import Util.RequestPayLoadGenerator;
import Util.ResponseMsgs;


public class AuthorOperationsUI {
	
	private static AuthorOperationsUI con;
	private AuthorConnections AuthorCon;
	private Scanner scn;
	
	private AuthorOperationsUI () {
	}
	
	public static AuthorOperationsUI getConnection () {
		if (con == null) {
			con =  new AuthorOperationsUI ();
		}		
		return con;
	}
	
	public void showAuthorOperations () {
		boolean chk =  true;		
		
		while (chk) {
			System.out.println ("\n");
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println ("\n------------------ Online Book Store ------------------");
			System.out.println ("\n------------- Author Operations -------------");
			System.out.println ("1. Add a New Author");
			System.out.println ("2. Delete an Author");
			System.out.println ("3. Update Autor Details");
			System.out.println ("4. View Autor Details By First Name");
			System.out.println ("4. View Autor Details By Last");
			System.out.println ("5. View All Autor Details");
			System.out.print ("Please Select Your Operation [1,2,3,4,5]..Press -1 To Terminate : ");
			Scanner scn = new Scanner (System.in);
		
			int choice = 0;			
		
			try { 
				choice = scn.nextInt();
			
				switch (choice) {
					case 1 : ResponseMsgs response = addNewAuthorUI ();	
					         
							 System.out.println ("\n");
							 System.out.print("\033[H\033[2J");
							 System.out.flush();
							 
							 if (response instanceof GeneralServerResponseMsgs) {
								 GeneralServerResponseMsgs obj = (GeneralServerResponseMsgs) response;
								 System.out.println (obj.getServerResponseCode());
								 System.out.println (obj.getMsg());
							 }
							 else {
								 GeneralClientResponseMsgs obj = (GeneralClientResponseMsgs)response;
								 System.out.println (obj.getMsg());
							 }
							 chk = false;
							 new java.util.Timer().schedule( 
										new java.util.TimerTask() {
											@Override
											public void run() {
												System.out.println ("\n");
											    System.out.print("\033[H\033[2J");
										  		System.out.flush();
											}
										}, 
										5000 
							  );
						  	 
							 break;
				
					case 2 : break;
				
					case 3 : break;
					
					case 4 : break;
					
					case 5 : break;
				
					case -1 : System.out.println ("\n");
							  System.out.print("\033[H\033[2J");
					          System.out.flush();
					          System.out.println ("Exiting the System");
					          
					          new java.util.Timer().schedule( 
										new java.util.TimerTask() {
											@Override
											public void run() {
												System.out.println ("\n");
											    System.out.print("\033[H\033[2J");
										  		System.out.flush();
											}
										}, 
										5000 
							  );
						  	 break; 
						  
					default : System.out.println ("\n");
					  		  System.out.print("\033[H\033[2J");
			                  System.out.flush();
			                  System.out.println ("Invalid Option Choosen. \nYou Need To Enter An Integer From 1,2,3,-1");
			                  
			                  new java.util.Timer().schedule( 
										new java.util.TimerTask() {
											@Override
											public void run() {
												System.out.println ("\n");
											    System.out.print("\033[H\033[2J");
										  		System.out.flush();
											}
										}, 
										5000 
								);
				}
			}
			catch (InputMismatchException e) {
				System.out.println ("\n");
		  		System.out.print("\033[H\033[2J");
                System.out.flush();
				System.out.println ("\nInvalid Input Type. \nYou Need To Enter An Integer From 1,2,3,-1");
				
				new java.util.Timer().schedule( 
						new java.util.TimerTask() {
							@Override
							public void run() {
								System.out.println ("\n");
							    System.out.print("\033[H\033[2J");
						  		System.out.flush();
							}
						}, 
						5000 
					 );
			}
			catch (Exception e) {
				System.out.println ("\n");
		  		System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println ("\nGeneral Error Occured");
				chk = false;
				
				new java.util.Timer().schedule( 
						new java.util.TimerTask() {
							@Override
							public void run() {
								System.out.println ("\n");
							    System.out.print("\033[H\033[2J");
						  		System.out.flush();
							}
						}, 
						5000 
					 );
			}	
		}		
	}
	
	ResponseMsgs addNewAuthorUI () {
		
		String fstName = "", mdleName = "", lastName = "", originCountry = "", reqContentType = "", resContentType = "";
		byte [] payload = null;
		byte [] response = null;
		ResponseMsgs serverRes = null;
		scn =  new Scanner (System.in).useDelimiter("\n");;
		
		System.out.println ("\n");
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println ("\n------------------ Online Book Store ------------------");
		System.out.println ("\n------------- Add New Author -------------");
		
		try {
			System.out.print ("Enter Author First Name :");
			fstName = scn.next();
			System.out.print ("\nEnter Author Middle Name :");
			mdleName = scn.next();
			System.out.print ("\nEnter Author Last Name :");
			lastName = scn.next();
			System.out.print ("\nEnter Author Origin Country :");
			originCountry = scn.next();
			System.out.println ("\n Request Content Type [text/json/xml] :");
			reqContentType = scn.next();
			System.out.println ("\n Response Content Type [text/json/xml] :");
			resContentType = scn.next();
			
			Author authorObj = new Author (fstName,mdleName,lastName,originCountry);
			
			RequestPayLoadGenerator payLoadGenCon = RequestPayLoadGenerator.getConnection();			
			
			if (reqContentType.equals("text")) {
				payload = payLoadGenCon.textPayLoadGenerator(authorObj);
				reqContentType = reqContentType +"/plain; charset=utf-8";
			}
			else if (reqContentType.equals("json")) {
				payLoadGenCon.textPayLoadGenerator(authorObj);
				reqContentType = "/application/json; utf-8";
			} 
			else if (reqContentType.equals("xml")) {
				payLoadGenCon.textPayLoadGenerator(authorObj);
				reqContentType = "application/xml";
			}
			
			if (resContentType.equals("text")) {
				resContentType = resContentType +"/plain; charset=utf-8";
			}
			else if (resContentType.equals("json")) {
				resContentType = "/application/json; utf-8";
			} 
			else if (resContentType.equals("xml")) {
				resContentType = "application/xml";
			} 
			
			try {
				AuthorCon = AuthorConnections.getConnection();
				response = AuthorCon.addNewAuthor(payload, reqContentType,resContentType );	
				ObjectGeneratorFromPayLoad objFromPayLoad = ObjectGeneratorFromPayLoad.getConnection();
				serverRes = (GeneralServerResponseMsgs)objFromPayLoad.getObjectFromText(response);				
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
			 
			 new java.util.Timer().schedule( 
				new java.util.TimerTask() {
					@Override
					public void run() {
						System.out.println ("\n\n Sorry. Something Went Worng");
					}
				}, 
				5000 
			 );
			showAuthorOperations();
		}
	  return serverRes;
	}
	
	String viewAuthorDetailsByFirstName (String fstName) {
		return null;
	}

}
