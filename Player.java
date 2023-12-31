import java.util.ArrayList;


public class Player {
	private String name;
	private String color;
	private ArrayList<Card> hand;
	
	public Player(String name, String color) {
		this.name = name;
		this.color = color;
		this.hand = new ArrayList<Card>();
	}
	
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public ArrayList<Card> getHand(){
		return hand;
	}
}
