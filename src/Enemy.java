import java.awt.Graphics2D;

public class Enemy extends Entity {

	private int health;
	private int reward;
	private int r;

	protected Enemy(int x, int y, int reward, Grid grid) {
		super(x, y);
		this.reward = reward;
		this.grid = grid;
		r = 20;
	}
	
	public int getRadius() { return r; }
	
	@Override
	public void draw(Graphics2D g) {
		g.fillOval((int)(x - r), (int)(y - r), 2*r, 2*r);
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
