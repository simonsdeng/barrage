import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid {

	public static int CELL_SIZE = 40;
	public static int COLS = (Barrage.WIDTH - 1) / CELL_SIZE + 1;
	public static int ROWS = (Barrage.HEIGHT - 1) / CELL_SIZE + 1;
	
	private Player player;
	private Nexus nexus;
	private List<Enemy> enemies;
	private List<Defense> defenses;
	private List<Entity> entities;
	private List<Structure> structures;
	private boolean[][] entityGrid;
	private boolean[][] structureGrid;
	
	public Grid(Player player, Nexus nexus) {
		this.player = player;
		this.nexus = nexus;
		enemies = new ArrayList<Enemy>();
		defenses = new ArrayList<Defense>();
		entities = new ArrayList<Entity>();
		structures = new ArrayList<Structure>();
		entityGrid = new boolean[COLS][ROWS];
		structureGrid = new boolean[COLS][ROWS];
		
		add(player);
		add(nexus);
	}
	
	public static Point getCellCenter(int gridX, int gridY) {
		return new Point((2 * gridX + 1) * Grid.CELL_SIZE / 2, (2 * gridY + 1) * Grid.CELL_SIZE / 2);
	}
	
	public static Point getContainingCell(Entity e) {
		return new Point(e.getX() / CELL_SIZE, e.getY() / CELL_SIZE);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Nexus getNexus() {
		return nexus;
	}
	
	public List<Enemy> getEnemies() {
		return enemies;
	}
	
	public List<Defense> getDefenses() {
		return defenses;
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public List<Structure> getStructures() {
		return structures;
	}
	
	public boolean[][] getEntityGrid() {
		return entityGrid;
	}
	
	public boolean[][] getStructureGrid() {
		return structureGrid;
	}
	
	public void add(Enemy enemy) {
		enemies.add(enemy);
		add((Entity) enemy);
	}
	
	public void add(Defense defense) {
		defenses.add(defense);
		add((Structure) defense);
	}

	public final void add(Structure structure) {
		structures.add(structure);
		updateStructures();
		add((Entity) structure);
	}
	
	public final void add(Entity entity) {
		entities.add(entity);
		updateEntities();
	}
	
	public void remove(Enemy enemy) {
		enemies.remove(enemy);
		remove((Entity) enemy);
	}
	
	public void remove(Defense defense) {
		defenses.remove(defense);
		remove((Structure) defense);
	}
	
	public void remove(Structure structure) {
		structures.remove(structure);
		updateStructures();
		remove((Entity) structure);
	}
	
	public void remove(Entity entity) {
		entities.remove(entity);
		updateEntities();
	}
	
	public final void updateEntities() {
		for (boolean[] r : entityGrid) Arrays.fill(r, false);
		
		for (Entity e : entities) {
			Point cell = getContainingCell(e);
			entityGrid[cell.x][cell.y] = true;
		}
	}
	
	public final void updateStructures() {
		for (boolean[] r : structureGrid) Arrays.fill(r, false);
		
		for (Structure s : structures) {
			structureGrid[s.getGridX()][s.getGridY()] = true;
		}
	}

}
