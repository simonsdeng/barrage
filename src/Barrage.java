import java.awt.Point;

import javax.swing.JFrame;

public class Barrage {

	public static int WIDTH = 890, HEIGHT = 650;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Barrage");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocation(new Point(200, 200));

		new MainPanel(frame,WIDTH,HEIGHT).init();
	}

}
