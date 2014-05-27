import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;


public class SpellPanel extends JPanel implements ActionListener {
	
	protected JButton b1, b2, b3;
	private Player player;
	
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
		
		b1.setBorderPainted(false);
		b2.setBorderPainted(false);
		b3.setBorderPainted(false);
		
		add(b1);
		add(b2);
		add(b3);
		
		setPreferredSize(new Dimension(GamePanel.WIDTH, 100));
		setBackground(Color.BLACK);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if("Fireblast".equals(e.getActionCommand())) {
			b1.setBorderPainted(true);
			b2.setBorderPainted(false);
			b2.setBorderPainted(false);
			player.setSpell(player.getSpells()[0]);
		}
		else if("Iceblast".equals(e.getActionCommand())) {
			b1.setBorderPainted(false);
			b2.setBorderPainted(true);
			b2.setBorderPainted(false);
			player.setSpell(player.getSpells()[1]);		
		}
		else {
			b1.setBorderPainted(false);
			b2.setBorderPainted(false);
			b3.setBorderPainted(true);
			player.setSpell(player.getSpells()[2]);
		}
	}


}
