import javax.swing.JFrame;

public class Barrage {

	public static int WIDTH = 880, HEIGHT = 640;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Barrage");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

		new MainPanel(frame,WIDTH,HEIGHT).init();
	}

}
