import javax.swing.*;

public class FlappyBird {
	JFrame frame;
	public static FlappyBird flappybird;
	public final int WIDTH = 800, HEIGHT = 800;


	//constructor
	public FlappyBird() {
		frame = new JFrame();
		
		frame.setSize(WIDTH, HEIGHT);	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	//main method
	public static void main(String[] args) {
		flappybird = new FlappyBird();
	}
}
