import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Connections.AuthorConnections;
import Entity.Author;

public class AuthorOperationsUI {
	
	private static AuthorOperationsUI con;
	private Main mainCon;
	private Scanner scn;
	
	private AuthorOperationsUI (Main mainCon) {
			this.mainCon = mainCon;
	}
	
	public static AuthorOperationsUI getConnection (Main mainCon) {
		if (con == null) {
			con =  new AuthorOperationsUI (mainCon);
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
			System.out.println ("4. View Autor Details");
			System.out.println ("5. View All Autor Details");
			System.out.print ("Please Select Your Operation [1,2,3,4,5]..Press -1 To Terminate : ");
			Scanner scn = new Scanner (System.in);
		
			int choice = 0;			
		
			try { 
				choice = scn.nextInt();
			
				switch (choice) {
					case 1 : String response = addNewAuthorUI ();
							 System.out.println ("\n");
							 System.out.println("\f");
							 System.out.flush();
						     System.out.println (response);
							 
							 new java.util.Timer().schedule( 
								new java.util.TimerTask() {
									@Override
									public void run() {
										//mainCon.showMainMenu();
									}
								}, 
								5000 
							 );
							 scn.close();
							 break;
				
					case 2 : break;
				
					case 3 : break;
					
					case 4 : break;
					
					case 5 : break;
				
					case -1 : System.out.println ("Exiting the System");
							  scn.close();
						  	  System.exit(0);
						  
					default : System.out.println ("Invalid Option Choosen. \n You Need To Enter An Integer From 1,2,3,-1");
				}
			}
			catch (InputMismatchException e) {
				System.out.println ("You Need To Enter An Integer From 1,2,3,-1");
				scn.close();
			}
			catch (Exception e) {
				System.out.println ("General Error Occured");
				scn.close();
				mainCon.showMainMenu();
			}	
		}
	}
	
	String addNewAuthorUI () {
		
		String fstName, mdleName, lastName, originCountry, status= "";
		scn =  new Scanner (System.in).useDelimiter("\n");;
		
		System.out.println ("\n");
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println ("\n------------------ Online Book Store ------------------");
		System.out.println ("\n------------- Add New Author -------------");
		
		try {
			System.out.print ("\n Enter Author First Name :");
			fstName = scn.next();
			System.out.print ("\n Enter Author Middle Name :");
			mdleName = scn.next();
			System.out.print ("\n Enter Author Last Name :");
			lastName = scn.next();
			System.out.print ("\n Enter Author Origin Country :");
			originCountry = scn.next();
			
			Author obj = new Author (fstName,mdleName,lastName,originCountry);
			AuthorConnections con = AuthorConnections.getConnection();
			
			try {
				status = con.addNewAuthor(obj);					
			}
			catch (MalformedURLException e) {
				status = e.getMessage();			
			}
			catch (ProtocolException e) {
				status = e.getMessage();
			}
			catch (IOException e) {
				status = "\n\n Sorry. Something Went Worng";
			}
		}
		catch (Exception e) {
			showAuthorOperations();
		}
	  return status;
	}

}
