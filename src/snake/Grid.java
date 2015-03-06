/**
 * 
 */
package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

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
	private Hunter hunter = new Hunter();
	
	private Random random = new Random();
	
	public static int SCORE;

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
		food.regen(random.nextInt(WIDTH), random.nextInt(HEIGHT));
	}
	
	/**
	 * 
	 */
	public void update(){
		hunter.update();
		//Update score
		SCORE = snake.getSegments().size() - 3;
		//Update snake
		snake.update();
		//Check for food collision
		if(snake.munch(food)){
			//If collision detected regenerate a new food
			int newX;
			int newY;
			boolean onSegment;
			boolean onPoop;
			while(true){
				newX = random.nextInt(WIDTH);
				newY = random.nextInt(HEIGHT);
				onSegment = false;
				onPoop = false;
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
				}
				//If the new x and y are not on a segment or poop then regenerate a new food
				if(!onSegment && !onPoop){
					food.regen(newX, newY);
					break;
				}
			}
		}
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
		g2d.drawString("Score: " + SCORE, 580, 15);
		hunter.render(g2d);
	}
	
	/**
	 * This method returns the snake instance within the grid
	 * @return
	 */
	public Snake getSnake(){
		return snake;
	}

}
