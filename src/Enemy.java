import java.awt.Graphics2D;

public class Enemy extends Entity {

	private int health;
	private int reward;

	protected Enemy(int x, int y, int reward) {
		super(x, y);
		this.reward = reward;
	}

	@Override
	public void draw(Graphics2D g) {

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
