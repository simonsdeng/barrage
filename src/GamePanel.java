import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * The main game view of Barrage
 * 
 * @author Simon Deng
 * @author Nikhil Ghosh
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {

	private static final int FPS = 30;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private boolean running;
	private Grid grid;
	private Player player;
	
	private MainPanel mainPanel;
	
	/**
	 * Creates a new GamePanel with the specified width and height
	 * 
	 * @param width the height of the GamePanel
	 * @param height the width of the GamePanel
	 * @param frame the parent JFrame
	 */
	public GamePanel(MainPanel mainPanel, int width, int height, Player player) {
		this.mainPanel = mainPanel;
		setPreferredSize(new Dimension(width, height));
		setBackground(new Color(238, 238, 238));
		this.player = player;
	}
	
//	public Player getPlayer() { return player; }
	
	protected void start() {
		final Nexus nexus = new Nexus(new Point(Grid.COLS / 2, Grid.ROWS / 2));
		grid = new Grid(player, nexus);
		
//		grid.add(new ArcherTower(Grid.COLS / 2, Grid.ROWS / 2 - 3, grid));
//		grid.add(new ArcherTower(Grid.COLS / 2, Grid.ROWS / 2 - 2, grid));
//		grid.add(new ArcherTower(Grid.COLS / 2, Grid.ROWS / 2 - 1, grid));
//		grid.add(new ArcherTower(Grid.COLS / 2, Grid.ROWS / 2 + 1, grid));
//		grid.add(new ArcherTower(Grid.COLS / 2, Grid.ROWS / 2 + 2, grid));
//		grid.add(new ArcherTower(Grid.COLS / 2, Grid.ROWS / 2 + 3, grid));
		
//		grid.add(new ArcherTower(new Point(5, 5)));
		
		final GameListener listener = new GameListener();
		mainPanel.addKeyListener(listener);
		addMouseListener(listener);
		addMouseMotionListener(listener);
		
		new Thread(this).start();
	}
	
	private void update() {
		if (Math.random() < .02) spawnEnemy();
		
		for (Entity e : grid.getEntities()) {
			e.act();
			for (Projectile p : e.getProjectiles()) p.act(); 
		}
		
		if (grid.getPlayer().getHealth() <= 0 || grid.getNexus().getHealth() <= 0) {
			System.out.println("You lose!");
			stop();
		}
		
		grid.updateEntities();
		mainPanel.updatePanels();
	}
	
	private void spawnEnemy() {
		switch ((int) (Math.random() * 4)) {
		case 1:
			grid.add(new Enemy(new Point2D.Double((int) (Math.random() * getWidth()), 0), 10));
			break;
		case 2:
			grid.add(new Enemy(new Point2D.Double((int) (Math.random() * getWidth()), getHeight()), 10));
			break;
		case 3:
			grid.add(new Enemy(new Point2D.Double(0, (int) (Math.random() * getHeight())), 10));
			break;
		case 4:
			grid.add(new Enemy(new Point2D.Double(getHeight(), (int) (Math.random() * getWidth())), 10));
			break;
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;
		
		if (grid != null) {
			g2d.setColor(Color.BLACK);
			ArrayList<Projectile> projList = new ArrayList<Projectile>();
			for (Entity e : grid.getEntities()) {
				for (Projectile p : e.getProjectiles()) projList.add(p);
				e.draw(g2d);
			}
			for (Projectile p: projList){
				p.draw(g2d);
			}
		}
	}
	
	public void placeDefense(Defense defense){
		Point loc = defense.getGridLocation();
		if(!grid.getEntityGrid()[loc.x][loc.y] && player.getGold() >= defense.getCost()) {
			grid.add(defense);
			player.setGold(player.getGold() - defense.getCost());
		}
	}
	
	@Override
	public void run() {
		final long targetTime = 1000 / FPS;
		running = true;
		
		while (running) {
			final long startTime = System.currentTimeMillis();
			
			update();
			repaint();
			
			final long elapsedTime = System.currentTimeMillis() - startTime;
			final long waitTime = targetTime - elapsedTime;
			
			if (waitTime > 0) {
				try {
					Thread.sleep(waitTime);
				} catch (InterruptedException e) {}
			}
		}
	}
	
	protected void stop() {
		running = false;
	}
	
	private class GameListener implements KeyListener, MouseListener, MouseMotionListener {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				player.setLeft(true);
				break;
			case KeyEvent.VK_D:
				player.setRight(true);
				break;
			case KeyEvent.VK_W:
				player.setUp(true);
				break;
			case KeyEvent.VK_S:
				player.setDown(true);
				break;
			case KeyEvent.VK_1:
				mainPanel.getSpellPanel().setSpell("Fireblast");
				break;
			case KeyEvent.VK_2:
				mainPanel.getSpellPanel().setSpell("Iceblast");
				break;
			case KeyEvent.VK_3:
				mainPanel.getSpellPanel().setSpell("Teleport");
				break;
			}
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				player.setLeft(false);
				break;
			case KeyEvent.VK_D:
				player.setRight(false);
				break;
			case KeyEvent.VK_W:
				player.setUp(false);
				break;
			case KeyEvent.VK_S:
				player.setDown(false);
				break;
			}
		}
		
		@Override
		public void keyTyped(KeyEvent e) {}
		
		@Override
		public void mouseClicked(MouseEvent e) {}
		
		@Override
		public void mousePressed(MouseEvent e) {
			player.setPointer(e.getPoint());
			if (!player.isPlacingDefense())
				player.castSpell(player.getSpell());
			else {
				int defense = player.getActiveDefense();
				Point2D.Double point = new Point2D.Double(player.getPointer().x, player.getPointer().y);
				if (defense == DefensePanel.ARCHER_TOWER){
					placeDefense(new ArcherTower(Grid.getContainingCell(point)));
				} else if (defense == DefensePanel.QUAKE_TOWER){
					placeDefense(new QuakeTower(Grid.getContainingCell(point)));
				}
				mainPanel.deselectDefenes();
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {}
		
		@Override
		public void mouseEntered(MouseEvent e) {}
		
		@Override
		public void mouseExited(MouseEvent e) {}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			player.setPointer(e.getPoint());
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			player.setPointer(e.getPoint());
		}
	}

}
