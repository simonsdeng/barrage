import javax.swing.JPanel;

import com.sun.tools.javac.util.List;


public class DefenseSelector extends JPanel {
	
	private List<Defense> defenses;

	public DefenseSelector(List<Defense> defenses){
		this.defenses = defenses;
	}
}
