
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
//Radithya and Sanad
//May 11th, 2021

//the panel class that holds all of the snake components
public class SnakePanel extends JPanel implements ActionListener{

	static final int SCREEN_WIDTH = 700;
	static final int SCREEN_HEIGHT = 700;
	static final int UNIT_SIZE = 35;  //how big the objects in the game are
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE); //calculating how many objects can fit on the screen
	static final int DELAY = 75;
	
	//arrays for holding all the body parts of the snake
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	
	int bodyParts = 6;
	int applesEaten;
	
	//coordinates for the apple
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;
	
	//constructor
	SnakePanel(){
		
		//setting up the panel
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	//creates a new apple on the screen
	public void newApple(){
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	}
	
	public void startGame() {
		//at the start of the game create a new apple and start timer.
		newApple();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		
		//if the game is running, do all this
		if(running) {
			
			//drawing the apple
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
		
			//create a for loop to iterate through all the body parts of the snake
			for(int i = 0; i< bodyParts;i++) {
				
				//if i is 0, then we're dealing with the head of the snake
				if(i == 0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				
				//if i is not 0, then we're dealing with the other body parts of the snake
				else {
					g.setColor(new Color(45,180,0));
					g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}			
			}
			//draws the score
			g.setColor(Color.red);
			g.setFont( new Font("Ink Free",Font.BOLD, 20));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		}
		else {
			gameOver(g);
		}
		
	}
	
	public void move(){
		//shifting every body part in the array by 1
		for(int i = bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		//creating cases for each possible direction
		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE; //if going up, from the y coordinate of the head, go to the next body part, which is below it
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE; //if going down, from the y coordinate of the head, go to the next body part, which is above
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE; //if going left, from the x coordinate of the head, go to the next body part, which is to the right
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE; //if going right, from the x coordinate of the head, go to the next body part, which is to the left
			break;
		}
		
	}
	
	public void checkApple() {
		//if the x and y positions of the snake equals the x and y positions of the snake, 
		//increase the body parts and number of apples eaten, and create a new apple
		if((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}
	public void checkCollisions() {
		//checks if head collides with body
		for(int i = bodyParts;i>0;i--) {
			
			//if the head equals to any other body part, stop running the game.
			if((x[0] == x[i])&& (y[0] == y[i])) {
				running = false;
			}
		}
		//check if head touches left border
		if(x[0] < 0) {
			running = false;
		}
		//check if head touches right border
		if(x[0] > SCREEN_WIDTH) {
			running = false;
		}
		//check if head touches top border
		if(y[0] < 0) {
			running = false;
		}
		//check if head touches bottom border
		if(y[0] > SCREEN_HEIGHT) {
			running = false;
		}
		
		if(running==false) {
			timer.stop();
		}
	}
	public void gameOver(Graphics g) {
		//Score
		g.setColor(Color.red);
		g.setFont( new Font("Arial",Font.BOLD, 20));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		
		//Game Over text
		g.setColor(Color.red);
		g.setFont( new Font("Arial",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//if the game is running, move the snake, check if apples are eaten, and check if there are any collisions
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		// when the game is no longer running, run the paint method
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			//4 cases, one for each arrow key. This prevents the snake from turning 180 degrees
			//limits the movement to just 90 degree turns
			case KeyEvent.VK_LEFT:
				if(direction != 'R') { //if the direction of the snake is not going to the right, then the player can go left
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') { //if the direction is not left, then the player can go right
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') { //if the direction is now down, then the player can go up
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') { //if the direction is not up, then the player is allowed to go down.
					direction = 'D';
				}
				break;
				
				//same thing but for WASD keys
			case KeyEvent.VK_A:
				if (direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_D:
				if (direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_W:
				if (direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_S:
				if (direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}
	}
}