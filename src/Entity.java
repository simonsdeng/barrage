import java.awt.Graphics2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Entity {

	protected int x;
	protected int y;
	protected List<Projectile> projectiles;
	
	protected Entity(int x, int y) {
		this.x = x;
		this.y = y;
		projectiles = new CopyOnWriteArrayList<Projectile>();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
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
