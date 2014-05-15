import java.awt.Graphics;
import java.awt.Image;

/**
 * @author Vikram Idury
 * 
 * Represents a basic structure on the map. The structures are bound by an imaginary grid of squares.
 */
public abstract class Structure {

	public static int GRID_SIZE = 20;
	
	private int[] xCoords, yCoords;
	private Image icon;
	
	/**
	 * 
	 * @param xCoords x coordinates of squares that make up structure
	 * @param yCoords y coordinates of squares that make up structure
	 * @param icon image that defines the appearance of the structure
	 */
	public Structure(int[] xCoords, int[] yCoords, Image icon) {
		this.xCoords = xCoords;
		this.yCoords = yCoords;
		this.icon = icon;
	}
	
	/**
	 * 
	 * @param x x coordinate of single-square structure
	 * @param y y coordinate of single-square structure
	 * @param icon image that defines appearance of the structure
	 */
	public Structure(int x, int y, Image icon){
		xCoords = new int[] {x};
		yCoords = new int[] {y};
		this.icon = icon;
	}

	public int[] getXCoords() {
		return xCoords;
	}

	public int[] getYCoords() {
		return yCoords;
	}

	/**
	 * Moves the structure to a new location
	 * 
	 * @param xCoords the new x coordinates of structure squares
	 * @param yCoords the new y coordinates of structure squares
	 */
	public void moveTo(int[] xCoords, int[] yCoords){
		this.xCoords = xCoords;
		this.yCoords = yCoords;
	}
	
	public abstract void draw(Graphics g);
	
	public abstract void act();
}
