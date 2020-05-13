import java.io.IOException;
import java.util.Base64;
import java.util.InputMismatchException;
import java.util.Scanner;

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
			String response = "";
			scn =  new Scanner (System.in);
			System.out.println("\f");
			System.out.flush();
			System.out.println ("------------------ Online Book Store ------------------");
			System.out.println ("\n------------- Main Menu -------------");
			System.out.println ("1. Book Operations");
			System.out.println ("2. Author Operations");
			System.out.println ("3. Publisher Operations");
			System.out.print   ("Please Select Your Operation [1,2,3].Press -1 To Terminate : ");
			
			int choice = 0;		
				
			try { 				
				choice = scn.nextInt();
							
				switch (choice) {
					case 1 : break;
				
					case 2 : AuthorOperationsUI AuthorOperationsUICon = AuthorOperationsUI.getConnection();
							 response = AuthorOperationsUICon.showAuthorOperations();
							 System.out.println ("\n");
						     System.out.print("\033[H\033[2J");
					  		 System.out.flush();
							 System.out.println (response);
							 
							 new java.util.Timer().schedule( 
								new java.util.TimerTask() {
									@Override
									public void run() {
										//mainCon.showMainMenu();
									}
								}, 
								25000 
							 );
							 break;
				
					case 3 : break;
				
					case -1 : System.out.println ("\n");
						      System.out.print("\033[H\033[2J");
					  		  System.out.flush();
					  		  System.out.println ("Exiting the System\n");
						  	  System.exit(0);
						  
					default : System.out.println ("\n");
							  System.out.print("\033[H\033[2J");
							  System.out.flush();
							  System.out.println ("Invalid Option Choosen. \nYou Need To Choose An Integer From 1,2,3,-1");
							  
							  new java.util.Timer().schedule( 
										new java.util.TimerTask() {
											@Override
											public void run() {
												
											}
										}, 
										25000 
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
								
							}
						}, 
						25000 
					 );
			}
			catch (Exception e) {
				System.out.println ("\n");
				System.out.print("\033[H\033[2J");
                System.out.flush();
				System.out.println ("\nGeneral Error Occured. \nExiting The System");
				e.printStackTrace();
				
				new java.util.Timer().schedule( 
						new java.util.TimerTask() {
							@Override
							public void run() {
								System.exit(0);
							}
						}, 
						25000 
					 );
				
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