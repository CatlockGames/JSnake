/**
 * 
 */
package snake;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Aaron
 *
 */
public class EndState extends GameState {
	private BufferedImage background;

	/**
	 * 
	 */
	public EndState(GameStateManager gameStateManager) {
		this.setGameStateManager(gameStateManager);
		try {
			background = ImageIO.read(this.getClass().getResource("/images/endBackground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(background, 0, 0, (int) (background.getWidth() * GamePanel.SCALE), (int) (background.getHeight() * GamePanel.SCALE), null);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			this.getGameStateManager().setGameState(GameStateManager.MENUSTATE);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
