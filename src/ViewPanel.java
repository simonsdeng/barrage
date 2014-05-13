import java.awt.CardLayout;

import javax.swing.JPanel;

public abstract class ViewPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private CardLayout layout;
	
	public ViewPanel(CardLayout layout) {
		super(true);
		this.layout = layout;
	}
	
	public void switchTo(String name) {
		layout.show(getParent(), name);
	}

}
