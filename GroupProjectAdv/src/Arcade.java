import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

//Radithya and Sanad
public class Arcade implements ActionListener {
	
	JFrame frame;
	JButton game1;
	JButton game2;
	JButton game3;
	Image img;
	JLabel label;
	
	public Arcade() {
		frame = new JFrame("Welcome to the Arcade");
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		game1 = new JButton("Game 1");
		game1.setBounds(101,30,300,100);
		game1.addActionListener(this);
		
		game2 = new JButton("Game 2");
		game2.setBounds(101,130,300,100);
		game2.addActionListener(this);
		
		game3 = new JButton("Game 3");
		game3.setBounds(101,230,300,100);
		game3.addActionListener(this);
		
		label = new JLabel();
		
		frame.add(game1);
		frame.add(game2);
		frame.add(game3);
		frame.add(label);
		
		frame.add(new JLabel(new ImageIcon("/Users/SanadHajarat21/git/GroupProjectAdvRepo/GroupProjectAdv/ArcadeMachine.jpeg")));
		
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==game1) {
			new Pong();
		}
	}

	
	public static void main(String[] args) {
		new Arcade();
	}
}


