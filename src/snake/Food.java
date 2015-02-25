/**
 * 
 */
package snake;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author Aaron
 *
 */
public class Food {
	private int x;
	private int y;
	
	private Random random = new Random();
	
	private Sprite sprites = new Sprite("/fruit.png", 16, 16);
	private BufferedImage[] pearFrames = {sprites.getSprite(0, 0)};
	private BufferedImage[] strawberryFrames = {sprites.getSprite(1, 0)};
	private BufferedImage[] orangeFrames = {sprites.getSprite(2, 0)};
	private BufferedImage[] cherryFrames = {sprites.getSprite(0, 1)};
	private BufferedImage[] lemonFrames = {sprites.getSprite(1, 1)};
	private BufferedImage[] watermelonFrames = {sprites.getSprite(2, 1)};
	private BufferedImage[] bananaFrames = {sprites.getSprite(0, 2)};
	private BufferedImage[] blueberryFrames = {sprites.getSprite(1, 2)};
	private BufferedImage[] appleFrames = {sprites.getSprite(2, 2)};
	
	private Animation[] animations = {
			new Animation(pearFrames, 250),
			new Animation(strawberryFrames, 250),
			new Animation(orangeFrames, 250),
			new Animation(cherryFrames, 250),
			new Animation(lemonFrames, 250),
			new Animation(watermelonFrames, 250),
			new Animation(bananaFrames, 250),
			new Animation(blueberryFrames, 250),
			new Animation(appleFrames, 250)	
	};
	
	private Animation animation;
	
	/**
	 * 
	 */
	public Food() {
		regen();
	}
	
	/**
	 * 
	 * @param g2d
	 */
	public void render(Graphics2D g2d){
		g2d.drawImage(animation.getSprite(), x * (GamePanel.WIDTH / Grid.WIDTH), y * (GamePanel.HEIGHT / Grid.HEIGHT), null);
	}
	
	/**
	 * This method regenerates a new random food.
	 */
	public void regen(){
		x = random.nextInt(Grid.WIDTH);
		y = random.nextInt(Grid.HEIGHT);
		animation = animations[random.nextInt(animations.length - 1)];
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
