import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{

	//instance of the random class. Determines who will go first randomly.
	Random random = new Random();
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JLabel textfield = new JLabel();

	JButton[] buttons = new JButton[9]; //an array of nine buttons
	boolean player1_turn; //true if player 1's turn, false if player 2's turn
	JButton restart = new JButton("Restart");

	//constructor
	TicTacToe(){

		frame.setSize(800,800);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		//text at the top of
		textfield.setFont(new Font("Arial",Font.BOLD,75));
		textfield.setHorizontalAlignment(JLabel.CENTER);

		//grid layout for the buttons
		button_panel.setLayout(new GridLayout(3,3));
		button_panel.setBackground(new Color(150,150,150));

		//since the buttons are in an array, we can easily initialize them all at once
		for(int i=0;i<9;i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("Arial",Font.BOLD,120));
			buttons[i].addActionListener(this);
		}

		//adding components
		title_panel.add(textfield);
		frame.add(title_panel,BorderLayout.NORTH);
		frame.add(button_panel);
		frame.add(restart, BorderLayout.SOUTH);

		firstTurn();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//checking button presses and show the X or the O.
		for(int i=0;i<9;i++) { 
			if(e.getSource()==buttons[i]) {
				if(player1_turn) {
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(255,0,0)); //color for x
						buttons[i].setText("X"); //setting the X in the button
						player1_turn=false; //change to player 2's turn
						textfield.setText("O turn");
						check(); //call the check method
					}
				}
				//same thing for O
				else {
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(0,0,255));
						buttons[i].setText("O");
						player1_turn=true;
						textfield.setText("X turn");
						check();
					}
				}

			}			
		}
		if (e.getSource() == restart) {
			for (int i=0;i<9;i++) {
				buttons[i].setText("");
				buttons[i].setEnabled(true);
			}
		
		}
	
	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyChar()=='p') {
			for(int i=0;i<9;i++) {
				buttons[i].setEnabled(false);
			}
			textfield.setText("Game Paused");
		}
	}

	public void firstTurn() {

		//if the chosen random number is 0, then player 1 starts
		if(random.nextInt(2)==0) {
			player1_turn=true;
			textfield.setText("X turn");
		}

		//if it's not player 1's turn, then it's player 2's turn
		else {
			player1_turn=false;
			textfield.setText("O turn");
		}
	}

	//method for checking to see if any player won
	public void check() {
		//check X win conditions
		//checking every single win condition
		if(
				(buttons[0].getText()=="X") &&
				(buttons[1].getText()=="X") &&
				(buttons[2].getText()=="X")
				) {
			//call the xWins method
			xWins();
		}
		if(
				(buttons[3].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[5].getText()=="X")
				) {
			xWins();
		}
		if(
				(buttons[6].getText()=="X") &&
				(buttons[7].getText()=="X") &&
				(buttons[8].getText()=="X")
				) {
			xWins();
		}
		if(
				(buttons[0].getText()=="X") &&
				(buttons[3].getText()=="X") &&
				(buttons[6].getText()=="X")
				) {
			xWins();
		}
		if(
				(buttons[1].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[7].getText()=="X")
				) {
			xWins();
		}
		if(
				(buttons[2].getText()=="X") &&
				(buttons[5].getText()=="X") &&
				(buttons[8].getText()=="X")
				) {
			xWins();
		}
		if(
				(buttons[0].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[8].getText()=="X")
				) {
			xWins();
		}
		if(
				(buttons[2].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[6].getText()=="X")
				) {
			xWins();
		}

		//check O win conditions
		if(
				(buttons[0].getText()=="O") &&
				(buttons[1].getText()=="O") &&
				(buttons[2].getText()=="O")
				) {
			oWins();
		}
		if(
				(buttons[3].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[5].getText()=="O")
				) {
			oWins();
		}
		if(
				(buttons[6].getText()=="O") &&
				(buttons[7].getText()=="O") &&
				(buttons[8].getText()=="O")
				) {
			oWins();
		}
		if(
				(buttons[0].getText()=="O") &&
				(buttons[3].getText()=="O") &&
				(buttons[6].getText()=="O")
				) {
			oWins();
		}
		if(
				(buttons[1].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[7].getText()=="O")
				) {
			oWins();
		}
		if(
				(buttons[2].getText()=="O") &&
				(buttons[5].getText()=="O") &&
				(buttons[8].getText()=="O")
				) {
			oWins();
		}
		if(
				(buttons[0].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[8].getText()=="O")
				) {
			oWins();
		}
		if(
				(buttons[2].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[6].getText()=="O")
				) {
			oWins();
		}
	}

	//method for setting up how x wins
	public void xWins() {
		//disable all buttons
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("X wins");
	}
	//method for setting up how o wins
	public void oWins() {

		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("O wins");
	}

	public static void main(String[] args) {
		TicTacToe runner = new TicTacToe();
	}
}
