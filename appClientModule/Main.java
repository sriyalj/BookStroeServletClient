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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import ServiceCalls.TestConnection;
import Util.Messages.GeneralClientResponseMsgs;
import Util.Messages.GeneralServerResponseMsgs;
import Util.Messages.ResponseMsgs;
import Util.Network.Cookie;

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
				e.printStackTrace();
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
		ResponseMsgs serverRes = login.login();	
		boolean loginStatus = true;
		
		if (serverRes instanceof GeneralServerResponseMsgs) {
			 GeneralServerResponseMsgs obj = (GeneralServerResponseMsgs) serverRes;
			 System.out.print("\033[H\033[2J");
			 System.out.flush();
			 System.out.println (obj.getServerResponseCode());
			 System.out.println (obj.getMsg());
			 System.out.println ("\n");
			 
			 if (obj.getServerResponseCode().equals("200")) {
				 loginStatus = false;				 
			 }
			 else {
				 try {
					  Thread.sleep(3000);        
				  } 
				  catch( InterruptedException ex) {  
					   Thread.currentThread().interrupt();
				  }
			 }
		 }
		 else {
			 GeneralClientResponseMsgs obj = (GeneralClientResponseMsgs)serverRes;
			 System.out.println (obj.getMsg());
			 loginStatus = true;
		 }
		return loginStatus;		
	}
			
	public static void main(String[] args) throws IOException {		// TODO Auto-generated method stub
		boolean loginStatus = true;
		int loginAttemptCnt = 0;
		
		while (loginStatus) {	
			
			if (loginAttemptCnt > 2 ) {
				System.out.print("\033[H\033[2J");
				System.out.flush();
				System.out.println ("\nThere Seems To Be Problem In Trying To Login To The System");
				System.out.print ("Do You Wish To Continue [True / False] : ");
				loginStatus = new Scanner (System.in).hasNextBoolean();
				
				if (loginStatus == false) {
					System.exit(0);
				}
			}
			loginStatus = new Main().login();
			loginAttemptCnt++;	
		} 
		//new Main().showMainMenu();
		
		
		//Cookie c = new Cookie ();
		//System.out.println (c.getDate());
		
	}
	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
}

/*
Timer t = new Timer();

t.scheduleAtFixedRate(
    new TimerTask()
    {
        public void run()
        {
            System.out.println("3 seconds passed");
        }
    },
    0,      // run first occurrence immediately
    3000);  // run every three seconds
*/

/*
 public class Test {

    private static class Updater implements Runnable {

        @Override
        public void run() {
            System.out.println("3 seconds passed");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Updater();
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(r, 0, 3, TimeUnit.SECONDS);

        Thread.sleep(10000);
        service.shutdown();

    }
}
 * 
 */

