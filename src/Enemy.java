import java.awt.Graphics2D;

public class Enemy extends Entity {

	private int health;
	private int reward;
	private boolean alive;
	private boolean frozen;
	private long freezeTime;
	private int freezeDelayTime;

	protected Enemy(int x, int y, int reward, Grid grid) {
		super(x, y);
		this.reward = reward;
		this.grid = grid;
		alive = true;
		frozen = false;
		freezeTime = 0;
		freezeDelayTime = 0;
		health = 100;
		r = 20;
	}
	
	
	@Override
	public void draw(Graphics2D g) {
		g.fillOval((int)(x - r), (int)(y - r), 2*r, 2*r);
	}

	@Override
	public void act() {
		
		if(health <= 0) 
			alive = false;
		
		if(frozen && System.currentTimeMillis() - freezeTime >= freezeDelayTime) {
			frozen = false;
			System.out.println("UNFROZEN");
		}
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
