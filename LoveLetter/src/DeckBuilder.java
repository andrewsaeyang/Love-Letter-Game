import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import java.util.Random;

/**
 * 
 * @author Andrew saeyang
 *
 */
public class DeckBuilder {

	public static Stack<Card> buildDeck() throws IOException
	{

		File vanilla = new File("LLDeck.txt");

		Card[] deck = new Card[16]; // initiate the deck array
		Stack<Card> gameDeck = new Stack<Card>();
		//deck = buildDeck();

		Scanner file = new Scanner(vanilla);
		file.useDelimiter("<"); //my delimiter used

		int b = 0; //Counter holder I think?
		int cardNum = 0;
		int cardQuantity = 0;
		String cardName =" ";
		String cardDescription = " ";

		/*This while loops is able to build an array of card objects
		 *while while pulling them from the text file
		 */
		while(file.hasNext()){

			cardNum = file.nextInt();
			cardQuantity = file.nextInt();
			cardName = file.next();
			cardDescription = file.next();
			file.nextLine();
			/*
			 * This for loop is creating the multiple copies of the cards with variable "q" being quantity
			 */
			for(int x = 0; x < cardQuantity; x++ ){
				deck[b] = new Card(cardNum,cardQuantity,cardName,cardDescription);                
				//System.out.println(deck[i].toString()); //Testing if building card works                
				b++;
			}

			}
		file.close(); //gotta close them files!
		
		//Now that the deck[] array is built, time to shuffle!
		deck = shuffleDeck(deck);
		//printDeckIndex(deck);

		//Put the deck into a stack
		for(int i = deck.length; i >0; i--) {
			gameDeck.push(deck[i-1]);
			//System.out.println(gameDeck.peek().toString());

		}

		
		/*
		 * GAME SETUP DONE
		 */

		return gameDeck;

	}

	
	/** Prints out what card is in each index. Used for testing purposes.
	 * 
	 * @param The game deck
	 */
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
	 
	/**The shuffle method for the game
	 * 
	 * https://learnappmaking.com/shuffling-array-swift-explained/
	 * https://www.youtube.com/watch?v=tLxBwSL3lPQ
	 *
	 * 
	 * @param z The array containing the deck
	 * @return An array of the shuffled deck
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
	
		return z;
	}
}
