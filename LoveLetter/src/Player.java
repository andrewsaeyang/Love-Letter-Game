
public class Player {
	private boolean handmaid = false;
	private boolean stillInRound = true;
	private String playerName;
	private int playerPoints;
	private Card[] playerHand = new Card[2];

	

	public Player(String n) {
		playerName = n;
		playerPoints = 0;
		handmaid = false;
		stillInRound = true;

	}

	public Player() {
		playerName = "";
		playerPoints = 0;
		handmaid = false;
		stillInRound = true;
		
	}
	
	public String getName() {
		return playerName;
	}
	public void setPlayerName(String n) {
		playerName = n;
	}

	public int getPlayerPoints() {
		return playerPoints;
	}

	public void setPlayerPoints(String p) {
		playerName = p;
	}

	public void handmaid() {
		handmaid = true;
	}
	public boolean getHandmaid() {
		return handmaid;
	}

	public boolean getRoundInfo() {
		return stillInRound;
	}

	public void out() {
		stillInRound = false;
	}

	public Card[] getPlayerHand() {
		return playerHand;
	}
	public void printPlayerHand() {
		for (int i=0; i< playerHand.length; i++) {
			if (playerHand[i] == null) {
				System.out.println("[X]");
			}else {
				System.out.println(playerHand[i].info());
			}
		}
		
		
	}
	//index 0 will be player current hand, index 1 is new card
	public void setPlayerHand(Card n, int slot) {
		playerHand[slot] = n;
	}

	public void win() {
		playerPoints++;
	}

	public void reset() {
		handmaid = false;
		stillInRound = true;
	}

	public void resetHandmaid() {
		handmaid = false;
		
	}
	
	public int getHandVal() {
		
		return playerHand[0].getVal();
	}


}
