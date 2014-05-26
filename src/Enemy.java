import java.awt.Graphics2D;

public class Enemy extends Entity {

	private int health;
	private int reward;
	private Grid grid;

	protected Enemy(int x, int y, int reward, Grid grid) {
		super(x, y);
		this.reward = reward;
		this.grid = grid;
	}

	@Override
	public void draw(Graphics2D g) {
		g.fillOval((int)(x - 20), (int)(y - 20), 40, 40);
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
