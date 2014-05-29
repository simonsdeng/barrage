import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class SpellPanel extends JPanel implements ActionListener {
	
	protected JButton b1, b2, b3;
	private Player player;
	
	private JLabel costLabel;
	
	public SpellPanel(Player player) {
		this.player = player;
		ImageIcon fireblast = new ImageIcon("fireball.png");
		ImageIcon iceblast = new ImageIcon("icespell.png");
		ImageIcon teleport = new ImageIcon("teleport.png");
		
		b1 = new JButton(fireblast);
		b1.setActionCommand("Fireblast");
		b1.setPreferredSize(new Dimension(50, 50));
		b1.setBorder(new EtchedBorder());
		
		b2 = new JButton(iceblast);
		b2.setActionCommand("Iceblast");
		b2.setPreferredSize(new Dimension(50, 50));
		b2.setBorder(new EtchedBorder());
		
		b3 = new JButton(teleport);
		b3.setActionCommand("Teleport");
		b3.setPreferredSize(new Dimension(50, 50));
		b3.setBorder(new EtchedBorder());
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		b1.setBorderPainted(true);
		b2.setBorderPainted(false);
		b3.setBorderPainted(false);
		
		setFocusable(false);
		b1.setFocusable(false);
		b2.setFocusable(false);
		b3.setFocusable(false);
		
		add(b1);
		add(b2);
		add(b3);
		
		costLabel = new JLabel("Cost: Free");
		costLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		costLabel.setForeground(Color.WHITE);
		add(costLabel);
		
		setPreferredSize(new Dimension(GamePanel.WIDTH, 60));
		setBackground(Color.BLACK);
	}
	
	public void setSpell(String name) {
		if (name.equals("Fireblast")) {
			b1.setBorderPainted(true);
			b2.setBorderPainted(false);
			b3.setBorderPainted(false);
			player.setSpell(player.getSpells()[0]);	
			costLabel.setText("Cost: " + "Free");
		} else if (name.equals("Iceblast")) {
			b1.setBorderPainted(false);
			b2.setBorderPainted(true);
			b3.setBorderPainted(false);
			player.setSpell(player.getSpells()[1]);	
			costLabel.setText("Cost: " + player.getSpells()[1].getCost());
		} else {
			b1.setBorderPainted(false);
			b2.setBorderPainted(false);
			b3.setBorderPainted(true);
			player.setSpell(player.getSpells()[2]);
			costLabel.setText("Cost: " + player.getSpells()[2].getCost());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		setSpell(e.getActionCommand());
	}


}
