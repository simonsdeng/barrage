import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Enemy extends Entity {

	private int health;
	private int reward;
	private int r;

	public Enemy(Point2D.Double loc, int reward) {
		super(loc);
		this.reward = reward;
		r = 20;
	}
	
	public int getRadius() { return r; }
	
	@Override
	public void draw(Graphics2D g) {
		g.fillOval((int) (loc.x - r), (int) (loc.y - r), 2 * r, 2 * r);
	}

	@Override
	public void act() {

	}

	public int getReward() {
		return reward;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}

}
