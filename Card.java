
public class Card {
	private String cardName;
	private boolean selected = false;
	
	public Card(String cardName) {
		this.cardName = cardName;
	}
	
	public String getCardName() {
		return this.cardName;
	}
	
	public String toString() {
		return this.cardName;
	}
	
	public void toggleSelect() {
		selected = !selected;
	}
	
	public boolean isSelected() {
		return selected;
	}
}
