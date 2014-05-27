import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class QuakeTower extends Defense {

	public static final Image icon = new ImageIcon("quaketower.png").getImage();
	public static final int damage = 25;

	private ArrayList<Projectile> projectiles;

	public QuakeTower(Point gridLoc, Grid grid) {
		super(gridLoc, grid);
		projectiles = new ArrayList<Projectile>();
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(icon, gridLoc.x * Grid.CELL_SIZE, gridLoc.y * Grid.CELL_SIZE,
				Grid.CELL_SIZE, Grid.CELL_SIZE, null);

	}

	@Override
	public void act() {
		ArrayList<Enemy> proximity = getEnemiesInProximity(grid.getEnemies());
		if (proximity.size() > 0){
			for(Enemy enemy: proximity){
				enemy.setHealth(enemy.getHealth() - damage);
			}
		}
	}

}
