import java.awt.Image;



public abstract class Defense extends Structure {

	private int health;
	private int cost;
	
	
	public Defense(int[] xCoords, int[] yCoords, Image icon) {
		super(xCoords, yCoords, icon);
		// TODO Auto-generated constructor stub
	}

	public Defense(int x, int y, Image icon) {
		super(x, y, icon);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void act();
	
	public void setCost(int cost){
		this.cost = cost;
	}
	public int getCost(){
		return cost;
	}
	
	public void setHealth(int health){
		this.health = health;
	}
	public int getHealth(){
		return health;
	}
	
	public abstract void draw(Graphics g);
	

}
