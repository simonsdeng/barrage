import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class QuakeTower extends Defense {

	public static final Image icon = new ImageIcon("quaketower.png").getImage();
	
	private static long time = 0;
	private static int delayTime = 300;
	public static final int damage = 25;
	
	public QuakeTower(Point gridLoc) {
		super(gridLoc);
		projectiles = new ArrayList<Projectile>();
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(icon, gridLoc.x * Grid.CELL_SIZE, gridLoc.y * Grid.CELL_SIZE,
				Grid.CELL_SIZE, Grid.CELL_SIZE, null);
	}
	
	@Override
	public void act() {
		List<Enemy> proximity = getEnemiesInProximity(grid.getEnemies());
		if (proximity.size() > 0 && System.currentTimeMillis() - time > delayTime) {
			for (Enemy enemy: proximity) enemy.setHealth(enemy.getHealth() - damage);
			time = System.currentTimeMillis();
		}

	}

}
