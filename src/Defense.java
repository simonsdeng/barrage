import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class Defense extends Structure {

	protected int health;
	protected int cost;
	protected int range;
	
	protected Defense(Point gridLoc) {
		super(gridLoc);
		range = 5 * Grid.CELL_SIZE;
	}
	
	public void setRange(int range) {
		this.range = range;
	}
	
	public int getRange() {
		return range;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
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
	
	public List<Enemy> getEnemiesInProximity(List<Enemy> enemies) {
		List<Enemy> close = new ArrayList<Enemy>();
		for (Enemy enemy: enemies) {
			double dist = loc.distance(enemy.getLocation());
			if (dist - range < .0001) {
				close.add(enemy);
			}
		}
		return close;
	}

}
