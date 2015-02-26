/**
 * 
 */
package snake;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * @author Aaron
 * @author Ryan
 *
 */
public class Grid {
	//Dimensions
	public static final int WIDTH = 40;
	public static final int HEIGHT = 32;
	
	private Snake snake = new Snake();
	private Food food = new Food();
	
	private int score;

	/**
	 * 
	 */
	public Grid() {
	}
	
	/**
	 * 
	 */
	public void init(){
		//Initialize snake
		snake.init();
	}
	
	/**
	 * 
	 */
	public void update(){
		//Update score
		score = snake.getSnake().size() - 3;
		//Update snake
		snake.update();
		//Check for food collision
		snake.munch(food);
	}
	
	/**
	 * This method renders to the double buffered image
	 * @param g2d
	 */
	public void render(Graphics2D g2d){
		//Render the background
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		//Render the snake
		snake.render(g2d);
		//Render the food
		food.render(g2d);
		
		g2d.setColor(Color.WHITE);
		g2d.drawString("Score: " + score, 580, 15);
	}
	
	/**
	 * This method returns the snake instance within the grid
	 * @return
	 */
	public Snake getSnake(){
		return snake;
	}

}
