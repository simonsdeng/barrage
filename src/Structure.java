import java.awt.Image;

/**
 * @author Vikram Idury
 * 
 * Represents a basic structure on the map. The structures are bound by an imaginary grid of squares.
 */
public abstract class Structure extends Entity {

	public static int GRID_SIZE = 20;
	
	protected int gridX;
	protected int gridY;
	protected Image img;
	
	/**
	 * 
	 * @param x grid x-coordinate of single-square structure
	 * @param y grid y-coordinate of single-square structure
	 * @param img image that defines appearance of the structure
	 */
	public Structure(int gridX, int gridY, Image img) {
		super((2 * gridX + 1) * GRID_SIZE / 2, (2 * gridY + 1) * GRID_SIZE / 2);
		this.gridX = gridX;
		this.gridY = gridY;
	}
	
	public int getGridX() {
		return gridX;
	}
	
	public int getGridY() {
		return gridY;
	}

}
