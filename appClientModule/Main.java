import java.io.IOException;
import java.util.Base64;
import java.util.InputMismatchException;
import java.util.Scanner;

import Connections.Connections;

public class Main {
	
	private static Main mainCon;
	
	private Main () {
		
	}
	
	public static Main getMainCon () {
		if (mainCon == null) {
			mainCon = new Main();
		}		
		return mainCon;
	}
	
	void showMainMenu () {
		System.out.println("\f");
		System.out.flush();
		System.out.println ("------------------ Online Book Store ------------------");
		System.out.println ("\n------------- Main Menu -------------");
		System.out.println ("1. Book Operations");
		System.out.println ("2. Author Operations");
		System.out.println ("3. Publisher Operations");
		System.out.print   ("Please Select Your Operation [1,2,3].Press -1 To Terminate : ");
		Scanner scn = new Scanner (System.in);
		int choice = 0;		
		
		while (true) {	
		
			try { 
				choice = scn.nextInt();
			
				switch (choice) {
					case 1 : break;
				
					case 2 : AuthorOperationsUI AuthorOperationsUICon = AuthorOperationsUI.getConnection(Main.getMainCon());
							 AuthorOperationsUICon.showAuthorOperations();
							 break;
				
					case 3 : break;
				
					case -1 : System.out.println ("Exiting the System\n");
							  scn.close();
						  	  System.exit(0);
						  
					default : System.out.println ("Invalid Option Choosen. \n You Need To Enter An Integer From 1,2,3,-1");
				}
			}
			catch (InputMismatchException e) {
				System.out.println ("Invalid Input Type. \n You Need To Enter An Integer From 1,2,3,-1");
			}
			catch (Exception e) {
				System.out.println ("General Error Occured");
			}		
		}
		
	}
			
	public static void main(String[] args) {		// TODO Auto-generated method stub
		Main.getMainCon().showMainMenu();		
	}
	
	

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	

}