import java.io.*;
import javax.swing.*;

import java.awt.*;
import java.util.*;



public class Board {
	private Tile[][] board;
	
	private final int size = 1000;
	private final int rows = 10;
	private Player human;
	private ArrayList<Card> hand;
	
	private JFrame frame;
	private JPanel panel;
	
	private int boardSize;
	private int topMargin;
	private int leftMargin;
	private int tileWidth;
	
	int cardTopMargin = topMargin + boardSize + 10;
	int cardBottomMargin = 0;
	int cardLeftMargin = 50;
	int cardWidth = 100;
	
	private Color backgroundGreen = new Color(0,100,0);
	private Color lightGray = new Color(169,169,169);
	
	private int[] tileIndex = {-1,-1};
	private Card selectedCard = null;
	
	public Board(Player human) {
		board = initBoard();
		frame = new JFrame();
		frame.setSize(size + 100,size + 100); //I think +100 is needed here
		panel = new JPanel();
		frame.add(panel);

		panel.addMouseListener(new MouseAdapater() {
			@Override
			public void mousePressed(MouseEvent e){
				this.handleMouse(e.getX(), e.getY());
			}
		});

		
		this.human = human;
		hand = human.getHand();
	}

	public void handleMouse(int x, int y){
		//do stuff
	}
	
	public void draw() {
		drawBackground();
		drawBoard();
		drawCards();
		drawOthers();
	}
	
	private void drawBackground() {
		g.setColor(backgroundGreen);
		g.fillRect(0, 0, size, size);
	}
	
	private void drawBoard() {
		drawGrid();
		drawTiles();
	}
	
