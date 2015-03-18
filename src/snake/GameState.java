/**
 * 
 */
package snake;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * @author Aaron
 * @author Ryan
 * @author Dylan
 *
 */
public abstract class GameState {
	private GameStateManager gameStateManager;
	
	public GameStateManager getGameStateManager() {
		return gameStateManager;
	}

	public void setGameStateManager(GameStateManager gameStateManager) {
		this.gameStateManager = gameStateManager;
	}

	public abstract void init();
	
	public abstract void update();
	
	public abstract void render(Graphics2D g2d);
	
	public abstract void keyPressed(KeyEvent e);
	
	public abstract void keyReleased(KeyEvent e);
	
	public abstract void keyTyped(KeyEvent e);
}
