import java.util.Scanner;
import java.util.ArrayList;



public class Sequence {
	
	//once set use .finalize()
	//static finals:
	public static final String[] COLORS = {"RED", "GREEN", "BLUE"};
	//finals:
	private Deck DECK = new Deck();
	private Player[] PLAYERS;
	//others:
	//If only 2 players, MAX_CARDS is 7, otherwise 6
	private int MAX_CARDS = 6;
	private Board BOARD = new Board();
	
	
	
	//***********************************************************************************************************
	
	public Sequence() {
		//System.out.println("Setting up game...");
		
		//this.PLAYERS = this.initPlayers();
		
		//System.out.println("Cards are being dealt...");
		
		//this.dealCards();
		
		//System.out.println("Cards dealt...");
		
		this.BOARD.draw();
		
		//System.out.println("Finished Setup!");
	}
	
	//***********************************************************************************************************
	
	private void dealCards() {
		//silly rule
		if(this.PLAYERS.length == 2) {
			this.MAX_CARDS = 7;
		}
		
		for(Player player : PLAYERS) {
			ArrayList<Card> hand = new ArrayList<Card>();
			for(int cardNum = 0; cardNum < this.MAX_CARDS; cardNum++) {
				hand.add(this.DECK.draw());
			}
			player.setHand(hand);
		}
	}
	
	private Player[] initPlayers() {
		Scanner scan = new Scanner(System.in);
		
		int numPlayers = this.inputNumPlayers(scan);
		int numTeams = this.inputNumTeams(scan);
		
		//System.out.println("Collected User Info");
		
		Player[] players = new Player[numPlayers];
		
		for(int player = 0; player < numPlayers; player++) {
			System.out.print("Enter player name: ");
			String name = scan.next();
			String color = this.calcColor(player,numTeams);
			players[player] = new Player(name,color);
		}
		
		return players;
	}
	
	public String calcColor(int playerNum, int numTeams) {
		return Sequence.COLORS[playerNum % numTeams];
	}
	
	public int inputNumPlayers(Scanner scan) {
		int numPlayers = 0;
		do {
			System.out.print("Enter number of players (2-12): ");
			numPlayers = scan.nextInt();
		} while(!(numPlayers >= 2 && numPlayers <= 12));
		
		return numPlayers;
	}
	
	public int inputNumTeams(Scanner scan) {
		int numteams = 0;
		do {
			System.out.print("Enter number of teams (2-3): ");
			numteams = scan.nextInt();
		} while(!(numteams >= 2 && numteams <= 3));
		
		return numteams;
	}
	
	//***********************************************************************************************************
	
	public void play() {
		//System.out.println("playing game...");
		
		//int currentPlayer = getFirstPlayer();	
		
		boolean playing = true;
		while(playing) {
			/*
			 * Turns consist of four steps:
			 * 1. Select a card that refers to a vacant board space
			 * 1.1. Click spot on the board
			 * 2. Discard the card
			 * 3. Place colored chip on blank square
			 * 4. Draw a new card
			 */		
			
			//wait for a player to click
		}
	}
	
	//***********************************************************************************************************
	
	public int getFirstPlayer() {
		return (int)(Math.random()*this.PLAYERS.length);
	}
}
