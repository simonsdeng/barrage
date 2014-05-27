import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Enemy extends Entity {

	private int health;
	private int reward;
	private boolean alive;
	private boolean frozen;
	private long freezeTime;
	private int freezeDelayTime;

	public Enemy(Point2D.Double loc, int reward) {
		super(loc);
		this.reward = reward;
		alive = true;
		frozen = false;
		freezeTime = 0;
		freezeDelayTime = 0;
		health = 100;
		r = 20;
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		g.fillOval((int) (loc.x - r), (int) (loc.y - r), 2 * r, 2 * r);
	}

	@Override
	public void act() {
		
		if(health <= 0) 
			alive = false;
		
		if(frozen && System.currentTimeMillis() - freezeTime >= freezeDelayTime) 
			frozen = false;		
	}
	
	public void freeze() {
		frozen = true;
		freezeTime = System.currentTimeMillis();
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
	
	public void setFreezeTime(int freezeDelayTime) {
		this.freezeDelayTime = freezeDelayTime;
	}

}
