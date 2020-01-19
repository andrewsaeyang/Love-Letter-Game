
public class Card {
	// instance variables - replace the example below with your own
	private int cardVal;
	private int cardQuan;
	private String cardName; 
	private String cardDesc; 

	/**
	 * Constructor for objects of class Card
	 */
	public Card(int v,int q, String n, String d)
	{
		// Initialize instance variables
		cardVal = v;
		cardQuan = q;
		cardName = n;
		cardDesc = d;        
	}

	public Card() {
	};

	//copy method, not sure why i made this lol
	public Card (Card that) {

		this(that.getVal(),that.getQuan(),that.getName(),that.getDesc()); 
	}

	public String toString(){
		return cardName + " has a value of " + cardVal + " with " + cardQuan + " number of card(s) in the deck. ";
	}

	public int getVal()
	{
		return cardVal;
	}

	public void setVal(int cardVal){this.cardVal=cardVal;}

	public int getQuan()
	{
		return cardQuan;
	}

	public void setQuan(int cardQuan){this.cardQuan=cardQuan;}

	public String getName()
	{
		return cardName;
	}

	public void setString (String cardName){ this.cardName = cardName;}

	public String getDesc()
	{
		return cardDesc;
	}

	public void setDesc(String cardDesc){this.cardDesc = cardDesc;}


}

