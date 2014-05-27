import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

public class Enemy extends Entity {

	private int speed;
	private int health;
	private int reward;
	private boolean alive;
	private boolean frozen;
	private long freezeTime;
	private int freezeDelayTime;
	private Player player;
	private Nexus nexus;
	private Entity target;
	private Grid.Path path;
	private Point waypoint;

	public Enemy(Point2D.Double loc, int reward) {
		super(loc);
		this.reward = reward;
		alive = true;
		frozen = false;
		freezeTime = 0;
		freezeDelayTime = 0;
		health = 100;
		r = 20;
		speed = 10;
	}
	
	@Override
	public void setGrid(Grid grid) {
		super.setGrid(grid);
		
		player = grid.getPlayer();
		nexus = grid.getNexus();
		chooseTarget();
		waypoint = path.getNext();
	}
	
	private void chooseTarget() {
		target = player.getLocation().distance(loc) < nexus.getLocation().distance(loc)
				? player : nexus;
		path = grid.getPath(this, target);
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.fillOval((int) (loc.x - r), (int) (loc.y - r), 2 * r, 2 * r);
	}
	
	@Override
	public void act() {
		if (health <= 0) alive = false;
		
		if (frozen && System.currentTimeMillis() - freezeTime >= freezeDelayTime)
			frozen = false;
		
		chooseTarget();
		
		if (Grid.getContainingCell(this).equals(waypoint)) {
			waypoint = path.getNext();
		}
		
		if (waypoint != null) {
			moveTowards(Grid.getCellCenter(waypoint));
		} else {
			moveTowards(target.getLocation());
		}
	}
	
	public void freeze() {
		frozen = true;
		freezeTime = System.currentTimeMillis();
	}
	
	private void moveTowards(Point2D.Double p) {
		final double ang = Math.atan2(loc.y - p.y, p.x - loc.x);
		loc.x += Math.cos(ang);
		loc.y -= Math.sin(ang);
	}
	
	public int getReward() {
		return reward;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setFreezeTime(int freezeDelayTime) {
		this.freezeDelayTime = freezeDelayTime;
	}

}
