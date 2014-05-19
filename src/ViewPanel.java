import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A JPanel that can be swapped and automatically focused within its JFrame
 * 
 * @author Simon Deng
 */
public abstract class ViewPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	protected JFrame frame;
	
	protected ViewPanel(JFrame frame, int width, int height) {
		this.frame = frame;
		setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * Adds panel to the frame and initializes it
	 */
	public void init() {
		frame.setContentPane(this);
		requestFocusInWindow();
		frame.invalidate();
		frame.validate();
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
