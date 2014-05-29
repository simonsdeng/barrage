import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A human player
 * 
 * @author Simon Deng
 * @author Nikhil Ghosh
 */
public class Player extends Entity {

	private int speed;
	private int health;
	private int gold;
	private int mana;
	private boolean left, right, up, down;
	private boolean placingDefense;
	private int activeDefense;
	private Point pointer;
	private Image img;
	private Spell[] spells = {new Fireblast(), new Iceblast(), new Teleport()};
	private Spell spell;
	private int kills;
	
	public static final int MANA_REGEN = 1;
	public static final int HEALTH_REGEN = 2;
	private long regenTime;
	private int regenDelayTime;
	
	/**
     * Creates a player at the specified location
     * 
     * @param x the x-coordinate of the player's location
     * @param y the y-coordinate of the player's location
     */
	public Player(Point2D.Double loc) {
		super(loc);
		activeDefense = 1;
		r = 25;
		gold = 100;
		kills = 0;
		speed = 7;
		health = 100;
		mana = 100;
		placingDefense = false;
		regenTime = 0;
		regenDelayTime = 150;
		pointer = new Point((int) loc.x, (int) loc.y);
		spell = spells[0];

		try {
			img = ImageIO.read(new File("wizard.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setLeft(boolean b) { left = b; }
	public void setRight(boolean b) { right = b; }
	public void setUp(boolean b) { up = b; }
	public void setDown(boolean b) { down = b; }
	public void setPointer(Point p) { pointer = p; }
	public void setMana(int m) { mana = m; }
	public void setSpell(Spell s) { spell = s; }
	
	public int getMana() { return mana; }
	public Point getPointer() { return pointer; }
	public int getLives() { return health; }
	public Spell getSpell() { return spell; }
	public Spell[] getSpells() { return spells; }
	public int getGold(){return gold;}
	public void setGold(int amount){gold = amount;}
	
	public int getKills(){return kills;}
	public void setKills(int amount){this.kills = amount;}
	
	public boolean isPlacingDefense(){return placingDefense;}
	public void setPlacingDefense(boolean placingDefense){this.placingDefense = placingDefense;}
	public void setActiveDefense(int defense){activeDefense = defense;}
	public int getActiveDefense(){return activeDefense;}
	
	/**
	 * Moves the player 
	 */
	@Override
	public void act() {
		//System.out.println(spell);
		
		if (System.currentTimeMillis() - regenTime >= regenDelayTime) {
			if (mana < 100 - MANA_REGEN)
				mana += MANA_REGEN;
			if (health < 100 - HEALTH_REGEN)
				health += HEALTH_REGEN;
			regenTime = System.currentTimeMillis();
		}

		int dx = 0, dy = 0;
		
		if (left) dx -= speed;
		if (right) dx += speed;
		if (up) dy -= speed;
		if (down) dy += speed;
		
		if (Math.abs(dx) > 0 && Math.abs(dy) > 0){
			dx /= Math.sqrt(2);
			dy /= Math.sqrt(2);
		}
		
		double x = loc.x + dx;
		double y = loc.y + dy;
		
		if (x < r) x = r;
		if (y < r) y = r;
		if (x + r > GamePanel.WIDTH) x = GamePanel.WIDTH - r;
		if (y + r > GamePanel.HEIGHT) y = GamePanel.HEIGHT - r;
		
		final Point cell = Grid.getContainingCell(new Point2D.Double(x, y));
		if (!grid.getStructureGrid()[cell.x][cell.y]) {
			loc.x = x;
			loc.y = y;
		}
	}
	
	public void castSpell(Spell s) {
		if(mana - s.getCost() >= 0) {
			s.cast(this);
		}
	}
	
	
	
	/**
	 * Draws the player
	 * 
	 * @param g the Graphics2D object to draw with
	 */
	@Override
	public void draw(Graphics2D g) {
		final double ang = Math.atan2(-(pointer.y - loc.y), pointer.x - loc.x) - Math.PI / 2;
		g.rotate(-ang, loc.x, loc.y);
		g.drawImage(img, (int) (loc.x + 10 - r), (int) (loc.y - 11 - r),
				r * 2, r * 2, null);
		g.rotate(ang, loc.x, loc.y);
	}

}
