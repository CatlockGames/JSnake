/**
 * 
 */
package snake;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * @author Aaron
 * @author Ryan
 * @author Dylan
 *
 */
public class GameStateManager {
	public static final int MENUSTATE = 0;
	public static final int LEVELSTATE = 1;
	public static final int ENDSTATE = 2;
	
	private ArrayList<GameState> gameStates = new ArrayList<GameState>();
	private int currentGameState;
	
	/**
	 * 
	 */
	public GameStateManager() {
		gameStates.add(new MenuState(this));
		gameStates.add(new LevelState(this));
		gameStates.add(new EndState(this));
		this.setGameState(MENUSTATE);
	}
	
	/**
	 * 
	 */
	public void update(){
		gameStates.get(currentGameState).update();
	}
	
	/**
	 * 
	 * @param g2d
	 */
	public void render(Graphics2D g2d){
		gameStates.get(currentGameState).render(g2d);
	}
	
	/**
	 * 
	 * @param gameState
	 */
	public void setGameState(int gameState){
		currentGameState = gameState;
		gameStates.get(currentGameState).init();
	}
	
	/**
	 * 
	 * @param e
	 */
	public void keyPressed(KeyEvent e){
		gameStates.get(currentGameState).keyPressed(e);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void keyReleased(KeyEvent e){
		gameStates.get(currentGameState).keyReleased(e);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void keyTyped(KeyEvent e){
		gameStates.get(currentGameState).keyTyped(e);
	}

}
