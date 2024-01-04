import java.awt.*;
import java.awt.event.*;

public class AL extends Frame implements ActionListener {
        Button b;

        public AL(String title) {

                super(title);
                b = new Button("Click me");
                add(b);
                b.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
               //do stuff
        }
}