	private void drawTiles() {
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[row].length; col++) {
				String cardName = board[row][col].getCardName();
				if(cardName.equals("W")) {
					this.drawPiece(row,col,Color.black);
				} else {
					this.drawTileName(row,col);
				}
			}
		}
	}
	
	private void drawPiece(int row, int col, Color color) {
		g.setColor(color);
		g.fillOval(leftMargin+col*tileWidth,topMargin+row*tileWidth,tileWidth,tileWidth);
		
		if(color.equals(lightGray)) {
			this.drawTileName(row,col);
		}
	}
	
	private void drawTileName(int row, int col) {
		String cardName = board[row][col].getCardName();
		this.setCardColor(cardName);
		cardName = this.prettyName(cardName);
		g.drawString(cardName, (int)(leftMargin+(col+0.3)*tileWidth), (int)(topMargin+(row+0.5)*tileWidth));
	}
	
	private void setCardColor(String cardName) {
		if(cardName.contains("H")||cardName.contains("D")) {
			g.setColor(Color.red);
		} else {
			g.setColor(Color.black);
		}
	}
	
	private String prettyName(String cardName) {
		cardName = cardName.replace("C","♣");
		cardName = cardName.replace("D","♦");
		cardName = cardName.replace("H","♥");
		cardName = cardName.replace("S","♠");
				
		return cardName;
	}
	
	private void drawGrid() {
		this.topMargin = 50;
		int bottom = 200;
		this.leftMargin = (int)((this.topMargin + bottom)/2);
		this.boardSize = this.size - this.topMargin - bottom;
		this.tileWidth = (int)(boardSize/rows);
		
		g.setColor(lightGray);
		g.fillRect(this.leftMargin, this.topMargin, boardSize, boardSize);
		g.setColor(Color.black);
		g.drawRect(this.leftMargin, this.topMargin, boardSize, boardSize);
		
		int gap = (int)(boardSize/rows);
		for(int i = 0; i < boardSize; i+=(gap)) {
			g.drawLine(this.leftMargin,this.topMargin+i,this.size-this.leftMargin,this.topMargin+i);
			g.drawLine(this.leftMargin+i, this.topMargin, this.leftMargin+i, this.size-bottom);
		}
	}
	
	private void drawCards() {
		this.drawPlayerCards();
		this.drawDeck();
	}
	
	private void drawPlayerCards() {
		for(int card = 0; card < hand.size(); card++) {
			this.drawPlayerCard(card, Color.gray);
		}
	}
	
	private void drawPlayerCard(int card, Color color) {
		int boardTopMargin = this.topMargin + boardSize + 10;
		int bottomMargin = 0;
		int leftMargin = 50;
		int cardWidth = 100;
		
		g.setColor(color);
		g.fillRect(leftMargin + card * cardWidth, boardTopMargin, cardWidth, size - boardTopMargin - bottomMargin);
		g.setColor(Color.black);
		g.drawRect(leftMargin + card * cardWidth, boardTopMargin, cardWidth, size - boardTopMargin - bottomMargin);
		
		String cardName = hand.get(card).getCardName();
		this.setCardColor(cardName);
		g.drawString(this.prettyName(cardName), (int)(leftMargin + (card + 0.3) * cardWidth), (int)(boardTopMargin * 1.05));
	}
		
	private void drawDeck() {		
		g.setColor(Color.gray);
		g.fillRect(leftMargin + 8 * cardWidth, cardTopMargin, cardWidth, size - cardTopMargin + cardBottomMargin);
		g.setColor(Color.black);
		g.drawRect(leftMargin + 8 * cardWidth, cardTopMargin, cardWidth, size - cardTopMargin + cardBottomMargin);
		
		String cardName = "Deck";
		g.drawString(cardName, (int)(leftMargin + (8 + 0.2) * cardWidth), (int)(cardTopMargin * 1.05));
	}
	
	private void drawOthers() {
		
	}
	
	private Tile[][] initBoard() {
		int size = 10;
		Tile[][] board = new Tile[size][size];
		
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++) {
				board[row][col] = new Tile(this.getCardName(row,col));
			}
		}
		
		return board;
	}
	
	//'W' = wild; the corners
	private String getCardName(int row, int col) {
		//double checked to make sure!!!!!!!!!!
		String[][] names = {
				{"W" , "6D", "7D", "8D","9D", "10D","QD","KD", "AD", "W"},
				{"5D", "3H", "2H", "2S","3S", "4S", "5S","6S", "7S", "AC"},
				{"4D", "4H", "KD", "AD","AC", "KC", "QC","10C","8S", "KC"},
				{"3D", "5H", "QD", "QH","10H","9H", "8H","9C", "9S", "QC"},
				{"2D", "6H", "10D","KH","3H", "2H", "7H","8C", "10S","10C"},
				{"AS", "7H", "9D", "AH","4H", "5H", "6H","7C", "QS", "9C"},
				{"KS", "8H", "8D", "2C","3C", "4C", "5C","6C", "KS", "8C"},
				{"QS", "9H", "7D", "6D","5D", "4D", "3D","2D", "AS", "7C"},
				{"10S","10H","QH", "KH","AH", "2C", "3C","4C", "5C", "6C"},
				{"W" , "9S", "8S", "7S","6S", "5S", "4S","3S", "2S", "W"}
		};
		
		return names[row][col];
	}
	
	public Tile[][] getBoard(){
		return board;
	}
	
	public DrawingPanel getPanel() {
		return this.panel;
	}
	
	//we need the player to click on their card before clicking on the board to place it
	public void getClick() {
		System.out.println("here2");
		this.panel.onClick( (x,y) -> { this.processClick(x,y); } );
		this.panel.onClick( (x,y) -> { System.out.println(x + "," + y); } );
	}
	
	private void processClick(int x, int y) {
		System.out.println(x + "," + y);
		if(y >= topMargin && y <= topMargin + boardSize && x >= leftMargin && x <= leftMargin + boardSize) {
			this.processBoardClick(x,y);
		} else if(y >= cardTopMargin && y <= size - cardBottomMargin && 
				x >= cardLeftMargin && x <= cardLeftMargin + hand.size() * cardWidth) {
			this.processCardClick(x,y);
		}
	}
	
	private void processBoardClick(int x, int y) {
		int[] index = this.getBoardIndex(x,y);
		String tileName = this.board[index[0]][index[1]].getCardName();
		String cardName = this.selectedCard.getCardName();
		if(tileName.equals(cardName) || cardName.substring(0,2).equals("2J")) {
			Color color = human.getColor();
			this.drawPiece(x, y, color);
		} else if(cardName.substring(0,2).equals("1J")) {
			this.drawPiece(x, y, lightGray);
		}
	}
	
	private int[] getBoardIndex(int x, int y) {
		x -= leftMargin;
		x = (int)(x / tileWidth);
		
		y -= topMargin;
		y = (int)(y / tileWidth);
		
		return new int[]{x,y};
	}
	
	private void processCardClick(int x, int y) {
		int index = this.getCardIndex(x,y);
		Card card = hand.get(index);
		card.toggleSelect();
		if(card.isSelected()) {
			this.drawPlayerCard(index, Color.gray);
			this.selectedCard = null;
		} else {
			this.drawPlayerCard(index, Color.yellow);
			this.selectedCard = card;
		}
	}
	
	private int getCardIndex(int x, int y) {
		x -= cardLeftMargin;
		x = (int)(x / cardWidth);
		
		return x;
	}

}
