// ADDED LINE

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class snek
implements KeyListener, ActionListener {
 
	public final int TILESIZE = 45;
	public final int GRIDHEIGHT=15;
	public final int GRIDWIDTH=15;
	JFrame frame;
	JPanel game;
	boolean mUp, mDown, mLeft, mRight;
	int selected=0;
	ArrayList<Integer> snakePartX = new ArrayList<Integer>();
	ArrayList<Integer> snakePartY = new ArrayList<Integer>();
	ArrayList<Integer> snakePartMode = new ArrayList<Integer>();
	int[][] map = new int[GRIDWIDTH+1][GRIDHEIGHT+1]; 

	public snek() {
		snakePartX.add(7);
		snakePartY.add(7);
		
		
		
		snakePartX.add(7);
		snakePartY.add(6);
		

		snakePartX.add(7);
		snakePartY.add(5);

		
		snakePartMode.add(0); //1 is up
		snakePartMode.add(0);
		snakePartMode.add(0);
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
		frame.setSize(700,700);
		frame.setResizable(false);
		frame.setLayout(new GridLayout());
		frame.setVisible(true);
		frame.addKeyListener(this);
		game = new JPanel() {

			public void paint(Graphics g) {
				super.paint(g);
				moveParts();
				movement();
				drawGrid(g, GRIDWIDTH,GRIDHEIGHT);
				try {Thread.sleep(200);} 
				catch (InterruptedException e) {}
				frame.getContentPane().repaint();
				g.setColor(Color.white);
			
			}

		};

		frame.add(game);

	}

	public void movement() {
		selected++;
		if(selected >= snakePartX.size()) {
			selected = 0;
		}
		System.out.println(selected);

	}
	public void moveParts() {
		snakePartY.set(selected, snakePartY.get(selected)+1);
	}
	public void drawGrid(Graphics g, int width, int height) {
		
		for(int i = 0; i < snakePartX.size();i++) {
			for(int i2 = 0; i2 < snakePartX.size();i2++) {
				map[snakePartX.get(i)][snakePartY.get(i2)] = 1;
			}
		}
		for(int i = 0; i < width; i++) //width
			for(int j = 0; j < height; j++) { //height
				if(map[i][j] != 1) {
					g.setColor(Color.red);
					g.fillRect(i*(TILESIZE), j*(TILESIZE), TILESIZE, TILESIZE);
					g.setColor(Color.black);
					g.drawString(i + " " + j, i*TILESIZE, j*TILESIZE);
					g.drawRect(i*(TILESIZE), j*(TILESIZE), TILESIZE, TILESIZE);
				}
			}
		for(int i = 0; i < width; i++) 
			for(int j = 0; j < height; j++) {
				map[i][j]=0;
			}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_W) {
			mUp = true;
			mRight = false;
			mDown = false;
			mLeft = false;
		}
		if(e.getKeyCode()== KeyEvent.VK_A) {
			mLeft = true;
			mRight = false;
			mDown = false;
			mUp = false;
		}
		if(e.getKeyCode()== KeyEvent.VK_D) {
			mRight = true;
			mUp = false;
			mDown = false;
			mLeft = false;
		}
		if(e.getKeyCode()== KeyEvent.VK_S) {
			mDown = true;
			mRight = false;
			mUp = false;
			mLeft = false;
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {new snek();}
}
