import javax.swing.JFrame;

public class Barrage {

	public static int WIDTH = 800, HEIGHT = 600;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Barrage");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		new GamePanel(frame, WIDTH, HEIGHT).init();
	}

}
