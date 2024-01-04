import java.util.ArrayList;
import java.awt.Color;


public class Player {
	private String name;
	private Color color;
	private ArrayList<Card> hand;
	
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
		this.hand = new ArrayList<Card>();
	}
	
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	public ArrayList<Card> getHand(){
		return this.hand;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Color getColor() {
		return this.color;
	}
	
}
