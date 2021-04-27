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
	JButton guidelines;
	Image img;
	JLabel label;
	
	public Arcade() {
		frame = new JFrame("Welcome to the Arcade");
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		game1 = new JButton("Pong");
		game1.setBounds(101,30,200,100);
		game1.addActionListener(this);
		
		game2 = new JButton("Snake");
		game2.setBounds(101,130,200,100);
		game2.addActionListener(this);
		
		game3 = new JButton("Game 3");
		game3.setBounds(101,230,200,100);
		game3.addActionListener(this);
		
		guidelines = new JButton("Guidelines");
		guidelines.setBounds(301, 30, 100, 300);
		guidelines.addActionListener(this);
		
		label = new JLabel();
		
		frame.add(game1);
		frame.add(game2);
		frame.add(game3);
		frame.add(guidelines);
		frame.add(label);
		
		frame.add(new JLabel(new ImageIcon("ArcadeMachine.jpeg")));
		
		frame.setVisible(true);
	}
	
	public void guidelines() {
		JFrame gf= new JFrame("Guidelines");
		TextArea ta = new TextArea();
		ta.setText("Guidelines on how to play Pong!\n"
				+ "Use W and A to control the left paddle and The Upper and Lower arrows to control the right paddle!\n"
				+ "If you want an extra ball, hold spacebar to make it appear!\n"
				+ "If you would like to play against a bot press 1, and press 2 to go back to original settings again.\n"
				+ "Lastly, click reset whenever you'd like to reset the game/scores.\n\n"
				+ "Guidelines on how to play Snake!\n"
				+ "Use WASD or the arrows to move around as the snake!\n"
				+ "Try and collect as much apples as you can to grow larger.\n"
				+ "Avoid overlapping yourself or reaching the edge as you will die.\n"
				+ "Try reaching as much score as you can without the game ending! Good luck!");
		ta.setBounds(0, 0, 500, 200);
		gf.add(ta);
		gf.setSize(500,200);
		gf.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==game1) {
			new Pong();
		}
		if (e.getSource()==game2) {
			new GameFrame();
		}
		if (e.getSource()==guidelines) {
			guidelines();
		}
	}

	
	public static void main(String[] args) {
		new Arcade();
	}
}


