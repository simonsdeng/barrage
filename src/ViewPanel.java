import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class ViewPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	protected JFrame frame;
	
	protected ViewPanel(JFrame frame, int width, int height) {
		this.frame = frame;
		setPreferredSize(new Dimension(width, height));
	}
	
	public void init() {
		frame.setContentPane(this);
		requestFocusInWindow();
		frame.revalidate();
		frame.pack();
		start();
	}
	
	protected void switchTo(ViewPanel panel) {
		stop();
		panel.init();
	}
	
	protected abstract void start();
	protected abstract void stop();
	

}
