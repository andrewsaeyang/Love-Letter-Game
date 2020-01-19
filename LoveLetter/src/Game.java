import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;


public class Game {
	public static void main(String[] args) throws IOException
	{


		//Asks the player for how many people are playing and validates
		int numOfPlayers = getNumberofPLayers();
		
		Player[] playerList = playerList(numOfPlayers);
		
		Stack<Card> gameDeck = DeckBuilder.buildDeck();	//Builds the deck
	

		


		/*
		 * GAME START
		 * 
		 * 
		 * Steps brain storming:
		 * use numPLayer to build the active players into array
		 * deal to players in the array
		 * 
		 * 
		 */



		/*
		 * Main loop for the game structure. 
		 * This level will take care of re-shuffling of the deck, resetting player state to active, and burning cards
		 * before looping through it will check if any players have hit the win condition
		 */

		boolean gameState = true;
		boolean roundState = true;
		
		

		while(gameState == false) {

			/*
			 * this loop will be the the bulk of the playing 
			 * this level includes: drawing from the deck, playing cards and eliminating players in order to give player points
			 * before looping through it will check if there is at least 2 players playing
			 */
			while(roundState == false) {

			}
		}
	}

	/*
	 * GAME DONE
	 */




public static Player[] playerList(int n) {
	Scanner name = new Scanner(System.in);
	Player[] p = new Player[n];
	for (int i=0; i< n; i++) {
		
		System.out.println("What is the name of player " + (i+1));
		//Ask for Player name
		p[i] = new Player(name.next());
	}
	name.close();
	return p;
	
}


	public static int getNumberofPLayers() {
		int numPlayer = 0;
		Scanner numObj = new Scanner(System.in);
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
		}while(!(numPlayer == 2 || numPlayer == 3 || numPlayer == 4)); {
			//numObj.close();
			return numPlayer;
		}	
	} static void buildDeck() {

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
	//Name a non-Guard card and choose another player. If that player has that card, he or she is out of the round
	public static void playGaurd() {}

	//Look at another player's hand
	public static void playPriest() {}

	//You and another player secretly compare hands. The player with the lower value is out of the round
	public static void playBaron() {}

	//Until your next turn, ignore all effects from other player's cards
	public static void playHandmaid() {}

	//Choose any player (including yourself) to discard his or her hand and draw a new card
	public static void playPrince() {}

	//Trade hands with another player of your choice
	public static void playKing() {}

	//If you have this card and the King or Prince is in your hand, you must discard this card
	public static void playCountess() {}

	// if you discard this card, you are out of the round
	public static void playPrincess() {}
}





