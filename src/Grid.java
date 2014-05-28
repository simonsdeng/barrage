import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;

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
	private List<Path> activePaths;
	
	public Grid(Player player, Nexus nexus) {
		this.player = player;
		this.nexus = nexus;
		enemies = new CopyOnWriteArrayList<Enemy>();
		defenses = new CopyOnWriteArrayList<Defense>();
		entities = new CopyOnWriteArrayList<Entity>();
		structures = new CopyOnWriteArrayList<Structure>();
		entityGrid = new boolean[COLS][ROWS];
		structureGrid = new boolean[COLS][ROWS];
		activePaths = new CopyOnWriteArrayList<Path>();
		
		add(player);
		add(nexus);
	}
	
	public static Point2D.Double getCellCenter(Point gridLoc) {
		return new Point2D.Double((2 * gridLoc.x + 1) * CELL_SIZE / 2.0,
				(2 * gridLoc.y + 1) * CELL_SIZE / 2.0);
	}
	
	public static Point getContainingCell(Entity e) {
		return getContainingCell(e.getLocation());
	}
	
	public static Point getContainingCell(Point2D.Double loc) {
		return new Point((int) loc.x / CELL_SIZE, (int) loc.y / CELL_SIZE);
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
		entity.setGrid(this);
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
		
		for (Path p : activePaths) p.updatePath();
	}
	
	public final void updateStructures() {
		for (boolean[] r : structureGrid) Arrays.fill(r, false);
		
		for (Structure s : structures) {
			final Point gridLoc = s.getGridLocation(); 
			structureGrid[gridLoc.x][gridLoc.y] = true;
		}
	}
	
	public Path getPath(Entity e, Entity target) {
		return getPath(getContainingCell(e), getContainingCell(target));
	}
	
	public Path getPath(Point from, Point to) {
		final Path path = new Path(getPathList(from, to));
		activePaths.add(path);
		return path;
	}
	
	private LinkedList<Point> getPathList(Point from, Point to) {
		final double[][] cost = new double[COLS][ROWS];
		final double[][] estimate = new double[COLS][ROWS];
		for (double[] r : cost) Arrays.fill(r, Integer.MAX_VALUE);
		cost[from.x][from.y] = 0;
		estimate[from.x][from.y] = from.distance(to);
		
		final Queue<Point> queue = new PriorityQueue<Point>(10, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return (int) (estimate[o1.x][o1.y] - estimate[o2.x][o2.y]); 
			}
		});
		final Map<Point, Point> visited = new HashMap<Point, Point>();
		
		queue.add(from);
		visited.put(from, null);
		
		while (!queue.isEmpty()) {
			final Point p = queue.remove();
			
			if (p.equals(to)) break;
			
			for (int x = Math.max(0, p.x - 1), xMax = Math.min(p.x + 2, COLS); x < xMax; x++) {
				for (int y = Math.max(0, p.y - 1), yMax = Math.min(p.y + 2, ROWS); y < yMax; y++) {
					if (x == p.x && y == p.y
							|| structureGrid[x][y] && (x != to.x || y != to.y)) continue;
					
					final Point next = new Point(x, y);
					final double tmpCost = cost[p.x][p.y] + p.distance(next);
					
					if (tmpCost < cost[x][y]) {
						visited.put(next, p);
						cost[x][y] = tmpCost;
						estimate[x][y] = tmpCost + next.distance(to);
						queue.add(next);
					}
				}
			}
		}
		
		final LinkedList<Point> path = new LinkedList<Point>();
		
		Point p = to;
		while (visited.containsKey(p)) {
			path.add(p);
			p = visited.get(p);
		}
		Collections.reverse(path);
		
		return path;
	}
	
	public class Path {
		private LinkedList<Point> path;
		private Point current;
		
		public Path(LinkedList<Point> path) {
			this.path = path;
			current = path.getFirst();
		}
		
		protected void updatePath() {
			if (!path.isEmpty()) path = getPathList(current, path.getLast());
		}
		
		public Point getNext() {
			current = path.remove();
			
			if (path.isEmpty()) {
				activePaths.remove(this);
				return null;
			}
			
			return path.remove();
		}
	}

}
