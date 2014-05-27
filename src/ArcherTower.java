import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class ArcherTower extends Defense {

	public static final Image icon = new ImageIcon("archertower.png").getImage();
	private List<Projectile> projectiles;

	public ArcherTower(Point gridLoc, Grid grid) {
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
		List<Enemy> proximity = getEnemiesInProximity(grid.getEnemies());

		if (proximity.size() > 0){
			Enemy target = proximity.get((int) (Math.random() * proximity.size()));
			double dir = getDirectionTowards(target.getLocation());
		}
//		projectiles.add(new Projectile(getX() + Structure.GRID_SIZE/2, getY() + Structure.GRID_SIZE/2, 10, 10, dir,))
		for (int i = 0; i < projectiles.size(); i++){
			Projectile p = projectiles.get(i);
//			if (!p.isActive()){ // Change this according to inactive projectile detection
//				projectiles.remove(i);
//			}
			p.act();
		}
	}

}
