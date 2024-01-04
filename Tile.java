import java.awt.*;
import java.awt.event.*;


public class Tile extends Frame implements ActionListener{
	private String cardName;
	private Button button;
	
	public Tile(String cardName) {
		super(cardName);

		this.cardName = cardName;

		button = new Button(this.cardName);
		add(button);
		button.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		//do stuff
	}
	
	public String getCardName() {
		return this.cardName;
	}
}
