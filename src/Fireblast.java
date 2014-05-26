import java.awt.Image;
import java.awt.Point;

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
		if(player == null)
			player = (Player) e;
		if(System.currentTimeMillis() - time >= delayTime) {
			Point p = player.getPointer();
			double ang = Math.atan2(-(p.getY() - player.getY()), p.getX() - player.getX())  - Math.PI / 2;
			double cos = Math.cos(ang), sin = Math.sin(ang);
			double dx = 5*cos - 70*sin, dy =- 70*cos - 5*sin;
			player.addProjectile(new FireblastProjectile((int)(player.getX() + dx), (int)(player.getY() + dy), 20, 20, -(Math.PI / 2 + ang)));
			time = System.currentTimeMillis();
		}
	}
	
	private class FireblastProjectile extends Projectile {
		private int r;
		
		public FireblastProjectile(int x, int y, int height, int width, double direction) {
			super(x, y, height, width, direction, player.getGrid(), player, img);
			r = width/2;
		}
		
		public boolean isOnScreen() {
			return getX() >= r && getX() <= Barrage.WIDTH - r && getY() >= r && getY() <= Barrage.HEIGHT - r;
		}
		
		@Override
		public void act() {
			super.act();
			if(!isOnScreen())
				player.removeProjectile(this);
			
			if(collision())
				player.removeProjectile(this);
		}
		
	}


}
