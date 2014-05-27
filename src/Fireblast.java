import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;

public class Fireblast implements Spell {

	private int cost;
	private static long time = 0;
	private static int delayTime = 150;
	private Player player;
	
	public static Image img = new ImageIcon("fireball.png").getImage();
	
	public Fireblast() {
		cost = 0;
	}
	
	public int getCost() { return cost; }
	
	public void cast(Entity e) {
		if (player == null) player = (Player) e;
		
		if (System.currentTimeMillis() - time >= delayTime) {
			Point p = player.getPointer();
			Point2D.Double playerLoc = player.getLocation();
			double ang = Math.atan2(-(p.y - playerLoc.y), p.x - playerLoc.x)  - Math.PI / 2;
			double cos = Math.cos(ang), sin = Math.sin(ang);
			double dx = 5*cos - 70*sin, dy =- 70*cos - 5*sin;
			player.addProjectile(new FireblastProjectile(
					new Point2D.Double(playerLoc.x + dx, playerLoc.y + dy),
					20, 20, -(Math.PI / 2 + ang)));
			time = System.currentTimeMillis();
		}
	}
	
	private class FireblastProjectile extends Projectile {
		private int r;
		
		public FireblastProjectile(Point2D.Double loc, int height, int width, double direction) {
			super(loc, height, width, direction, player, img);
			r = width/2;
		}
		
		public boolean isOnScreen() {
			return loc.x >= r && loc.x <= Barrage.WIDTH - r && loc.y >= r && loc.y <= Barrage.HEIGHT - r;
		}
		
		@Override
		public void act() {
			super.act();
			if (!isOnScreen()) player.removeProjectile(this);
			if (collision()) player.removeProjectile(this);
		}
		
	}


}
