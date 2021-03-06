import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;

import javax.swing.ImageIcon;

public class ArcherTower extends Defense {

	public static final Image projectileImage = new ImageIcon("shuriken.gif")
			.getImage();
	public static final Image icon = new ImageIcon("archertower.png")
			.getImage();

	public static final int COST = 50;
	private long time = 0;
	private int delayTime = 500;

	public ArcherTower(Point gridLoc) {
		super(gridLoc);
		setCost(COST);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(icon, gridLoc.x * Grid.CELL_SIZE, gridLoc.y
				* Grid.CELL_SIZE, Grid.CELL_SIZE, Grid.CELL_SIZE, null);
	}

	@Override
	public void act() {
		if (System.currentTimeMillis() - time >= delayTime) {
			List<Enemy> proximity = getEnemiesInProximity(grid.getEnemies());
			if (proximity.size() > 0) {
				Enemy target = proximity.get((int) (Math.random() * proximity
						.size()));
				double dir = getDirectionTowards(target.getLocation());
				addProjectile(new Arrow(new Point2D.Double(loc.x, loc.y), 10,
						10, dir, grid, this));
				time = System.currentTimeMillis();
			}
		}
	}

	private class Arrow extends Projectile {
		private ArcherTower tower;
		private int damage;

		public Arrow(Point2D.Double loc, int height, int width,
				double direction, Grid grid, ArcherTower tower) {
			super(loc, height, width, direction, tower, projectileImage);
			r = width / 2;
			velocity = 10;
			damage = 10;
			this.tower = tower;
		}

		@Override
		public void act() {
			super.act();
			if (!isOnScreen())
				tower.removeProjectile(this);

			Enemy e = collision();

			if (e != null) {
				tower.removeProjectile(this);
				e.setHealth(e.getHealth() - damage);
			}
		}
	}

}
