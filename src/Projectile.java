import java.awt.Graphics;
import java.awt.Image;

/**
 * @author Vikram Idury
 * 
 * Represents a projectile entity that moves continuously at a given speed in one direction.
 */
public abstract class Projectile {

	private int x, y;
	private double velocity = 15;
	private double theta;
	
	private int height, width;
	
	private Image image;
	
	/**
	 * Projectile must be given an initial position as well as direction, which should be a number on the closed interval [0, 2pi]. The image provided is the image shown on the projectile animation.
	 */
	public Projectile(int x, int y,int height, int width, double direction, Image image){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		theta = direction;
		this.image = image;
	}
	
	/**
	 * Advances the position of the projectile
	 */
	public void act(){
		x += Math.cos(theta) * velocity;
		y += Math.sin(theta) * velocity;
	}
	
	/**
	 * Draws the projectile onto the screen
	 * @param g The Graphics object to be used
	 */
	public void draw(Graphics g){
		g.drawImage(image, x, y, width, height, null);
	}
	
	public double getVelocity(){
		return velocity;
	}
	
	public double getDirection(){
		return theta;
	}
	
	public void setVelocity(double velocity){
		this.velocity = velocity;
	}
	
	public void setDirection(double theta){
		this.theta = theta;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	
}
