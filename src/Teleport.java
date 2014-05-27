import java.awt.Point;


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
		if(player == null)
			player = (Player) e;
		
		if(System.currentTimeMillis() - time >= delayTime) {
			Point p = player.getPointer();
			
			boolean clear = true;
			for(Entity ge : player.getGrid().getEntities()) {
				if(Math.hypot(ge.getX() - p.getX(), ge.getY() - p.getY()) <= ge.getRadius()) {
					clear = false;
					break;
				}			
			}
			
			if(clear) {
				player.setX(p.x);
				player.setY(p.y);
			}	
				
			time = System.currentTimeMillis();
		}
		
	}

	@Override
	public int getCost() {
		return cost;
	}

}
