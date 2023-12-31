import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> deck;
	
	public Deck() {
		this.deck = setDeck();
	}
	
	private ArrayList<Card> setDeck() {
		String[] suits = {"C", "D", "H", "S"};
		//Jack is special: 1J = 1 eyed Jack; 2J = 2 eyed Jack
		String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Q", "K", "A"};
		String[] jacks = {"1J", "2J"};
		
		//size of 2 decks of cards
		ArrayList<Card> deck = new ArrayList<Card>();
		for(String suit : suits) {
			for(String rank : ranks) {
				String cardName = rank + suit;
				Card card = new Card(cardName);
				deck.add(card);
			}
			for(String jack : jacks) {
				String cardName = jack + suit;
				Card card = new Card(cardName);
				deck.add(card);
			}
		}
		
		deck = this.shuffle(deck);
		
		return deck;
	}
	
	private ArrayList<Card> shuffle(ArrayList<Card> deck) {
		int numShuffles = 1000;
		
		for(int i = 0; i < numShuffles; i++) {
			int card1 = (int)(Math.random()*deck.size());
			int card2 = (int)(Math.random()*deck.size());
			this.cardSwap(deck,card1,card2);
		}
		
		return deck;
	}
	
	private void cardSwap(ArrayList<Card> deck, int card1, int card2) {
		Card temp = new Card(deck.get(card1).getCardName());
		deck.set(card1, deck.get(card2));
		deck.set(card2, temp);
	}
	
	public Card draw() {
		return this.deck.remove(0);
	}
}
