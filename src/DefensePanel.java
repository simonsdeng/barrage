import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;


public class DefensePanel extends JPanel implements ActionListener{
	
	private List<Defense> defenses;
	private Player player;
	private Grid grid;
	
	protected JButton b1, b2, b3;

	public DefensePanel(Player player){
		grid = player.getGrid();
		ImageIcon archerTower = new ImageIcon("archertower.png");
		archerTower = new ImageIcon(archerTower.getImage().getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH ));
		ImageIcon quakeTower = new ImageIcon("quaketower.png");
		quakeTower = new ImageIcon(quakeTower.getImage().getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH ));
	
		
		b1 = new JButton(archerTower);
		b1.setActionCommand("ArcherTower");
		b1.setBorder(new EtchedBorder());
		b1.setBackground(Color.BLACK);
		
		b2 = new JButton(quakeTower);
		b2.setBackground(Color.BLACK);
		b2.setActionCommand("QuakeTower");
		b2.setBorder(new EtchedBorder());
		
	
		
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
		if("ArcherTower".equals(action)) {
			b1.setBorderPainted(true);
			b2.setBorderPainted(false);
		}
		else if("QuakeTower".equals(action)) {
			b1.setBorderPainted(false);
			b2.setBorderPainted(true);
	
		}
		
	}
}
