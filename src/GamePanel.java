import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

/**
 * The main game view of Barrage
 * 
 * @author Simon Deng
 * @author Nikhil Ghosh
 */
@SuppressWarnings("serial")
public class GamePanel extends ViewPanel implements Runnable {

	private static final int FPS = 30;
	
	private boolean running;
	private Grid grid;
	private Player player;
	
	
	/**
	 * Creates a new GamePanel with the specified width and height
	 * 
	 * @param width the height of the GamePanel
	 * @param height the width of the GamePanel
	 * @param frame the parent JFrame
	 */
	public GamePanel(JFrame frame, int width, int height) {
		super(frame, width, height);
		setBackground(Color.WHITE);
	}
	
	@Override
	protected void start() {
		player = new Player(Barrage.WIDTH / 2, Barrage.HEIGHT / 2);
		final Nexus nexus = new Nexus(Grid.COLS / 2, Grid.ROWS / 2);
		grid = new Grid(player, nexus);
		player.setGrid(grid);
		grid.add(new Enemy(100, 100, 10, grid));
		
		final GameListener listener = new GameListener();
		addKeyListener(listener);
		addMouseListener(listener);
		addMouseMotionListener(listener);
		
		new Thread(this).start();
	}
	
	private void update() {
		for (Entity e : grid.getEntities()) {
			e.act();
			for (Projectile p : e.getProjectiles()) p.act(); 
		}
		
		grid.updateEntities();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;
		
		if (running) {
			for (Entity e : grid.getEntities()) {
				e.draw(g2d);
				for (Projectile p : e.getProjectiles()) p.draw(g2d);
			}
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
	
	@Override
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
			player.castSpell(player.getTeleport());
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
