import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

public class Enemy extends Entity {

	private double speed;
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
	
	public static final int MAX_HEALTH = 50;
	private Color color;

	public Enemy(Point2D.Double loc, int reward) {
		super(loc);
		this.reward = reward;
		alive = true;
		frozen = false;
		freezeTime = 0;
		freezeDelayTime = 0;
		health = MAX_HEALTH;
		r = 20;
		speed = 1;
		color = Color.BLACK;
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
		final Entity newTarget = player.getLocation().distance(loc) < nexus.getLocation().distance(loc) + 100
				? player : nexus;
		if (newTarget != target) {
			target = newTarget;
			if (path != null) path.dispose();
			path = grid.getPath(this, target);
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		updateColor();
		g.setColor(color);
		g.fillOval((int) (loc.x - r), (int) (loc.y - r), 2 * r, 2 * r);
	}
	
	private void updateColor(){
		double percentage = 1 - health/(double)MAX_HEALTH;
		if (percentage > 1) percentage = 1;
		int value = (int)(percentage* 255);
		color = new Color(value, value, value);
	}
	
	@Override
	public void act() {
		if (health <= 0){
			grid.remove(this);
			player.setGold(player.getGold() + reward);
			player.setKills(player.getKills() + 1);
		}
		
		if (frozen) {
			if (System.currentTimeMillis() - freezeTime >= freezeDelayTime) {
				frozen = false;
			} else {
				return;
			}
		}
		
		chooseTarget();
		
		final Point currentCell = Grid.getContainingCell(this);
		
		if (currentCell.equals(waypoint)) {
			waypoint = path.getNext();
		}
		
		if (!currentCell.equals(waypoint)) {
			moveTowards(Grid.getCellCenter(waypoint));
		} else if (loc.distance(target.getLocation()) > r + target.getRadius()) {
			moveTowards(target.getLocation());
		} else {
			target.setHealth(target.getHealth() - 1);
		}
	}
	
	public void freeze() {
		frozen = true;
		freezeTime = System.currentTimeMillis();
	}
	
	private void moveTowards(Point2D.Double p) {
		final double ang = Math.atan2(loc.y - p.y, p.x - loc.x);
		loc.x += Math.cos(ang) * speed;
		loc.y -= Math.sin(ang) * speed;
	}
	
	public int getReward() {
		return reward;
	}
	
	public void setFreezeTime(int freezeDelayTime) {
		this.freezeDelayTime = freezeDelayTime;
	}

}
