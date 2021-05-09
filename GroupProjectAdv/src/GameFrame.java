import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class GameFrame extends JFrame implements ActionListener{
	
	GameFrame(){

		this.add( new GamePanel() );
		this.setTitle("Snake");
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new GameFrame();
	}
}