import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;


public class DefensePanel extends JPanel implements ActionListener{
	
	private List<Defense> defenses;
	private Player player;
	private Grid grid;
	
	public static final int ARCHER_TOWER = 1;
	public static final int QUAKE_TOWER = 2;
	
	protected JButton b1, b2, b3;
	
	private String lastAction;

	public DefensePanel(Player player){
		this.player = player;
		this.grid = player.getGrid();
		ImageIcon archerTower = new ImageIcon("archertowericon.png");
		archerTower = new ImageIcon(archerTower.getImage().getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH ));
		ImageIcon quakeTower = new ImageIcon("quaketowericon.png");
		quakeTower = new ImageIcon(quakeTower.getImage().getScaledInstance(80,80,  java.awt.Image.SCALE_SMOOTH ));
	
		
		b1 = new JButton(archerTower);
		b1.setActionCommand("ArcherTower");
		b1.setBorder(new EtchedBorder());
		b1.setPreferredSize(new Dimension(80, 80));
		
		
		b2 = new JButton(quakeTower);
		b2.setActionCommand("QuakeTower");
		b2.setBorder(new EtchedBorder());
		b2.setPreferredSize(new Dimension(80, 80));
	
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		b1.setBorderPainted(false);
		b2.setBorderPainted(false);
		
		setFocusable(false);
		b1.setFocusable(false);
		b2.setFocusable(false);
		
		add(b1);
		add(b2);

		setPreferredSize(new Dimension(100, GamePanel.HEIGHT));
		setBackground(Color.BLACK);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String action = e.getActionCommand();
		if (action.equals(lastAction)){
			b1.setBorderPainted(false);
			b2.setBorderPainted(false);
			player.setPlacingDefense(false);
			lastAction = "reset";
		}
		else if("ArcherTower".equals(action)) {
			b1.setBorderPainted(true);
			b2.setBorderPainted(false);
			lastAction = action;
			player.setActiveDefense(ARCHER_TOWER);
			player.setPlacingDefense(true);
		}
		else if("QuakeTower".equals(action)) {
			b1.setBorderPainted(false);
			b2.setBorderPainted(true);
			lastAction = action;
			player.setActiveDefense(QUAKE_TOWER);
			player.setPlacingDefense(true);
		}

	}
	

	

}
