import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Window extends JFrame{
	
	//GUI window creation and initialization.
	Window(){
		this.setTitle("Block Collision Pi Calculator");					//Labeling window.
		this.setResizable(false);										//Disabling window resizing.
		this.setSize(1280, 720);										//Standard window size set.
												
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//End program when window is closed.
				
		ImageIcon icon = new ImageIcon("Pi-symbol.png");				//Importing a new icon image.
		this.setIconImage(icon.getImage());								//Setting window icon to new image.
		this.getContentPane().setBackground(new Color(33, 33, 33));		//Changing background color.
		this.setLocationRelativeTo(null);								//Centering window on screen.
		
		this.setVisible(true);											//Make this visible.
	}
	
	
}