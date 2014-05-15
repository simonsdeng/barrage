import java.awt.Graphics;
import java.awt.Image;


public abstract class Projectile {

	private int x, y;
	private static final int velocity = 10;
	
	private Image image;
	
	public Projectile(int x, int y, Image image){
		this.x = x;
		this.y = y;
		this.image = image;
	}
	
	public void act(){
		x += 10;
	}
	
	public void draw(Graphics g){
		g.drawImage(image, 10, 10, null);
	}
	

	
}
