import java.util.List;

import javax.swing.JPanel;


public class DefensePanel extends JPanel {
	
	private List<Defense> defenses;
	private Player player;
	private Grid grid;

	public DefensePanel(Grid grid){
		defenses = grid.getDefenses();
		player = grid.getPlayer();
		this.grid = grid;
	}
}
