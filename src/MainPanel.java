import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainPanel extends ViewPanel {
	
	protected GamePanel gpanel;
	protected SpellPanel spanel;
	protected DefensePanel dpanel;

	protected MainPanel(JFrame frame, int width, int height) {
		super(frame, width, height);
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);

		final Player player = new Player(new Point2D.Double(Barrage.WIDTH / 2, Barrage.HEIGHT / 2));
	    gpanel = new GamePanel(this, GamePanel.WIDTH, GamePanel.HEIGHT, player);
		spanel = new SpellPanel(player);
		dpanel = new DefensePanel(player);
		
		add(gpanel, BorderLayout.CENTER);
		add(spanel, BorderLayout.SOUTH);
		add(dpanel, BorderLayout.EAST);
		
	}

	@Override
	protected void start() {
		gpanel.start();
	}

	@Override
	protected void stop() {
		gpanel.stop();
	}
	
	

}
