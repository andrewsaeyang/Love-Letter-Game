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
		Player[] players = tempPlayer();
		Stack<Card> gameDeck = DeckBuilder.buildDeck();	//Builds the deck

		/*
		 * Main loop for the game structure. 
		 * This level will take care of re-shuffling of the deck, resetting player state to active, and burning cards
		 * before looping through it will check if any players have hit the win condition
		 */

		boolean gameState = true;
		boolean roundState = true;
		int turnMarker = 0;
		int activePlayers = nPlayers;

		while(gameState == true) {
			burnCard(gameDeck, nPlayers); // number of cards burned depends on the number of players

			for (int i=0; i<nPlayers; i++) {
				players[i].setPlayerHand(gameDeck.pop(), 0); //puts the top card of the deck into each players hand
			}
 
			/*
			 * this loop will be the the bulk of the playing 
			 * this level includes: drawing from the deck, playing cards and eliminating players in order to give player points
			 * before looping through it will check if there is at least 2 players playing
			 */

			while(roundState == true) {

				Scanner scanner = new Scanner(System.in);
				
				players[turnMarker].resetHandmaid();
				System.out.println("It's " + players[turnMarker].getName() + "'s turn.");
				System.out.println("");
				players[turnMarker].setPlayerHand(gameDeck.pop(), 1);
				
				players[turnMarker].printPlayerHand();
				System.out.println("");

				int choice = getCardChoice(countessCheck(players[turnMarker].getPlayerHand()), players[turnMarker]);

				

				//System.out.println("Player 2 has " + players[1].getPlayerHand()[0].getVal()); // for testing purpose

				playCard(players, turnMarker, choice-1, nPlayers, handmaidCheck(players, nPlayers), gameDeck);

				if(turnMarker != nPlayers-1) {
					turnMarker++;
				}else {
					turnMarker = 0;
				}

				System.out.println("=========================================");

				/*
				 * TODO: Check to see if more than 2 players remain in round (adjusts active player count)
				 * TODO: Check to see if there are cards remaining in the deck
				 * TODO: Go to next player
				 * TODO: Check for if player has countess along with prince or King
				 */

			}
			break;
			/*
			 * TODO: Game clean up:
			 * TODO: Check game winning condition
			 * TODO: ->if winner applicable, announce winner and end game.
			 * TODO: Shuffle deck
			 * TODO: Deal new card
			 * TODO: Reset player info(still in round status, hand maid status, hand)
			 * TODO: Display leader board
			 *
			 */
		}
	}



	//TEST CASE FOR 2 PLAYER GAME
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
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
		if( p == 2) {
			System.out.println("The three cards burned are: ");
			for (int i=0; i <3;i++) {
				System.out.println(n.pop().getName());

			}
		}else {
			System.out.println("burning one card.");
			n.pop();
		}

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
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

	/*
	 * Inputs the number of people playing the game and returns the player's target
	 */
	public static int getTarget(int p, int currentPlayer) {
		int t = 0;
		Scanner numObj1 = new Scanner(System.in);
		do {
			System.out.println("Which player would you like to target?");

			try {
				t = numObj1.nextInt();

				if(t-1 == currentPlayer) {

					System.out.println("You cannot target your self! Please Select a valid player.");

				}else if (t < 1 || t > p) {

					System.out.println("Not a valid number!!!");

				}
			}
			catch(InputMismatchException e){
				System.out.println("That wasnt a number yo...");
				numObj1.next();
			}
		}while(t-1 == currentPlayer|| t < 1||t > p); 
		//numObj.close();
		return t-1;

	} 

	public static int getCardChoice(boolean b, Player p) {
		int choice = 0;
		
		Scanner scanner = new Scanner(System.in);
		
		if(b) {

			do {
				System.out.println("Which card would you like to play?");
				System.out.println("Card 1 or 2?");

				try {
					choice = scanner.nextInt();
					
					
					if(choice <1 || choice >2) {
						System.out.println("Not a valid choice!!!");
						System.out.println("Please choose 1 or 2.");
						
					}else if(p.getPlayerHand()[choice-1].getVal() == 5 || p.getPlayerHand()[choice-1].getVal() == 6) {
						
						System.out.println("You cannot play this card while you have the Countess in your hand!");

					}

				}
				catch(InputMismatchException e){
					System.out.println("That wasnt a number yo...");
					scanner.next();
				}
			}while(choice < 1 || choice > 2 ||p.getPlayerHand()[choice-1].getVal() == 5 || p.getPlayerHand()[choice-1].getVal() == 6); 
			
			
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
			
		}
		
		
		return choice;
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


	/*
	 * n being number of players in the game
	 * This method determines the status of "Handmaid" among all the players.
	 * If there is any instance where everyone but 1 player has the "Handmaid" status active,
	 * The active player is limited on their action depending on their card. The active player
	 * will never have "Handmaid" active so of the active statuses will always be targets (which cannot be targeted) 
	 */
	public static boolean handmaidCheck(Player[] p, int n) { 

		int maidCount = 0;

		for (int i = 0; i < n; i++) {
			if (p[i].getHandmaid() == true) {
				maidCount++;
			}
		}

		if(maidCount == n-1) {
			return true;
		}else {
			return false;
		}
	}

	public static boolean countessCheck(Card[] hand) {

		boolean retVal = false;

		if(hand[0].getVal() == 7 ||hand[1].getVal() == 7) {

			for(int i = 0; i <2; i++) {

				if(hand[i].getVal() == 5 || hand[i].getVal() == 6 ) {
					
					retVal = true;
				}
			}
		}

		return retVal;
	}


	/*TODO: Add logic so you can't target your self...
	 * players - the array containing all the players
	 * t - represents the current player's turn
	 * c - represents which card the player is choosing from their hand (array size of 2)
	 * nPlayers - number of players in the game for edge cases
	 * h - whether or not all targets have handmaid active
	 */



	/*
	 * Cards that can't target yourself:
	 * 1, 2, 3, 6 
	 */
	private static void playCard(Player[] players, int t, int c, int nPlayers, boolean h, Stack<Card> gameDeck) {


		Scanner numObj = new Scanner(System.in);

		int targetCardVal = 0;
		Player targetPlayer = new Player();
		/*
		 * problem with putting the shift here. If index 0 is chosen, index 1 will be the card selected because the shift will occur BEFORE card has been selected and used.
		 *if (c==0) {
		 *	players[t].getPlayerHand()[0] = players[t].getPlayerHand()[1]; // Cards are always dealt to [1] so it just shifts it over if needed
		 *}
		 */





		switch(players[t].getPlayerHand()[c].getVal()) {

		case 8: // if you discard this card, you are out of the round

			System.out.println(players[t].getName() + " has discarded the Princess. They are out of the round."); 

			players[t].out();

			break;

		case 1: //Player designates another player and names a type of card. If that player's hand matches the type of card specified, that player is eliminated from the round. Guard cannot be named as the type of card.

			if(h == false) {

				targetPlayer = players[getTarget(nPlayers, t)];
				do {

					System.out.println("Which card do you think " + targetPlayer.getName() + " has? Pick a number between 2 and 8");

					try {
						targetCardVal = numObj.nextInt();
						if(targetCardVal == 1) {
							System.out.println("Cannot guess another Guard!!!");

						}else if(targetCardVal <2 || targetCardVal >8) {
							System.out.println("Not a valid number!!!");

						}
					}
					catch(InputMismatchException e){
						System.out.println("That wasnt a number yo...");
						numObj.next();
					}
				}while(targetCardVal < 2 || targetCardVal > 8 );

				//numObj.close();
				if (targetCardVal == targetPlayer.getPlayerHand()[0].getVal()) {
					System.out.println("That's correct!");
					targetPlayer.out();
				}else {
					System.out.println("Not Quite!");
				}


			}else{
				System.out.println("The are no valid targets. Skipping turn");
			}
			if (c==0) {
				players[t].getPlayerHand()[0] = players[t].getPlayerHand()[1]; // Cards are always dealt to [1] so it just shifts it over if needed
			}
			break;

		case 2:

			if(h==false) {
				targetPlayer = players[getTarget(nPlayers, t)];
				if (c==0) {
					players[t].getPlayerHand()[0] = players[t].getPlayerHand()[1]; // Cards are always dealt to [1] so it just shifts it over if needed
				}		
				System.out.println(targetPlayer.getPlayerHand()[0].getName()) ;
			}else {
				System.out.println("The are no valid targets. Skipping turn");
			}
			break;

		case 3: //You and another player secretly compare hands. The player with the lower value is out of the round

			if (c==0) {
				players[t].getPlayerHand()[0] = players[t].getPlayerHand()[1]; // Cards are always dealt to [1] so it just shifts it over if needed
			}

			if(h==false) {
				targetPlayer = players[getTarget(nPlayers, t)];
				if (players[t].getPlayerHand()[0].getVal() > targetPlayer.getPlayerHand()[0].getVal()) {

					System.out.println(players[t].getName() + " has the better hand!");
					targetPlayer.out();

				}else if(players[t].getPlayerHand()[0].getVal() < targetPlayer.getPlayerHand()[0].getVal()) {

					System.out.println(targetPlayer.getName() + " has the better hand!");

					players[t].out();

				}else {

					System.out.println("It was a tie! *Le Gasp*");

				}
			}else {
				System.out.println("The are no valid targets. Skipping turn");
			}
			break;

		case 4://Until your next turn, ignore all effects from other player's cards

			players[t].handmaid();

			if (c==0) {
				players[t].getPlayerHand()[0] = players[t].getPlayerHand()[1]; // Cards are always dealt to [1] so it just shifts it over if needed
			}
			System.out.println(players[t].getName() + " has played Handmaid. They cannot be targeted until the beginning of thier next turn.");
			break;

		case 5://Choose any player (including yourself) to discard his or her hand and draw a new card




			int temp = 0;
			Scanner numObj1 = new Scanner(System.in);
			do {
				System.out.println("Which player should discard thier hand?");

				try {
					temp = numObj1.nextInt();

					if (temp < 1 || temp > nPlayers) {
						System.out.println("Not a valid number!!!");

					}
				}
				catch(InputMismatchException e){
					System.out.println("That wasnt a number yo...");
					numObj1.next();
				}
			}while(temp < 1||temp > nPlayers); 
			//numObj.close();


			/*

			int temp = 0;
			do {
				System.out.println("Which player should discard thier hand?");

				try {
					temp = numObj.nextInt();
					if(temp < 0 || temp >nPlayers) {
						System.out.println("Not a valid number!!!");
					}
				}
				catch(InputMismatchException e){
					System.out.println("That wasnt a number yo...");
					numObj.next();
				}
			}while(!(temp <0 || temp >nPlayers)); 


			 */


			if (c==0) {
				players[t].getPlayerHand()[0] = players[t].getPlayerHand()[1]; // Cards are always dealt to [1] so it just shifts it over if needed
			}

			System.out.println("Card before discard is: " + players[t].getPlayerHand()[0].getName() );

			// if the target player is discarding a princess, they are out of the round, otherwise, card is discarded and new hand is drawn.

			temp--; // ie. player 2 is index 1

			if(players[temp].getPlayerHand()[0].getVal()==8) {
				players[temp].out();
				System.out.println(players[temp] + " has discarded the Princess. They are out of the round.");
			}else {
				players[temp].getPlayerHand()[0] = gameDeck.pop();
			}

			System.out.println("Card after discard is: " + players[t].getPlayerHand()[0].getName() );

			break;

		case 6://Trade hands with another player of your choice

			if(h==false) {
				targetPlayer = players[getTarget(nPlayers, t)];

				if (c==0) {
					players[t].getPlayerHand()[0] = players[t].getPlayerHand()[1]; // Cards are always dealt to [1] so it just shifts it over if needed
				}

				Card tCard = players[t].getPlayerHand()[0];
				players[t].getPlayerHand()[0] = targetPlayer.getPlayerHand()[0] ;
				targetPlayer.getPlayerHand()[0] = tCard;
			}else {

				System.out.println("The are no valid targets. Skipping turn");
			}
			break;

		case 7://If you have this card and the King or Prince is in your hand, you must discard this card

			if (c==0) {
				players[t].getPlayerHand()[0] = players[t].getPlayerHand()[1]; // Cards are always dealt to [1] so it just shifts it over if needed
			}

			break;

		}
		
		System.out.println();

	}

}
