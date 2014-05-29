import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;

public class Fireblast implements Spell {

	private int cost;
	private long time;
	private int delayTime;
	private Player player;
	private int damage;
	
	public static Image img = new ImageIcon("fireball.png").getImage();
	
	public Fireblast() {
		cost = 0;
		damage = 20;
		time = 0;
		delayTime = 150;
	}
	
	public int getCost() { return cost; }
	
	public void cast(Entity e) {
		if (player == null) player = (Player) e;
		player.setMana(player.getMana() - cost);
		
		if (System.currentTimeMillis() - time >= delayTime) {
			Point p = player.getPointer();
			Point2D.Double playerLoc = player.getLocation();
			double ang = Math.atan2(-(p.y - playerLoc.y), p.x - playerLoc.x)  - Math.PI / 2;
			double cos = Math.cos(ang), sin = Math.sin(ang);
			double dx = 5*cos - 35*sin, dy =- 35*cos - 5*sin;
			player.addProjectile(new FireblastProjectile(
					new Point2D.Double(playerLoc.x + dx, playerLoc.y + dy),
					20, 20, -(Math.PI / 2 + ang)));
			time = System.currentTimeMillis();
		}
	}
	
	private class FireblastProjectile extends Projectile {
		
		public FireblastProjectile(Point2D.Double loc, int height, int width, double direction) {
			super(loc, height, width, direction, player, img);
			r = width / 2;
		}
		
		@Override
		public void act() {
			super.act();
			if (!isOnScreen()) player.removeProjectile(this);
			
			Enemy e = collision();
	
			if (e != null) {
				player.removeProjectile(this);
				e.setHealth(e.getHealth() - damage);
			}
		}
		
	}


}
