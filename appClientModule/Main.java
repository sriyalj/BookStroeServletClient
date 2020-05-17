import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connections.TestConnection;
import Util.GeneralClientResponseMsgs;
import Util.GeneralServerResponseMsgs;
import Util.ResponseMsgs;

//import DBConn.AuthorDBConn;

public class Main {
	
	private Scanner scn;
		
	public void showMainMenu () {
				
		while (true) {
			scn =  new Scanner (System.in);
			System.out.println("\f");
			System.out.flush();
			System.out.println ("------------------ Online Book Store ------------------");
			System.out.println ("\n------------- Main Menu -------------");
			System.out.println ("1. Book Operations");
			System.out.println ("2. Author Operations");
			System.out.println ("3. Publisher Operations");
			System.out.println ("4. Test");
			System.out.print   ("Please Select Your Operation [1,2,3].Press -1 To Terminate : ");
			
			int choice = 0;		
				
			try { 				
				choice = scn.nextInt();
							
				switch (choice) {
					case 1 : break;
				
					case 2 : AuthorOperationsUI AuthorOperationsUICon = AuthorOperationsUI.getConnection();
							 AuthorOperationsUICon.showAuthorOperations();
							 break;
				
					case 3 : break;
					
					case 4 : TestConnection tC = TestConnection.getConnection();
							 tC.testConnection();
							 break;
				
					case -1 : System.out.println ("\n");
						      System.out.print("\033[H\033[2J");
					  		  System.out.flush();
					  		  System.out.println ("Exiting the System\n");
						  	  System.exit(0);
						  
					default : System.out.println ("\n");
							  System.out.print("\033[H\033[2J");
							  System.out.flush();
							  System.out.println ("Invalid Option Choosen. \nYou Need To Choose An Integer From 1,2,3,-1");
							  
							  try {
								  Thread.sleep(3000);        
							  } 
							  catch( InterruptedException ex) {  
								   Thread.currentThread().interrupt();
							  }
				}
			}
			catch (InputMismatchException e) {
				System.out.println ("\n");
		  		System.out.print("\033[H\033[2J");
                System.out.flush();
				System.out.println ("\nInvalid Input Type. \nYou Need To Enter An Integer From 1,2,3,-1");
				
				try {
					Thread.sleep(3000);        
				} 
				catch( InterruptedException ex) {  
					Thread.currentThread().interrupt();
				}
			}
			catch (Exception e) {
				System.out.println ("\n");
				System.out.print("\033[H\033[2J");
                System.out.flush();
				System.out.println ("\nGeneral Error Occured. \nExiting The System");
				Logger lgr = Logger.getLogger(Main.class.getName());
	            lgr.log(Level.SEVERE, e.getMessage(), e);
				
				new java.util.Timer().schedule( 
						new java.util.TimerTask() {
							@Override
							public void run() {
								System.exit(0);
							}
						}, 
						5000 
					 );
				
			}		
		}
		
	}
	
	public boolean login (){
		LoginUI login =  new LoginUI();
		ResponseMsgs serverRes = login.loginInterface();	
		boolean loginStatus = true;
		
		if (serverRes instanceof GeneralServerResponseMsgs) {
			 GeneralServerResponseMsgs obj = (GeneralServerResponseMsgs) serverRes;
			 System.out.println ("\n");
			 System.out.print("\033[H\033[2J");
			 System.out.flush();
			 System.out.println (obj.getServerResponseCode());
			 System.out.println (obj.getMsg());
			 System.out.println ("\n");
			 
			 if (obj.getServerResponseCode().equals("200")) {
				 loginStatus = false;
				 
				 Test t = new Test ();
				 
					try {
						t.testConnection(login.getCookies(), "Sriyal");
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					
					Scanner scn =  new Scanner (System.in);
					scn.next();
					
					try {
						t.testConnection(login.getCookies(), "Rehansa");
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				 
			 }
		 }
		 else {
			 GeneralClientResponseMsgs obj = (GeneralClientResponseMsgs)serverRes;
			 System.out.println (obj.getMsg());
			 System.out.println ("\n");
			 loginStatus = true;
		 }
		return loginStatus;		
	}
			
	public static void main(String[] args) throws IOException {		// TODO Auto-generated method stub
		boolean loginStatus = true;
		while (loginStatus) {
			loginStatus = new Main().login();
		}
		/*
		Test t = new Test ();
		try {
			t.testConnection("QWERTY");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Scanner scn =  new Scanner (System.in);
		scn.next();
		
		try {
			t.testConnection("ASDFGH");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
}


/*
new java.util.Timer().schedule( 
	new java.util.TimerTask() {
		@Override
		public void run() {			  						
			//showMainMenu();
		}
	}, 
	5000 
);
*/