import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Entity {

	protected Point2D.Double loc;
	protected List<Projectile> projectiles;
	protected Grid grid;
	protected int r;
	
	protected Entity(Point2D.Double loc) {
		this.loc = loc;
		projectiles = new CopyOnWriteArrayList<Projectile>();
	}
	
	public Point2D.Double getLocation() {
		return loc;
	}
	
	public void setLocation(Point2D.Double loc) {
		this.loc = loc;
	}
	
	public int getRadius() {
		return r;
	}
	
	public final Grid getGrid() {
		return grid;
	}
	
	public final void setGrid(Grid grid) {
		this.grid = grid;
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
