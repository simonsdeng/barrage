import java.awt.Image;


public abstract class Structure {

	public static int GRID_SIZE = 20;
	
	private int[] xCoords, yCoords;
	private Image icon;
	
	public Structure(int[] xCoords, int[] yCoords, Image icon) {
		this.xCoords = xCoords;
		this.yCoords = yCoords;
		this.icon = icon;
	}
	
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

	public void moveTo(int[] xCoords, int[] yCoords){
		this.xCoords = xCoords;
		this.yCoords = yCoords;
	}
	
	public abstract void act();
}
