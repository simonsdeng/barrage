import java.awt.Graphics;

public interface Spell {
	
	public void act();
	public void draw(Graphics g);
	public boolean isActive();
	
}
