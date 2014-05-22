import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
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

	private int width, height;
	private int speed;
	private int lives;
	private boolean left, right, up, down;
	private Point pointer;
	private Image img;
	
	/**
     * Creates a player at the specified location
     * 
     * @param x the x-coordinate of the player's location
     * @param y the y-coordinate of the player's location
     */
	public Player(int x, int y) {
		super(x, y);
		width = 100;
		height = 100;
		speed = 7;
		lives = 3;
		pointer = new Point(x, y);

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
	public void setPointer(Point p) { pointer = p; };
	
	/**
	 * Moves the player 
	 */
	@Override
	public void act() {
		int dx = 0, dy = 0;
		
		if (left) dx -= speed;
		if (right) dx += speed;
		if (up) dy -= speed;
		if (down) dy += speed;
		
		if (Math.abs(dx) > 0 && Math.abs(dy) > 0){
			dx /= Math.sqrt(2);
			dy /= Math.sqrt(2);
		}
		
		x += dx;
		y += dy;
		
		final int hw = width / 2;
		final int hh = height / 2;
		
		if (x < hh) x = hw;
		if (y < hw) y = hh;
		if (x + hw > Barrage.WIDTH) x = Barrage.WIDTH - hw;
		if (y + hh > Barrage.HEIGHT) y = Barrage.HEIGHT - hh;
	}
	
	public void castSpell(Spell s) {
		s.cast(this);
	}
	
	/**
	 * Draws the player
	 * 
	 * @param g the Graphics2D object to draw with
	 */
	@Override
	public void draw(Graphics2D g) {
		final double ang = Math.atan2(-(pointer.y - y), pointer.x - x) - Math.PI / 2;
		
		g.rotate(-ang, x, y);
		g.drawImage(img, x + 19 - width / 2, y - 22 - height / 2, width, height, null);
		g.rotate(ang, x, y);
	}

}
