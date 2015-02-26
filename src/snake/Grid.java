/**
 * 
 */
package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	
	private int score;
	
	private BufferedImage background;

	/**
	 * 
	 */
	public Grid() {
		try {
			background = ImageIO.read(this.getClass().getResource("/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		g2d.drawImage(background, 0, 0, null);
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
