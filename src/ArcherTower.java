import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class ArcherTower extends Defense {

	public static final Image icon = new ImageIcon("archertower.png").getImage();
	private ArrayList<Enemy> enemies;
	
	private ArrayList<Projectile> projectiles;

	public ArcherTower(int gridX, int gridY, ArrayList<Enemy> enemies) {
		super(gridX, gridY);
		this.enemies = enemies;
		projectiles = new ArrayList<Projectile>();
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(icon, x, y, Grid.CELL_SIZE, Grid.CELL_SIZE, null);
		
	}

	@Override
	public void act() {
		ArrayList<Enemy> proximity = getEnemiesInProximity(enemies);
		Enemy target = proximity.get((int)(Math.random() * proximity.size()));
		double dir = getDirectionTowards(target.getX(), target.getY());
//		projectiles.add(new Projectile(getX() + Structure.GRID_SIZE/2, getY() + Structure.GRID_SIZE/2, 10, 10, dir,))
		for (int i = 0; i < projectiles.size(); i++){
			Projectile p = projectiles.get(i);
			if (!p.isActive()){ // Change this according to inactive projectile detection
				projectiles.remove(i);
			}
			p.act();
		}
	}

}
