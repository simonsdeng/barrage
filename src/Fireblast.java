import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Fireblast extends Projectile implements Spell {
	
	private int level;
	private int r;
	private Entity entity;
	
	public static Image img = null;
	static {
		try { img = ImageIO.read(new File("fireball.png")); } catch (Exception e) {}
	}
	
	public Fireblast(int x, int y, int height, int width, int level, double direction) {
		super(x, y, height, width, direction, img);
		setShooter(this);
		this.level = level;
		r = width/2;
	}
	
	public void cast(Entity e) {
		entity = e;
		e.addProjectile(this);
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
	
	@Override
	public void act() {
		super.act();
		if (!isOnScreen())
			entity.removeProjectile(this);
	}

}
