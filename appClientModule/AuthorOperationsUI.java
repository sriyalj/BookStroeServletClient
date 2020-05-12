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
		
		while (true) {	
		
			try { 
				choice = scn.nextInt();
			
				switch (choice) {
					case 1 : addNewAuthorUI ();
							 break;
				
					case 2 : break;
				
					case 3 : break;
					
					case 4 : break;
					
					case 5 : break;
				
					case -1 : System.out.println ("Exiting the System");
							  scn.close();
						  	  System.exit(0);
						  
					default : System.out.println ("Invalid Option Choosen");
				}
			}
			catch (InputMismatchException e) {
				System.out.println ("You Need To Enter An Integer From 1,2,3,-1");
			}
			catch (Exception e) {
				System.out.println ("General Error Occured");
				mainCon.showMainMenu();
			}	
		}
	}
	
	void addNewAuthorUI () {
		
		String fstName, mdleName, lastName, originCountry = "";
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
				con.addNewAuthor(obj);
			}
			catch (MalformedURLException e) {
				System.out.println ("MalformedURLException");
			}
			catch (ProtocolException e) {
				System.out.println ("ProtocolException");
			}
			catch (IOException e) {
				System.out.println ("\n\n Sorry. Something Went Worng");
				Main.getMainCon();
			}
		}
		catch (Exception e) {
			showAuthorOperations();
		}
		
	}

}
