/**
 * 
 */
package snake;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * @author Aaron
 * @author Ryan
 * @author Dylan
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
	private Animation poop = new Animation(poops, 10000);
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
		g2d.drawImage(poop.getSprite(), x * (GamePanel.WIDTH / LevelState.WIDTH), y * (GamePanel.HEIGHT / LevelState.HEIGHT), (int) (poop.getSprite().getWidth() * GamePanel.SCALE), (int) (poop.getSprite().getHeight() * GamePanel.SCALE), null);
		g2d.drawImage(fly.getSprite(), x * (GamePanel.WIDTH / LevelState.WIDTH), y * (GamePanel.HEIGHT / LevelState.HEIGHT), (int) (fly.getSprite().getWidth() * GamePanel.SCALE), (int) (fly.getSprite().getHeight() * GamePanel.SCALE), null);
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
