import java.awt.Color;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainPanel extends ViewPanel {
	
	protected GamePanel panel;

	protected MainPanel(JFrame frame, int width, int height) {
		super(frame, width, height);
		setBackground(Color.WHITE);
		panel =	new GamePanel(this, GamePanel.WIDTH, GamePanel.HEIGHT);
		panel.getKeyListeners();
		add(panel);
	}

	@Override
	protected void start() {
		panel.start();
	}

	@Override
	protected void stop() {
		panel.stop();
	}
	
	

}
