import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * @author Vikram Idury
 * 
 *         Represents a basic structure on the map. The structures are bound by
 *         an imaginary grid of squares.
 */
public abstract class Structure extends Entity {

	protected Point gridLoc;
	protected Image img;

	/**
	 * 
	 * @param x
	 *            grid x-coordinate of single-square structure
	 * @param y
	 *            grid y-coordinate of single-square structure
	 * @param img
	 *            image that defines appearance of the structure
	 */
	protected Structure(Point gridLoc) {
		super(Grid.getCellCenter(gridLoc));
		this.gridLoc = gridLoc;
	}

	public Point getGridLocation() {
		return gridLoc;
	}
	
	public double getDirectionTowards(Point2D.Double l){
		return Math.tan((l.y - loc.y) / (l.x - loc.x));
	}
}
