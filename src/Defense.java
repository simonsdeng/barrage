public abstract class Defense extends Structure {

	protected int health;
	protected int cost;

	public Defense(int x, int y) {
		super(x, y);
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
