/**
 * 
 */
package snake;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author Aaron
 * @author Ryan
 * @author Dylan
 *
 */
public class Food {
	private int x;
	private int y;
	
	private Random random = new Random();
	
	private Sprite sprites = new Sprite("/images/fruit.png", 16, 16);
	private BufferedImage[] fruits = {
			sprites.getSprite(0, 0), //Pear
			sprites.getSprite(1, 0), //Strawberry
			sprites.getSprite(2, 0), //Orange
			sprites.getSprite(0, 1), //Cherry
			sprites.getSprite(1, 1), //Lemon
			sprites.getSprite(2, 1), //Water melon
			sprites.getSprite(0, 2), //Banana
			sprites.getSprite(1, 2), //Blueberry
			sprites.getSprite(2, 2) //Apple
	};
	
	private BufferedImage fruit;
	
	/**
	 * 
	 */
	public Food() {
	}
	
	/**
	 * This method renders to the double buffered image
	 * @param g2d
	 */
	public void render(Graphics2D g2d){
		g2d.drawImage(fruit, x * (GamePanel.WIDTH / Grid.WIDTH), y * (GamePanel.HEIGHT / Grid.HEIGHT), (int) (fruit.getWidth() * GamePanel.SCALE), (int) (fruit.getHeight() * GamePanel.SCALE), null);
	}
	
	/**
	 * This method regenerates a new random food
	 */
	public void regen(int x, int y){
		this.x = x;
		this.y = y;
		fruit = fruits[random.nextInt(fruits.length)];
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
