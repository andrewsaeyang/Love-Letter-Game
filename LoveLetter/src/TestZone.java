import java.util.InputMismatchException;
import java.util.Scanner;

public class TestZone {
	public static void main(String[] args) {
		
		Card countess = new Card(7, 1, "Countess"," yes" );
		Card king = new Card(6, 1, "King"," yes" );
		Card prince = new Card(5, 1, "Prince"," yes" );
		

		
		Card[] hand = {countess, king};
		Card[] hand2 = {countess, prince};
		Card[] hand3 = {prince, countess};
		Card[] hand4 = {prince, king};
		
		
	
		System.out.println(Game.countessCheck(hand4));
		
			
Scanner scanner = new Scanner(System.in);	


int choice = 0;

		if(Game.countessCheck(hand4)) {

			do {
				System.out.println("Which card would you like to play?");
				System.out.println("Card 1 or 2?");

				try {
					choice = scanner.nextInt();
					
					
					if(choice <1 || choice >2) {
						System.out.println("Not a valid choice!!!");
						System.out.println("Please choose 1 or 2.");
						
					}else if(hand[choice-1].getVal() == 5 || hand[choice-1].getVal() == 6) {
						
						System.out.println("You cannot play this card while you have the Countess in your hand!");

					}
					 

				}
				catch(InputMismatchException e){
					System.out.println("That wasnt a number yo...");
					scanner.next();
				}
			}while(choice < 1 || choice > 2 || hand[choice-1].getVal() == 5 || hand[choice-1].getVal() == 6); 
			
			System.out.println(choice + " made it through");


		}else {

			do {
				System.out.println("Which card would you like to play?");
				System.out.println("Card 1 or 2?");

				try {
					choice = scanner.nextInt();
					if(!(choice == 2 || choice == 1)) {
						System.out.println("Not a valid choice!!!");
						System.out.println("Please choose 1 or 2.");
					}

				}
				catch(InputMismatchException e){
					System.out.println("That wasnt a number yo...");
					scanner.next();
				}
			}while(!(choice == 2 || choice == 1));
			
			System.out.println(choice + " made it through");

		}

		
	}
}
