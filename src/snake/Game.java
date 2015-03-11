/**
 * 
 */
package snake;

import javax.swing.JFrame;

/**
 * @author Aaron
 * @author Ryan
 * @author Dylan
 *
 */
public class Game {

	/**
	 * 
	 */
	public Game() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame(GamePanel.TITLE + " v" + GamePanel.VERSION);
		frame.setContentPane(new GamePanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
