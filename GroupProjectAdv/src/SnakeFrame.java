import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
//Radithya and Sanad
//May 11th, 2021

//this class creates the frame for snake
public class SnakeFrame extends JFrame implements ActionListener{

	SnakeFrame(){
		
		//adding the GamePanel class
		SnakePanel panel = new SnakePanel();
		
		this.add(panel);
		this.setTitle("Snake");
		this.setResizable(false);
		this.pack(); //if we add components to the frame, the frame will fit around the components
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new SnakeFrame();
	}
}