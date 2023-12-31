import java.awt.Graphics;
import java.awt.Color;



public class Board {
	private Tile[][] board;
	private final int size = 1000;
	private final int rows = 10;
	private DrawingPanel panel;
	private Graphics g;
	
	private int boardSize;
	private int topMargin;
	private int leftMargin;
	private int tileWidth;
	
	private Color backgroundGreen = new Color(0,100,0);
	private Color lightGray = new Color(169,169,169);
	
	public Board() {
		this.board = this.initBoard();
		this.panel = new DrawingPanel(this.size,this.size);
		this.g = this.panel.getGraphics();
	}
	
	public void draw() {
		this.drawBackground();
		this.drawBoard();
		this.drawPlayer();
		this.drawOthers();
	}
	
	private void drawBackground() {
		g.setColor(backgroundGreen);
		g.fillRect(0, 0, size, size);
	}
	
	private void drawBoard() {
		this.drawGrid();
		this.drawTiles();
	}
	
	private void drawTiles() {
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[row].length; col++) {
				String cardName = board[row][col].getCardName();
				if(cardName.equals("W")) {
					this.drawWild(row,col);
				} else {
					this.drawCard(row,col,cardName);
				}
			}
		}
	}
	
	private void drawWild(int row, int col) {
		g.setColor(Color.black);
		g.fillOval(leftMargin+col*tileWidth,topMargin+row*tileWidth,tileWidth,tileWidth);
	}
	
	private void drawCard(int row, int col, String cardName) {
		if(cardName.contains("H")||cardName.contains("D")) {
			g.setColor(Color.red);
		} else {
			g.setColor(Color.black);
		}
		cardName = this.prettyName(cardName);
		g.drawString(cardName, (int)(leftMargin+(col+0.3)*tileWidth), (int)(topMargin+(row+0.5)*tileWidth));
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
	
	private void drawPlayer() {
		
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
}
