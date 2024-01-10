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
import javax.swing.JLabel;
import javax.swing.Timer;

public class MyPanel extends JPanel implements ActionListener{
	
	//Initial values
	
	final int PANEL_WIDTH = 1280;
	final int PANEL_HEIGHT = 720;
	Image ground;
	Image wall;
	Image smallBlock;
	Image bigBlock;
	Timer timer;
	int xsmall = 100;
	int ysmall = 550;
	int xbig = 1000;
	int ybig = 400;
	double bV = 3;
	double realbV = 2;
	double sV = 0;
	double realsV = 0;
	int count = 0;
	
	double massbig = 100;
	double masssmall = 1;
	
	MyPanel(){
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));		//Setting size of panel/window
		this.setBackground(new Color(33, 33, 33));								//Setting background color
		smallBlock = new ImageIcon("block1.png").getImage();					//making image objects
		bigBlock = new ImageIcon("block2.png").getImage();
		timer = new Timer(5, this);
		
		timer.start();															//Creating timer to activate action listener
		
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);															//Paints background
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(smallBlock, xsmall, ysmall, null);						//Paints the two blocks
		g2D.drawImage(bigBlock, xbig, ybig, null);
		
		g2D.setFont(new Font("Ink Free", Font.BOLD, 30));						//Creating the collision counter label
		g2D.setPaint(Color.LIGHT_GRAY);
		g2D.drawString("Collisions: " + String.valueOf(count), 1000, 30);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(xbig<= (xsmall + 100)){
			realbV = ((((massbig-masssmall)/(massbig+masssmall))*realbV) + (((2*masssmall)/(massbig+masssmall))*realsV));
			realsV = ((((2*massbig)/(massbig+masssmall))*realbV) + (((masssmall-massbig)/(massbig+masssmall))*realsV));
			bV = realbV;
			sV = realsV;
			System.out.println(sV);
			count++;
		}
		if(xsmall <= 0) {
			sV = (int)(sV*-1);
		}
		
		xbig -= bV;
		xsmall -= sV;
		repaint();
	}
	
}
