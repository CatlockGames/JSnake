/**
 * 
 */
package snake;

import java.awt.Graphics2D;

/**
 * @author Aaron
 *
 */
public class Grid {
	//Dimensions
	public static final int WIDTH = 40;
	public static final int HEIGHT = 32;
	
	private Snake snake = new Snake();
	
	private Food food = new Food();

	/**
	 * 
	 */
	public Grid() {
	}
	
	/**
	 * 
	 */
	public void init(){
		//initialize snake
		snake.init();
	}
	
	/**
	 * 
	 */
	public void update(){
		//update snake
		snake.update();
		//check for food collision
		snake.munch(food);
	}
	
	/**
	 * This method renders to the double buffered image
	 * @param g2d
	 */
	public void render(Graphics2D g2d){
		//render the snake
		snake.render(g2d);
		//render the food
		food.render(g2d);
	}
	
	/**
	 * This method returns the snake instance within the grid
	 * @return
	 */
	public Snake getSnake(){
		return snake;
	}

}
