/**
 * 
 */
package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

/**
 * @author Aaron
 * @author Ryan
 * @author Dylan
 *
 */
public class LevelState extends GameState {
	//Dimensions
	public static int WIDTH = 40;
	public static int HEIGHT = 32;
	
	private int score;
	
	private boolean debug;
	
	//Fonts
	private Font mainFont;
	private Font debugFont = new Font("Century Gothic", Font.PLAIN, (int) (11f * GamePanel.SCALE));

	private Snake snake = new Snake();
	private Food food = new Food();
	private Hunter hunter = new Hunter();

	private Random random = new Random();
	
	private boolean hunterSpawned;

	/**
	 * 
	 */
	public LevelState(GameStateManager gameStateManager) {
		this.setGameStateManager(gameStateManager);
		try {
			mainFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/fonts/Bitwise.ttf")).deriveFont(Font.PLAIN, (int) (16f * GamePanel.SCALE));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(mainFont);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
		debug = false;
		//Initialize snake
		snake.init();
		//Initialize hunter
		hunter.init();
		hunterSpawned = false;
		//Generate the first food
		food.regen(random.nextInt(WIDTH), random.nextInt(HEIGHT));
	}

	@Override
	public void update() {
		if(!snake.isAlive()){
			gameOver();
		}
		//Update score
		score = snake.getSegments().size() - 3;
		if(score == 21 && !hunterSpawned){
			hunter.init();
			hunterSpawned = true;
		}
		//Update snake
		snake.update();
		//Update the hunter
		if(hunterSpawned){
			hunter.update();
		}
		
		//Check for hunter collision
		snake.caught(hunter);
		//Check for trap collision
		snake.trapped(hunter.getTraps());
		
		//Check for food collision
		if(snake.munch(food)){
			//If collision detected regenerate a new food
			int newX;
			int newY;
			boolean onSegment;
			boolean onPoop;
			boolean onTrap;
			while(true){
				newX = random.nextInt(WIDTH);
				newY = random.nextInt(HEIGHT);
				onSegment = false;
				onPoop = false;
				onTrap = false;
				//Checks if the food is on a segment
				for(int i = 0; i < snake.getSegments().size(); i++){
					if(newX == snake.getSegments().get(i).getX() && newY == snake.getSegments().get(i).getY()){
						onSegment = true;
						break;
					}
				}
				//Redundant to check poop if on segment
				if(!onSegment){
					//Checks if the food is on a poop
					for(int i = 0; i < snake.getPoops().size(); i++){
						if(newX == snake.getPoops().get(i).getX() && newY == snake.getPoops().get(i).getY()){
							onPoop = true;
							break;
						}
					}
					
					//Redundant to check traps if on poop
					if(!onPoop){
						//Checks if the food is on a trap;
						for(int i = 0; i < hunter.getTraps().size(); i++){
							if(newX == hunter.getTraps().get(i).getX() && newY == hunter.getTraps().get(i).getY()){
								onTrap = true;
								break;
							}
						}
					}
				}
				//If the new x and y are not on a segment or poop then regenerate a new food
				if(!onSegment && !onPoop && !onTrap){
					food.regen(newX, newY);
					break;
				}
			}
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		//Render the background
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		//Render the snake
		snake.render(g2d);
		//Render the food
		food.render(g2d);
		//Render the hunter
		if(hunterSpawned){
			hunter.render(g2d);
		}

		//Render the score
		g2d.setColor(Color.WHITE);
		g2d.setFont(mainFont);
		g2d.drawString("Score: " + score, GamePanel.WIDTH / 2 - (int) mainFont.getStringBounds("Score: " + score, g2d.getFontRenderContext()).getWidth() / 2, mainFont.getSize());
		
		//Render the debug information
		if(debug){
			g2d.setColor(Color.WHITE);
			g2d.setFont(debugFont);
			g2d.drawString("Debug Menu", (int) (5 * GamePanel.SCALE), debugFont.getSize() * 1);
			g2d.drawString("fps:" + GamePanel.FPS, (int) (5 * GamePanel.SCALE), debugFont.getSize() * 2);
			g2d.drawString("Resolution:" + GamePanel.SCREENWIDTH + "x" + GamePanel.SCREENHEIGHT, (int) (5 * GamePanel.SCALE), debugFont.getSize() * 3);
			g2d.drawString("Window Size:" + GamePanel.WIDTH + "x" + GamePanel.HEIGHT, (int) (5 * GamePanel.SCALE), debugFont.getSize() * 4);
			g2d.drawString("Scale:" + GamePanel.SCALE, (int) (5 * GamePanel.SCALE), debugFont.getSize() * 5);
			g2d.drawString("Head Direction:" + snake.getSegments().get(0).getDirection(), (int) (5 * GamePanel.SCALE), debugFont.getSize() * 6);
			g2d.drawString("Head Position:" + snake.getSegments().get(0).getX() + "(" + snake.getSegments().get(0).getX() * (GamePanel.WIDTH / WIDTH) + ")" + "," + snake.getSegments().get(0).getY() + "(" + snake.getSegments().get(0).getY() * (GamePanel.HEIGHT / HEIGHT) + ")", (int) (5 * GamePanel.SCALE), debugFont.getSize() * 7);
		}
	}
	
	/**
	 * 
	 */
	private void gameOver(){
		this.getGameStateManager().setGameState(GameStateManager.ENDSTATE);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//Debugging Information
		if(e.getKeyCode() == KeyEvent.VK_F3){
			if(debug){
				debug = false;
			} else{
				debug = true;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
			snake.setHeadDirectionRequest(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
			snake.setHeadDirectionRequest(1);
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
			snake.setHeadDirectionRequest(2);
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
			snake.setHeadDirectionRequest(3);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
