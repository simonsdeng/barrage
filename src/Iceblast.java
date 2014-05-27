import java.awt.Image;
import java.awt.Point;

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
			double ang = Math.atan2(-(p.getY() - player.getY()), p.getX() - player.getX())  - Math.PI / 2;
			double cos = Math.cos(ang), sin = Math.sin(ang);
			double dx = 5*cos - 70*sin, dy =- 70*cos - 5*sin;
			player.addProjectile(new IceblastProjectile((int)(player.getX() + dx), (int)(player.getY() + dy), 20, 20, -(Math.PI / 2 + ang)));

			time = System.currentTimeMillis();
		}
		
	}

	
	private class IceblastProjectile extends Projectile {
		private int r;
		
		public IceblastProjectile(int x, int y, int height, int width, double direction) {
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
			
			Enemy e = collision();
			if(e != null) {
				player.removeProjectile(this);
				e.freeze();
				e.setFreezeTime(freezeDelayTime);
			}
		}
		
	}


}
