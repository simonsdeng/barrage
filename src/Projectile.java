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
	protected Entity entity;
	
	
	/**
	 * Projectile must be given an initial position as well as direction, which should be a number on the closed interval [0, 2pi]. The image provided is the image shown on the projectile animation.
	 */
	public Projectile(int x, int y,int height, int width, double direction, Grid grid, Entity entity, Image image) {
		super(x, y);
		this.width = width;
		this.height = height;
		theta = direction;
		this.image = image;
		this.entity = entity;
		this.grid = grid;
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
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(image, (int)(x - width/2), (int)(y - height/2), width, height, null);
	}
	
	public boolean collision() {
		for(Enemy e : grid.getEnemies()) {
			if(Math.hypot(x - e.getX(), y - e.getY()) <= e.getRadius() + width/2)
				return true;
		}
		
		return false;
	}

}
