import java.awt.Image;

public class Fireblast extends Projectile {
	
	private int level;
	private int r;
	
	public Fireblast(int x, int y, int height, int width, int level, double direction, Image image) {
		super(x, y, height, width, direction, image);
		setShooter(this);
		this.level = level;
		r = width/2;
	}
	
	public boolean isOnScreen() {
		return getX() >= r && getX() <= Barrage.WIDTH - r && getY() >= r && getY() <= Barrage.HEIGHT - r;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getDamage() {
		return level*5;
	}

}
