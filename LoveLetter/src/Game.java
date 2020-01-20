import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;


public class Game {
	public static void main(String[] args) throws IOException, InterruptedException
	{



		//int nPlayers = getNumberofPLayers(); //Asks the player for how many people are playing and validates
		//Player[] playerList = playerList(nPlayers); //creates the array storing player data for game


		int nPlayers = 2;
		Player[] player = tempPlayer();
		Stack<Card> gameDeck = DeckBuilder.buildDeck();	//Builds the deck

		/*
		 * GAME START
		 * 
		 * Steps brain storming:
		 * use numPLayer to build the active players into array
		 * deal to players in the array
		 * 
		 */

		/*
		 * Main loop for the game structure. 
		 * This level will take care of re-shuffling of the deck, resetting player state to active, and burning cards
		 * before looping through it will check if any players have hit the win condition
		 */

		boolean gameState = true;
		boolean roundState = true;
		int turnMarker = 0;

		while(gameState == true) {
			burnCard(gameDeck, nPlayers); // number of cards burned depends on the number of players

			for (int i=0; i<nPlayers; i++) {
				player[i].setPlayerHand(gameDeck.pop(), 0); //puts the top card of the deck into each players hand
			}

			/*
			 * this loop will be the the bulk of the playing 
			 * this level includes: drawing from the deck, playing cards and eliminating players in order to give player points
			 * before looping through it will check if there is at least 2 players playing
			 */

			

			while(roundState == true) {

				Scanner scanner = new Scanner(System.in);
				int choice = 0;
				System.out.println("It's " + player[turnMarker].getName() + "'s turn.");
				System.out.println("");
				player[turnMarker].setPlayerHand(gameDeck.pop(), 1);
				player[turnMarker].printPlayerHand();
				System.out.println("");


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
				}while(!(choice == 2 || choice == 1)); {
					
					//return numPlayer;
				}	

				playCard(player[turnMarker], choice-1);


				/*
				 * Round clean up:
				 * -check to see if more than 2 players remain in round
				 * -check to see if there are cards remaining in the deck
				 * -push cards in player array to the left (if possible)
				 * 
				 */

			}
			break;
			/*
			 * Game clean up:
			 * -Award round winner point
			 * -Check game winning condition
			 * --if winner applicable, announce winner
			 * -Shuffle deck
			 * -Deal new card
			 * -Reset player info(still in round status, hand maid status, hand)
			 * -display leader board
			 */
		}
	}


	private static Player[] tempPlayer() {
		Player[] p = new Player[2];
		p[0] = new Player("Andrew");
		p[1] = new Player("Josh");
		return p;
	}

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

	public static void burnCard(Stack<Card> n, int p) {

		if( p == 2) {
			for (int i=0; i <4;i++) {
				n.pop();
			}
		}else {
			n.pop();
		}
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


	private static void playCard(Player player, int c) {
		// TODO Auto-generated method stub
		
		
		switch(player.getPlayerHand()[c].getVal()) {

		case 1:
			System.out.println("Card is Guard");
			break;

		case 2:
			System.out.println("Card is Priest");
			break;

		case 3:
			System.out.println("Card is Baron");
			break;

		case 4:
			System.out.println("Card is Handmaid");
			break;

		case 5:
			System.out.println("Card is Prince");
			break;

		case 6:
			System.out.println("Card is King");
			break;

		case 7:
			System.out.println("Card is Countess");
			break;

		case 8:
			System.out.println("Card is Princess");
			break;


		}
	}

	//Name a non-Guard card and choose another player. If that player has that card, he or she is out of the round
	public static void playGaurd(Player t) {}

	//Look at another player's hand
	public static void playPriest(Player t) {
		System.out.println(t.getName() + "has the "+ t.getPlayerHand()[0].getName());
	}

	//You and another player secretly compare hands. The player with the lower value is out of the round
	public static void playBaron(Player p, Player t) {}

	//Until your next turn, ignore all effects from other player's cards
	public static void playHandmaid(Player p) {
		p.handmaid();
	}

	//Choose any player (including yourself) to discard his or her hand and draw a new card
	public static void playPrince(Player t) {}

	//Trade hands with another player of your choice
	public static void playKing(Player p, Player t) {}

	//If you have this card and the King or Prince is in your hand, you must discard this card
	public static void playCountess(Player p) {	}

	// if you discard this card, you are out of the round
	public static void playPrincess(Player p) {
		p.out();
	}

}





