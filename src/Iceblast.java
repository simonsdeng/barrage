import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import javax.swing.ImageIcon;


public class Iceblast implements Spell {
	
	private int cost;
	private long time;
	private int delayTime;
	private int freezeDelayTime;
	private Player player;

	public static Image img = new ImageIcon("icespell.png").getImage();
	
	public Iceblast() {
		cost = 10;
		time = 0;
		delayTime = 300;
		freezeDelayTime = 1000;
	}
	
	public int getCost() { return cost; }

	
	@Override
	public void cast(Entity e) {
		if(player == null)
			player = (Player) e;
		
		player.setMana(player.getMana() - cost);
		
		if(System.currentTimeMillis() - time >= delayTime) {
			Point p = player.getPointer();
			Point2D.Double loc = player.getLocation();
			double ang = Math.atan2(-(p.y - loc.y), p.x - loc.x)  - Math.PI / 2;
			double cos = Math.cos(ang), sin = Math.sin(ang);
			double dx = 5*cos - 70*sin, dy =- 70*cos - 5*sin;
			player.addProjectile(new IceblastProjectile(new Point2D.Double(loc.x + dx,
					loc.y + dy), 20, 20, -(Math.PI / 2 + ang)));

			time = System.currentTimeMillis();
		}
		
	}

	
	private class IceblastProjectile extends Projectile {
		private int r;
		
		public IceblastProjectile(Point2D.Double loc, int height, int width, double direction) {
			super(loc, height, width, direction, player, img);
			r = width/2;
		}
		
		public boolean isOnScreen() {
			return loc.x >= r && loc.x <= Barrage.WIDTH - r && loc.y >= r && loc.y <= Barrage.HEIGHT - r;
		}
		
		@Override
		public void act() {
			super.act();
			if(!isOnScreen())
				player.removeProjectile(this);
			
			Enemy e = collision();
			if(e != null) {
				player.removeProjectile(this);
				e.freeze();
				e.setFreezeTime(freezeDelayTime);
			}
		}
		
	}


}
