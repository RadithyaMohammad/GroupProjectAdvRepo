import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

//this class creates the frame for snake
public class GameFrame extends JFrame implements ActionListener{

	GameFrame(){
		
		//adding the GamePanel class
		GamePanel panel = new GamePanel();
		
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
		new GameFrame();
	}
}