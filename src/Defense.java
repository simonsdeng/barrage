import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Defense extends Structure {

	protected int health;
	protected int cost;
	protected int range;
	

	public Defense(int x, int y, Image icon) {
		super(x, y, icon);
	}
	
	public void setRange(int range){
		this.range = range;
	}
	
	public int getRange(){
		return range;
	}
	
	public void setCost(int cost){
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

	
	public ArrayList<Enemy> getEnemiesInProximity(ArrayList<Enemy> enemies){
		ArrayList<Enemy> close = new ArrayList<Enemy>();
		for (Enemy enemy: enemies){
			Point e = new Point(enemy.getX(), enemy.getY());
			Point p = new Point(getX(), getY());
			double dist = e.distance(p);
			if (dist - range < .0001){
				close.add(enemy);
			}
		}
		return close;
	}

}
