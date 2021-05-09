import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Pong extends JPanel implements KeyListener, ActionListener {

	// constants that are predefined and won't change as the program runs
	private final int WIDTH = 600, HEIGHT =600, WINDOW_HEIGHT = 650;
	private final int PADDLE_WIDTH = 20, DIAM = 15, PADDLE_HEIGHT = 100;
	private final int PADDLE_SPEED = 7;

	// Balls placement & speed horizontally & vertically along with paddle placement & score keeping
	private int ballX = WIDTH/2, ballY = HEIGHT/2;
	private int ballX2 = WIDTH/2, ballY2 = HEIGHT/2;
	private int speedX = 3, speedY = 5, speedX2 = 0, speedY2 = 0;
	private int paddle1 = HEIGHT/2- PADDLE_HEIGHT/2, paddle2 = HEIGHT/2 - PADDLE_HEIGHT/2, pSpeed = 8;
	private int score1 = 0, score2 = 0;
	private int paddle1x = 0, paddle2x = WIDTH-PADDLE_WIDTH;

	// some instance variables
	private boolean up1, down1, up2, down2; 		// booleans to keep track of paddle movement
	private boolean solo = false;					// playing against AI
	private boolean addBall = false;				// adds a new ball to the game which AI can't beat

	public Pong() {
		JFrame frame = new JFrame();
		JButton button = new JButton("restart");
		
		frame.setSize(WIDTH+6, WINDOW_HEIGHT);
		frame.add(this);
		frame.add(button, BorderLayout.SOUTH);
		frame.setResizable(false);
		frame.setVisible(true);
		button.addActionListener(this);
		
		this.addKeyListener(this);
		this.setFocusable(true);

		run();
	}

	// this method moves the ball
	public void move_ball() {
		ballX += speedX;
		ballY += speedY;

		ballX2 += speedX2;
		ballY2 += speedY2;
	}

	// this method moves the paddles at each timestep, and has other boolean setups like playing against 
	// an AI and additionally the paddle moving out of bound trick
	public void move_paddles() {

		if (up1)
			paddle1 -= pSpeed;

		if (down1)
			paddle1 += pSpeed;

		if (up2)
			paddle2 -= pSpeed;

		if (down2)
			paddle2 += pSpeed;

		if (solo) {

			if (paddle2 + PADDLE_HEIGHT/2 <= ballY) {
				paddle2 += PADDLE_SPEED;
			}

			else if (paddle2 + PADDLE_HEIGHT/2 >= ballY) {
				paddle2 -= PADDLE_SPEED;
			}
		}

		if (paddle1 >= HEIGHT)
			paddle1 = -1 * PADDLE_HEIGHT;

		else if (paddle1 <= -1 * PADDLE_HEIGHT)
			paddle1 = HEIGHT;

		if (paddle2 >= HEIGHT)
			paddle2 = -1 * PADDLE_HEIGHT;

		else if (paddle2 <= -1 * PADDLE_HEIGHT)
			paddle2 = HEIGHT;
	}

	// this method checks if there are any bounces to take care of,
	// and if the ball has reached a left/right wall it adds to 
	// the corresponding player's score
	public void check_collisions() {

		// the 16 and 4 are so that the ball has space to bounce on because it might not directly hit
		// the paddle on its surface, so I added 4 more spaces for it to bounce on on both paddle.
		if ((ballX >= PADDLE_WIDTH - 16 && ballX <= PADDLE_WIDTH) && (ballY >= paddle1 && ballY <= (paddle1 + PADDLE_HEIGHT)))
			speedX *= -1;

		if ((ballX2 >= PADDLE_WIDTH - 16 && ballX2 <= PADDLE_WIDTH) && (ballY2 >= paddle1 && ballY2 <= (paddle1 + PADDLE_HEIGHT)))
			speedX2 *= -1;

		if ((ballX <= WIDTH - PADDLE_WIDTH + 4 && ballX >= WIDTH - PADDLE_WIDTH) && (ballY >= paddle2 && ballY <= (paddle2 + PADDLE_HEIGHT))) 
			speedX *= -1;

		if ((ballX2 <= WIDTH - PADDLE_WIDTH + 4 && ballX2 >= WIDTH - PADDLE_WIDTH) && (ballY2 >= paddle2 && ballY2 <= (paddle2 + PADDLE_HEIGHT))) 
			speedX2 *= -1;

		if (ballY <= 0 || ballY + DIAM >= HEIGHT) 
			speedY *= -1;

		if (ballY2 <= 0 || ballY2 + DIAM >= HEIGHT) 
			speedY2 *= -1;

		if (ballX <= 0) {
			score2 += 1;
			ballX = WIDTH/2;
			ballY = HEIGHT/2;
		}

		if (ballX2 <= 0) {
			score2 += 1;
			ballX2 = WIDTH/2;
			ballY2 = HEIGHT/2;
		}

		if (ballX >= WIDTH) {
			score1 += 1;
			ballX = WIDTH/2;
			ballY = HEIGHT/2;
		}

		if (ballX2 >= WIDTH) {
			score1 += 1;
			ballX2 = WIDTH/2;
			ballY2 = HEIGHT/2;
		}	
	}


	// defines what we want to happen anytime we draw the game
	public void paint(Graphics g) {

		// background color is gray
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// draw your rectangles and circles here
		g.setColor(new Color(0, 0, 0));
		g.fillOval(ballX, ballY, DIAM, DIAM);

		g.setColor(new Color(0, 0, 200));
		g.fillRect(paddle1x, paddle1, PADDLE_WIDTH, PADDLE_HEIGHT);

		g.setColor(new Color(200, 0, 0));
		g.fillRect(paddle2x , paddle2, PADDLE_WIDTH, PADDLE_HEIGHT);


		// Adds a new ball to the game (AI loses to this) (ADDITIONAL)
		if (addBall) {

			g.setColor(new Color(255, 255, 255));
			g.fillOval(ballX2, ballY2, DIAM, DIAM);

		}		

		// writes the score of the game
		Font f = new Font("Arial", Font.BOLD, 14);
		g.setFont(f);
		// makes score in blue for blue paddle (ADDITIONAL)
		g.setColor(Color.blue);
		g.drawString("P1 Score: " + score1, WIDTH/5, 20);
		// makes score in red for red paddle (ADDITIONAL)
		g.setColor(Color.red);
		g.drawString("P2 Score: " + score2, WIDTH*3/5, 20);
	}

	// defines what we want to happen if a keyboard button has 
	// been pressed
	public void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();

		// changes paddle direction based on what button is pressed
		if (keyCode == KeyEvent.VK_DOWN) 
			down2 = true;

		if (keyCode == KeyEvent.VK_UP) 
			up2 = true;

		if (e.getKeyChar() == 'w')
			up1 = true;

		if (e.getKeyChar() =='s')
			down1 = true;

		// turn 1-player mode on
		if (e.getKeyChar() == '1')
			solo = true;

		// turn 2-player mode on
		if (e.getKeyChar() == '2')
			solo = false;

		// Keep holding space for ball to stay
		if (e.getKeyChar() == KeyEvent.VK_SPACE) {
			addBall = true;
			speedX2 = 4;
			speedY2 = 3;
		}
	}


	// defines what we want to happen if a keyboard button
	// has been released
	public void keyReleased(KeyEvent e) {

		int keyCode = e.getKeyCode();

		// stops paddle motion based on which button is released
		if (keyCode == KeyEvent.VK_UP) 
			up2 = false;

		if (keyCode == KeyEvent.VK_DOWN) 
			down2 = false;

		if(e.getKeyChar() == 'w')
			up1 = false;

		if (e.getKeyChar() == 's')
			down1 = false;

		if (e.getKeyChar() == KeyEvent.VK_SPACE) {
			addBall = false;
			speedX2 = 0;
			speedY2 = 0;
		
		}
	}

	// restarts the game, including scores and added ball
	public void restart() {

		score1 = 0;
		score2 = 0;

		ballX = WIDTH/2;
		ballY = HEIGHT/2;

		paddle1 = HEIGHT/2- PADDLE_HEIGHT/2;
		paddle2 = HEIGHT/2- PADDLE_HEIGHT/2;

		addBall = false;

		this.requestFocus();

	}

	// this method runs the actual game.
	public void run() {

		// closing the graphics window will end the program
		while (true) {

			// draws the game
			repaint();

			// we move the ball, paddle, and check for collisions
			// every hundredth of a second
			move_ball();
			move_paddles();
			check_collisions();


			//rests for a hundredth of a second
			try {				
				Thread.sleep(10);
			} 

			catch (Exception ex) {
				System.out.println("failed");
			}
		}
	}


	// checks if the user has pushed the restart button
	public void actionPerformed(ActionEvent e) {

		String str = e.getActionCommand();
		if (str.equals("restart")) 
			restart();
	}

	public void keyTyped(KeyEvent e) {

	}

	//main method
	public static void main(String[] args) {
		new Pong();
	}
}