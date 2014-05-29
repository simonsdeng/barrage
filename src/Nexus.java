import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Nexus extends Structure {
	
	private Color color;

	public Nexus(Point gridLoc) {
		super(gridLoc);
		health = 100;
		color = new Color(128, 0, 128);
	}

	@Override
	public void draw(Graphics2D g) {
		updateColor();
		g.setColor(color);
		g.fillRect((int)loc.x - Grid.CELL_SIZE / 2, (int)loc.y - Grid.CELL_SIZE/2,Grid.CELL_SIZE, Grid.CELL_SIZE);
		g.setColor(Color.BLACK);
	}
	
	private void updateColor(){
		double percentage = health/100.0;
		if (percentage > 1) percentage = 1;
		int value = (int)(percentage* 128);
		color = new Color(value, 0, value);
	}

	@Override
	public void act() {
		
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
