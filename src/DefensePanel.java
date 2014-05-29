import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;


public class DefensePanel extends JPanel implements ActionListener{
	
	private List<Defense> defenses;
	private Player player;
	private Grid grid;
	
	public static final int ARCHER_TOWER = 1;
	public static final int QUAKE_TOWER = 2;
	
	private JLabel[] labels;
	private JLabel costLabel;
	protected JButton b1, b2, b3;
	
	private String lastAction;

	public DefensePanel(Player player){
		this.player = player;
		this.grid = player.getGrid();
		ImageIcon archerTower = new ImageIcon("archertowericon.png");
		archerTower = new ImageIcon(archerTower.getImage().getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH ));
		ImageIcon quakeTower = new ImageIcon("quaketowericon.png");
		quakeTower = new ImageIcon(quakeTower.getImage().getScaledInstance(80,80,  java.awt.Image.SCALE_SMOOTH ));
		
		labels = new JLabel[4];

		labels[0] = new JLabel("Health: " + player.getLives());
		labels[0].setForeground(Color.RED);
		labels[1] = new JLabel("Mana: " + player.getMana());
		labels[1].setForeground(Color.CYAN);
		labels[2] = new JLabel("Gold: " + player.getGold());
		labels[2].setForeground(Color.YELLOW);
		labels[3] = new JLabel("Kills: " + player.getKills());
		labels[3].setForeground(Color.WHITE);
		
		for (JLabel label: labels){
			label.setFont(new Font("Serif", Font.PLAIN, 18));
			add(label);
		}
	
		
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
		
		costLabel = new JLabel();
		costLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		costLabel.setForeground(Color.WHITE);
		add(costLabel);
	}
	
	public void updateLabels(){
		
		labels[0].setText("Health: " + player.getLives());
		labels[1].setText("Mana: " + player.getMana());
		labels[2].setText("Gold: " + player.getGold());
		labels[3].setText("Kills: " + player.getKills());
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String action = e.getActionCommand();
		if (action.equals(lastAction)){
			b1.setBorderPainted(false);
			b2.setBorderPainted(false);
			player.setPlacingDefense(false);
			costLabel.setText("");
			lastAction = "reset";
		}
		else if("ArcherTower".equals(action)) {
			b1.setBorderPainted(true);
			b2.setBorderPainted(false);
			lastAction = action;
			player.setActiveDefense(ARCHER_TOWER);
			costLabel.setText("Cost: " + ArcherTower.COST);

			player.setPlacingDefense(true);
		}
		else if("QuakeTower".equals(action)) {
			b1.setBorderPainted(false);
			b2.setBorderPainted(true);
			lastAction = action;
			player.setActiveDefense(QUAKE_TOWER);
			costLabel.setText("Cost: " + QuakeTower.COST);
			player.setPlacingDefense(true);
		}

	}

	public void deselectDefenses() {
		b1.setBorderPainted(false);
		b2.setBorderPainted(false);
		player.setPlacingDefense(false);
		costLabel.setText("");
		lastAction = "reset";
		
	}
	

	

}
