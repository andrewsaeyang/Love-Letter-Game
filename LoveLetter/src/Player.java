
public class Player {
	private String playerName;
	private int playerPoints;
	private Card[] playerHand = new Card[2];
	
	
	public Player(String n) {
		playerName = n;
		playerPoints = 0;
	}
	public void setPlayerName(String n) {
		playerName = n;
	}
	
	public void setPlayerPoints(String p) {
		playerName = p;
	}
	
	//index 0 will be player current hand, index 1 is new card
	public void setPlayerHand(Card n, int slot) {
		playerHand[slot] = n;
	}
	
	public void win() {
		playerPoints++;
	}
	
	
}
