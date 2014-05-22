import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid {

	public static int CELL_SIZE = 40;
	public static int COLS = (Barrage.WIDTH - 1) / CELL_SIZE + 1;
	public static int ROWS = (Barrage.HEIGHT - 1) / CELL_SIZE + 1;
	
	private List<Entity> entities;
	private List<Structure> structures;
	private boolean[][] entityGrid;
	private boolean[][] structureGrid;
	
	public Grid() {
		entities = new ArrayList<Entity>();
		structures = new ArrayList<Structure>();
		
		entityGrid = new boolean[COLS][ROWS];
		structureGrid = new boolean[COLS][ROWS];
	}
	
	public boolean[][] getEntityGrid() {
		return entityGrid;
	}
	
	public boolean[][] getStructureGrid() {
		return structureGrid;
	}
	
	public void add(Entity entity) {
		entities.add(entity);
		updateEntities();
	}

	public void add(Structure structure) {
		structures.add(structure);
		updateStructures();
	}
	
	public void remove(Entity entity) {
		entities.remove(entity);
		updateEntities();
	}
	
	public void remove(Structure structure) {
		structures.remove(structure);
		updateStructures();
	}
	
	public void updateEntities() {
		for (boolean[] r : entityGrid) Arrays.fill(r, false);
		
		for (Entity e : entities) {
			entityGrid[e.getX() / CELL_SIZE][e.getY() / CELL_SIZE] = true;
		}
	}
	
	public void updateStructures() {
		for (boolean[] r : structureGrid) Arrays.fill(r, false);
		
		for (Structure s : structures) {
			structureGrid[s.getGridX()][s.getGridY()] = true;
		}
	}

}
