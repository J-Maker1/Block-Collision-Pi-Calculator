import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;

public class MyPanel extends JPanel implements ActionListener{
	
	final int PANEL_WIDTH = 1280;
	final int PANEL_HEIGHT = 720;
	Block small;
	Block big;
	Image smallImg;
	Image bigImg;
	Timer timer;
	int xSmall = 100;	//Separation of 450 pixels from the right side of the small cube to the left side of the big cube.
	int xBig = 700;
	int ySmall = 620;
	int yBig = 470;
	double bigV = -3.0;		//initial big block velocity
	int collisionCounter = 0;
	
	MyPanel(){
		//Initializing and creating graphical elements.
		this.setBackground(new Color(0, 0, 0));
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		smallImg = new ImageIcon("piBlockSmall.png").getImage();
		bigImg = new ImageIcon("piBlockMedium.png").getImage();
		
		//Creating blocks and simulation object to utilizae the blocks.
		small = new Block(xSmall, 50, 1.0, 0.0);	//Small block only has a width of 50, a weight of 1kg, and 0 velocity.
		big = new Block(xBig, 200, 10000.0, bigV);		//Big block has a width of 200, a weight of 1kg, and a velocity of 2m/s to the left.
		
		timer = new Timer(10, this); 
		timer.start();
	}
	
	public void paint(Graphics g) {
		//Painting the objects on the screen;
		super.paint(g);
		Graphics g2D = (Graphics2D) g;
		//Things get graphically weird with large objects, so this reduces how badly the blocks look visually when colliding near the edge of the screen.
		if(xSmall >= 0) {
			g2D.drawImage(smallImg, xSmall, ySmall, null);
		}
		else {
			g2D.drawImage(smallImg, 0, ySmall, null);
		}
		g2D.drawImage(bigImg, xBig, yBig, null);
		g2D.setFont(new Font("Ink Free", Font.BOLD, 30));
		g2D.setColor(Color.WHITE);
		g2D.drawString("Collisions: " + String.valueOf(collisionCounter), 1000, 30);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//First run big block's actions
		if(big.getVelocity() < 0.0) {													//Check if big block's velocity points to the left
			if(big.getX() <= small.getX() + small.getWidth()) {		//Check for collision with small block
				collision();
			}
			else {													//If there's no collision, continue moving on.
				big.move();
				xBig = (int)Math.round(big.getX());
			}
		}
		else if(big.getVelocity() >= 0.0){														//If it points to the right, let it move right.
			big.move();
			xBig = (int)Math.round(big.getX());
		}
		
		//Next to check small block's behavior
		if(small.getVelocity() < 0) {								//check if velocity points left
			if(small.getX() <= 0) {									//If momentum is left, but against the wall, bounce right
				small.setVelocity(-1 * small.getVelocity());
				collisionCounter++;
				if(small.getX()+small.getWidth() >= big.getX()) {	//test section
					collision();
				}
			}
			else {													//If it isn't against the wall, then it can move left
				small.move();
				xSmall = (int)Math.round(small.getX());
			}
		}
		else if(small.getVelocity() > 0){							//Next is to check if the big block is right next to the small one
			if(small.getX()+small.getWidth() >= big.getX()) {
				collision();
				small.move();
				xSmall = (int)Math.round(small.getX());
			}
			else {
				small.move();
				xSmall = (int)Math.round(small.getX());
			}
		}
		
		repaint();
	}
	
	public void collision() {
		//recording values from blocks in smaller/easier to use variables.
		//Made them all double so that calculations would not round to an integer.
		double mb2 = big.getMass();
		double ms1 = small.getMass();
		double bv2 = big.getVelocity();
		double sv1 = small.getVelocity();
		
		//Perfectly elastic collision calculation.
		double newBigV = ((((2.0*ms1)/(ms1+mb2)) * sv1) + (((mb2-ms1)/(ms1+mb2)) * bv2));
		double newSmallV = (((ms1-mb2)/(ms1+mb2)) * sv1) + (((2.0*mb2)/(ms1+mb2)) * bv2);
		big.setVelocity(newBigV);
		small.setVelocity(newSmallV);
		
		collisionCounter++;
	}
	
}
