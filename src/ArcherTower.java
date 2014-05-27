import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;


public class ArcherTower extends Defense {

	public static final Image icon = new ImageIcon("archertower.png").getImage();
	
	private final Grid grid;
	private static long time = 0;
	private static int delayTime = 150;
	private List<Projectile> projectiles;

	public ArcherTower(int gridX, int gridY, Grid grid) {
		super(gridX, gridY);
		this.grid = grid;
		projectiles = new ArrayList<Projectile>();
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(icon, (int)x, (int)y, Grid.CELL_SIZE, Grid.CELL_SIZE, null);
	}

	@Override
	public void act() {
		List<Enemy> proximity = getEnemiesInProximity(grid.getEnemies());
		if (proximity.size() > 0 && System.currentTimeMillis() - time >= delayTime){
			Enemy target = proximity.get((int)(Math.random() * proximity.size()));
			double dir = getDirectionTowards((int)target.getX(), (int)target.getY());
			addProjectile(new Arrow((int)getX(), (int)getY(), 10, 10, dir,grid,this));

		}
		time = System.currentTimeMillis();

	}
	
	private class Arrow extends Projectile {
		private int r;
		private ArcherTower tower;
		
		public Arrow(int x, int y, int height, int width, double direction,Grid grid, ArcherTower tower) {
			super(x, y, height, width, direction, grid, tower, img);
			r = width/2;
			this.tower = tower;
		}
		
		public boolean isOnScreen() {
			return getX() >= r && getX() <= Barrage.WIDTH - r && getY() >= r && getY() <= Barrage.HEIGHT - r;
		}
		
		@Override
		public void act() {
			super.act();
			if(!isOnScreen())
				tower.removeProjectile(this);

		}
		
	}

}
