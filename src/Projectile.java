import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;

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
	public Projectile(Point2D.Double loc, int height, int width, double direction, Entity entity, Image image) {
		super(loc);
		this.width = width;
		this.height = height;
		theta = direction;
		this.image = image;
		this.entity = entity;
		setGrid(entity.getGrid());
	}
	
	
	/**
	 * Advances the position of the projectile
	 */
	public void act() {
		loc.x += Math.cos(theta) * velocity;
		loc.y += Math.sin(theta) * velocity;
	}
	
	/**
	 * Draws the projectile onto the screen
	 * @param g The Graphics object to be used
	 */
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(image, (int) (loc.x - width / 2), (int) (loc.y - height / 2),
				width, height, null);
	}
	
	public Enemy collision() {
		for (Enemy e : grid.getEnemies()) {
			final Point2D.Double l = e.getLocation();
			if (Math.hypot(loc.x - l.x, loc.y - l.y) <= e.getRadius() + width / 2)
				return e;
		}
		
		return null;
	}
	
	public boolean isOnScreen() {
		return loc.x >= r && loc.x <= Barrage.WIDTH - r && loc.y >= r && loc.y <= Barrage.HEIGHT - r;
	}

}
