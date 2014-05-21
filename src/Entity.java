import java.awt.Graphics2D;

public abstract class Entity {

	protected int x;
	protected int y;
	
	protected Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public abstract void draw(Graphics2D g);
	public abstract void act();
}
