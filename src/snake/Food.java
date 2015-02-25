/**
 * 
 */
package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

/**
 * @author Aaron
 *
 */
public class Food {
	private int x;
	private int y;
	
	private Random random = new Random();

	/**
	 * 
	 */
	public Food() {
		x = random.nextInt(Grid.WIDTH);
		y = random.nextInt(Grid.HEIGHT);
	}
	
	/**
	 * 
	 * @param g2d
	 */
	public void render(Graphics2D g2d){
		g2d.setColor(Color.RED);
		g2d.fillOval(x * (GamePanel.WIDTH / Grid.WIDTH), y * (GamePanel.HEIGHT / Grid.HEIGHT), GamePanel.WIDTH / Grid.WIDTH, GamePanel.HEIGHT / Grid.HEIGHT);
	}
	
	/**
	 * This method regenerates a new random food.
	 */
	public void regen(){
		x = random.nextInt(Grid.WIDTH);
		y = random.nextInt(Grid.HEIGHT);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getY(){
		return y;
	}

}
