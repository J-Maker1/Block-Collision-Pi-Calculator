import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Window extends JFrame{
	
	MyPanel panel;
	//GUI window creation and initialization.
	Window(){
		panel = new MyPanel();
		this.add(panel);
		
		
		this.setTitle("Block Collision Pi Calculator");					//Labeling window.
		//this.setResizable(false);										//Disabling window resizing.
		this.pack();													//Window size set around panels in the window.							
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//End program when window is closed.
				
		ImageIcon icon = new ImageIcon("Pi-symbol.png");				//Importing a new icon image.
		this.setIconImage(icon.getImage());								//Setting window icon to new image.
		this.setLocationRelativeTo(null);								//Centering window on screen.
		
		
		this.setVisible(true);											//Make this visible.
	}
	
	
}