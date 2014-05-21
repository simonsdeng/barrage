import java.awt.Image;

public abstract class Defense extends Structure {

	protected int health;
	protected int cost;

	public Defense(int x, int y, Image icon) {
		super(x, y, icon);
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealth() {
		return health;
	}

}
