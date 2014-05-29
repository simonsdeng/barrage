import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Nexus extends Structure {
	
	private int health;

	public Nexus(Point gridLoc) {
		super(gridLoc);
		health = 100;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.MAGENTA);
		g.fillRect((int)loc.x, (int)loc.y,Grid.CELL_SIZE, Grid.CELL_SIZE);
		g.setColor(Color.BLACK);
	}

	@Override
	public void act() {
		if (health <= 0){
			System.out.println("You lose!");
		}
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
