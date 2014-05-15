import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {

	private Point loc;
	private int width, height;
	private int speed;
	private int lives;
	private boolean left, right, up, down;
	private Point pointer;
	private Image img;
	
	public Player(int x, int y) {
		width = 100;
		height = 100;
		loc = new Point(x, y);
		speed = 10;
		lives = 3;
		pointer = new Point(x, y);
		try {
			img = ImageIO.read(new File("soldier.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Point getLocation() { return loc; }
	
	public void setLeft(boolean b) { left = b; }
	public void setRight(boolean b) { right = b; }
	public void setUp(boolean b) { up = b; }
	public void setDown(boolean b) { down = b; }
	public void setPointer(Point p) { pointer = p; };
	
	public void act() {
		int dx = 0, dy = 0;
		
		if (left) dx -= speed;
		if (right) dx += speed;
		if (up) dy -= speed;
		if (down) dy += speed;
		
		loc.x += dx;
		loc.y += dy;
		
		final int hw = width / 2;
		final int hh = height / 2;
		
		if (loc.x < hh) loc.x = hw;
		if (loc.y < hw) loc.y = hh;
		if (loc.x + hw > Barrage.WIDTH) loc.x = Barrage.WIDTH - hw;
		if (loc.y + hh > Barrage.HEIGHT) loc.y = Barrage.HEIGHT - hh;
	}
	
	public void draw(Graphics2D g) {
		final double ang = Math.atan2(-(pointer.y - loc.y), pointer.x - loc.x) - Math.PI / 2;
		
		g.rotate(-ang, loc.x, loc.y);
		g.drawImage(img, loc.x - width / 2, loc.y - height / 2, width, height, null);
		g.rotate(ang, loc.x, loc.y);
	}

}