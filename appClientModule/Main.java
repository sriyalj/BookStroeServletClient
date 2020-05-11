import java.io.IOException;
import java.util.Base64;
import java.util.InputMismatchException;
import java.util.Scanner;

import Connections.Connection;

public class Main {
	
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println ("------------------ Online Book Store ------------------");
		System.out.println ("\n------------- Main Menu -------------");
		System.out.println ("1. Book Operations");
		System.out.println ("2. Author Operations");
		System.out.println ("3. Publisher Operations");
		System.out.println ("Please Select Your Operation [1,2,3]..Press -1 To Terminate");
		Scanner scn = new Scanner (System.in);
		int choice = 0;
		boolean cont = true;
		
		while (cont) {	
		
			try { 
				choice = scn.nextInt();
			
				switch (choice) {
					case 1 : break;
				
					case 2 : break;
				
					case 3 : break;
				
					case -1 : System.out.println ("Exiting the System");
						  	  System.exit(0);
						  
					default : System.out.println ("Invalid Option Choosen");
				}
			}
			catch (InputMismatchException e) {
				System.out.println ("You Need To Enter An Integer From 1,2,3,-1");
			}
		
		
		}
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	

}