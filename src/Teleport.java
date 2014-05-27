import java.awt.Point;
import java.awt.geom.Point2D;

public class Teleport implements Spell {

	private int cost;
	private long time;
	private int delayTime;
	private Player player;
	
	public Teleport() {
		cost = 10;
		time = 0;
		delayTime = 300;
	}
	
	@Override
	public void cast(Entity e) {
		if (player == null)
			player = (Player) e;
		
		if (System.currentTimeMillis() - time >= delayTime) {
			final Point p = player.getPointer(); 
			final Point cell = Grid.getContainingCell(new Point2D.Double(p.x, p.y));
			
			if (player.getGrid().getEntityGrid()[cell.x][cell.y]) {
				player.setLocation(new Point2D.Double());
			}
				
			time = System.currentTimeMillis();
		}
		
	}

	@Override
	public int getCost() {
		return cost;
	}

}
