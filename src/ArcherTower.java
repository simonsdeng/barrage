import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;

import javax.swing.ImageIcon;

public class ArcherTower extends Defense {

	public static final Image projectileImage = new ImageIcon("shuriken.gif").getImage();
	public static final Image icon = new ImageIcon("archertower.png").getImage();
	
	private static long time = 0;
	private static int delayTime = 200;

	public ArcherTower(Point gridLoc) {
		super(gridLoc);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(icon, gridLoc.x * Grid.CELL_SIZE, gridLoc.y * Grid.CELL_SIZE,
				Grid.CELL_SIZE, Grid.CELL_SIZE, null);
	}

	@Override
	public void act() {
		List<Enemy> proximity = getEnemiesInProximity(grid.getEnemies());
		if (proximity.size() > 0 && System.currentTimeMillis() - time >= delayTime){
			Enemy target = proximity.get((int)(Math.random() * proximity.size()));
			double dir = getDirectionTowards(target.getLocation());
			addProjectile(new Arrow(new Point2D.Double(loc.x, loc.y), 10, 10, dir,grid,this));
			time = System.currentTimeMillis();
		}
	}
	
	private class Arrow extends Projectile {
		private ArcherTower tower;
		
		public Arrow(Point2D.Double loc, int height, int width, double direction,Grid grid, ArcherTower tower) {
			super(loc, height, width, direction, tower, projectileImage);
			r = width/2;
			velocity = 5;
			this.tower = tower;
		}
		
		@Override
		public void act() {
			super.act();
			if (!isOnScreen()) tower.removeProjectile(this);
			if (collision() != null) tower.removeProjectile(this);
		}
	}

}
