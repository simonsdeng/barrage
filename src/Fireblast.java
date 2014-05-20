import java.awt.Image;

public class Fireblast extends Projectile implements Spell {
	
	private int level;
	private int r;
	
	public Fireblast(int x, int y, int height, int width, int level, double direction, Image image) {
		super(x, y, height, width, direction, image);
		this.level = level;
		r = width/2;
	}
	
	public boolean isActive() {
		return getX() >= r && getX() <= Barrage.WIDTH - r && getY() >= r && getY() <= Barrage.HEIGHT - r;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getDamage() {
		return level*5;
	}

}
