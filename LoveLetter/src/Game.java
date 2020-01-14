import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Game {
	public static void main(String[] args) throws IOException
	{

		
		Scanner numObj = new Scanner(System.in);
		int numPlayer=0;
		
		//Asks the player for how many people are playing and validates
		do {
			System.out.println("How many players yo? Enter a number between 2-4.");
			
			try {
				numPlayer = numObj.nextInt();
				if(numPlayer <2 || numPlayer >4) {
					System.out.println("Not a valid number!!!");
				}
			}
			catch(InputMismatchException e){
				System.out.println("That wasnt a number yo...");
				numObj.next();
			}
		}while(numPlayer == 2 || numPlayer == 3 || numPlayer == 4); {
			System.out.println("number of players is " + numPlayer);
			
			}
			
		
		
		
		
		/*
		 * DECK SET UP
		 */
		
		//File vanilla = new File("C:\\Users\\Blue Fox\\git\\Love-Letter-Game\\LoveLetter\\LLDeck.txt"); //for Laptop
		File vanilla = new File("C:\\Users\\a09sa\\git\\Love-Letter-Game\\LoveLetter\\LLDeck.txt"); //for PC
		
		
		Card[] deck = new Card[16]; // initiate the deck array
		Card[] gameDeck = new Card[16];
		//deck = buildDeck();

		Scanner file = new Scanner(vanilla);
		file.useDelimiter("<"); //my delimiter used

		int b = 0; //Counter holder I think?
		int cardNum = 0;
		int cardQuantity = 0;
		String cardName =" ";
		String cardDescription = " ";


		//Card guard = new Card(p,q,n,d); //Card obj constructor

		/*This while loops is able to build an array of card objects
		 *while while pulling them from the text file
		 */
		while(file.hasNext()){

			cardNum = file.nextInt();
			cardQuantity = file.nextInt();
			cardName = file.next();
			cardDescription = file.nextLine();

			/*
			 * This for loop is creating the multiple copies of the cards with variable "q" being quantity
			 */
			for(int x = 0; x < cardQuantity; x++ ){
				deck[b] = new Card(cardNum,cardQuantity,cardName,cardDescription);                
				//System.out.println(deck[i].toString()); //Testing if building card works                
				b++;
			}

			/* 
			 * TEST CODE
			 */
			//System.out.print(file.next()+" ");//Card power
			//System.out.print(file.next()+" ");// number of cards
			//System.out.print(file.next()+" ");//name
			//System.out.println(file.next());//description

		}
		file.close(); //gotta close them files!

		//printDeckIndex(deck);
		//System.out.println(); // Spaces out line in the prompt
		//System.out.println("===================================================================");

		//Now that the deck[] array is built, time to shuffle!

		gameDeck = shuffleDeck(deck);

		//checking randomness of deck
		//printDeckIndex(gameDeck); 

		//printDeckIndex(deck);

		/*
		 * DECK SET UP DONE
		 */


	}
public static void buildDeck() {
	
}
	//prints out what card is in each index
	public static void printDeckIndex (Card[] z){
		for(int i = 0; i < 16;i++){
			if(z[i]==null){
				System.out.println("index " + z +" is null.");

			}else{
				System.out.println("The card in index " + i + 
						" is " + z[i].getName() );
			}         
		}

	}

	/*The shuffle method for the game
	 * 
	 * https://learnappmaking.com/shuffling-array-swift-explained/
	 * https://www.youtube.com/watch?v=tLxBwSL3lPQ
	 * 
	 */
	public static Card[] shuffleDeck (Card[] z){
		Random rand = new Random();
		Card temp = new Card();
		for (int i = 1 ; i <z.length; i++){
			int randomNum = rand.nextInt(z.length-i); // we  but we want a range from 0-14
			temp = z[randomNum];
			z[randomNum] = z[z.length-i];
			z[z.length-i] = temp;

		}
		/* 
		 * prints array to test
		 * printDeckIndex(z);
		 * System.out.println("================================");
		 */
		return z;
	}
}


