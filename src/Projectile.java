import java.awt.Graphics2D;
import java.awt.Image;

/**
 * @author Vikram Idury
 * 
 * Represents a projectile entity that moves continuously at a given speed in one direction.
 */
public abstract class Projectile extends Entity {

	protected double velocity = 10;
	protected double theta;
	
	protected Entity shooter;
	
	protected int height, width;
	
	private Image image;
	
	/**
	 * Projectile must be given an initial position as well as direction, which should be a number on the closed interval [0, 2pi]. The image provided is the image shown on the projectile animation.
	 */
	public Projectile(int x, int y,int height, int width, double direction, Image image) {
		super(x, y);
		this.width = width;
		this.height = height;
		theta = direction;
		this.image = image;
	}
	
	public void setShooter(Entity shooter){
		this.shooter = shooter;
	}
	
	//Implement this Method
	public boolean isActive(){
		return false; 
	}
	
	/**
	 * Advances the position of the projectile
	 */
	public void act() {
		x += Math.cos(theta) * velocity;
		y += Math.sin(theta) * velocity;
	}
	
	/**
	 * Draws the projectile onto the screen
	 * @param g The Graphics object to be used
	 */
	public void draw(Graphics2D g) {
		g.drawImage(image, width, height, null);
	}

}
