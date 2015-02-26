/**
 * 
 */
package snake;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * @author Ryan
 *
 */
public class Poop {
	private int x;
	private int y;
	
	private Sprite sprites = new Sprite("/images/poop.png", 16, 16);
	private BufferedImage[] poops = {
			sprites.getSprite(0, 0),
			sprites.getSprite(1, 0),
			sprites.getSprite(2, 0)
	};
	private BufferedImage[] flies = {
			sprites.getSprite(0, 1),
			sprites.getSprite(1, 1),
			sprites.getSprite(2, 1),
			sprites.getSprite(1, 1)
	};
	private Animation poop = new Animation(poops, 4000);
	private Animation fly = new Animation(flies, 25);
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Poop(int x, int y) {
		this.x = x;
		this.y = y;
		
		poop.start();
		fly.loop();
	}
	
	/**
	 * 
	 */
	public void update() {
		poop.update();
		fly.update();
	}
	
	/**
	 * 
	 * @param g2d
	 */
	public void render(Graphics2D g2d) {
		g2d.drawImage(poop.getSprite(), x * (GamePanel.WIDTH / Grid.WIDTH), y * (GamePanel.HEIGHT / Grid.HEIGHT), null);
		g2d.drawImage(fly.getSprite(), x * (GamePanel.WIDTH / Grid.WIDTH), y * (GamePanel.HEIGHT / Grid.HEIGHT), null);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean done() {
		return poop.done();
	}
	
}
