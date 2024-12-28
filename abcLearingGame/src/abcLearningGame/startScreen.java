package abcLearningGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class startScreen {
	
	JFrame frame;
	JLabel displayField;
	ImageIcon image;
	
	public startScreen() {
		frame = new JFrame("Image display test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
		image = new ImageIcon(getClass().getResource("abcgame.png"));
		displayField = new JLabel(image);
		frame.add(displayField);	
		}catch(Exception e) {
			System.out.println("Image Cannot be Found");
			
		}
		frame.setSize(1000,1000);
		frame.setVisible(true);
		//Associate ActionListener with button
		JButton button;
		JPanel p = new JPanel();
	    button = new JButton("Start Game");
	    p.setLayout(null);
	    button.setBounds(500, 100, 100, 60);
	    p.add(button);
	    frame.add(p);
	    // setLayout(null);
	    frame.setDefaultCloseOperation(3);
	    frame.setVisible(true);	
	    
		button.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent ae)

		{
			frame.setVisible(false);
				
		}// End of method

		});// End of anonymous class
		}
}
