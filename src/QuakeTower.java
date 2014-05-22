import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class QuakeTower extends Defense {

	public static final Image icon = new ImageIcon("quaketower.png").getImage();
	private ArrayList<Enemy> enemies;
	
	public static final int damage = 25;

	private ArrayList<Projectile> projectiles;

	public QuakeTower(int gridX, int gridY, ArrayList<Enemy> enemies) {
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
		for(Enemy enemy: proximity){
			enemy.setHealth(enemy.getHealth() - damage);
		}
	}

}
