import java.awt.Graphics2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Entity {

	protected double x;
	protected double y;
	protected List<Projectile> projectiles;
	protected Grid grid;
	protected int r;
	
	protected Entity(int x, int y) {
		this.x = x;
		this.y = y;
		projectiles = new CopyOnWriteArrayList<Projectile>();
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public int getRadius() {
		return r;
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public void setGrid(Grid g) {
		grid = g;
	}
	
	public void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	
	public void removeProjectile(Projectile p) {
		projectiles.remove(p);
	}
	
	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public abstract void draw(Graphics2D g);
	public abstract void act();
}
