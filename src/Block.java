
public class Block {
	/*
	 * 	Initial values for a block and the only variables it will need.
	 * 	The simulation only needs an x axis variable and so a y variable will be unneeded and will be
	 * 	defined in the graphical program.
	 */
	private double x;
	private int width;
	private double mass;
	private double velocity;
	
	//Constructor to create a block with given values.
	Block(double x, int width, double d, double velocity){
		this.x = x;
		this.width = width;
		this.mass = d;
		this.velocity = velocity;
	}
	
	//Getter functions to retrieve values
	public double getX() {
		return x;
	}
	public void setX(double newX) {
		x = newX;
	}
	public int getWidth() {
		return width;
	}
	public double getMass() {
		return mass;
	}
	public void setMass(int newMass) {
		mass = newMass;
	}
	public double getVelocity() {
		return velocity;
	}
	public void setVelocity(double newVelocity) {
		velocity = newVelocity;
	}
	public void move() {
		x += velocity;			//every update will be the equivalent of half a second
		//
	}
}
