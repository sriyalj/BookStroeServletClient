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

//import DBConn.AuthorDBConn;

public class Main {
	
	private static Main mainCon;
	private Scanner scn;
		
	private Main () {
		//scn =  new Scanner (System.in);
	}
	
	public static Main getMainCon () {
		if (mainCon == null) {
			mainCon = new Main();
		}		
		return mainCon;
	}
	
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
	
	public void login () throws IOException {
		LoginUI.getConnection().loginService();
		
		/*
		
		CookieManager cookieManager = new CookieManager();
		CookieHandler.setDefault(cookieManager);		

		List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
		System.out.println (cookies.isEmpty());
		
		URL url = new URL("https://stackoverflow.com");

		URLConnection connection = url.openConnection();
		connection.getContent();
		
		url = new URL("https://www.google.com");

		connection = url.openConnection();
		connection.getContent();
		*/
		/*
		for (HttpCookie cookie : cookies) {
			System.out.println ("Iterating Cookies");
		    System.out.println(cookie.getDomain());
		    System.out.println(cookie);
		}
		System.out.println ("Cookie thing is over");
		
		//cookieManager.getCookieStore().removeAll();
		*/
	}
			
	public static void main(String[] args) throws IOException {		// TODO Auto-generated method stub
		Main.getMainCon().login();
		//Main.getMainCon().showMainMenu();	
		
	}
	
	

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	

}