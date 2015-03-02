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
public class Hunter {
	private int x = 1;
	private int y = 1;
	
	/*
	private Sprite sprites = new Sprite("/images/hunter.png", 16, 48);
	private BufferedImage[] hunters = {
			sprites.getSprite(0, 0),
			sprites.getSprite(1, 0),
			sprites.getSprite(0, 0),
			sprites.getSprite(2, 0),
			
			sprites.getSprite(0, 1),
			sprites.getSprite(1, 1),
			sprites.getSprite(0, 1),
			sprites.getSprite(2, 1),
			
			sprites.getSprite(3, 0),
			sprites.getSprite(4, 0),
			sprites.getSprite(3, 0),
			sprites.getSprite(5, 0),
			
			sprites.getSprite(3, 1),
			sprites.getSprite(4, 1),
			sprites.getSprite(3, 1),
			sprites.getSprite(5, 1)
	};
	private Animation hunter = new Animation (hunters, 250);
	*/
	
	/**
	 * 
	 */
	public Hunter() {
		//hunter.loop();
	}
	
	/**
	 * 
	 */
	public void update() {
		//hunter.update();
	}
	
	/**
	 * 
	 * @param g2d
	 */
	public void render(Graphics2D g2d) {
		//g2d.drawImage(hunter.getSprite(), x * (GamePanel.WIDTH / Grid.WIDTH), y * (GamePanel.HEIGHT / Grid.HEIGHT), (int) (hunter.getSprite().getWidth() * GamePanel.SCALE), (int) (hunter.getSprite().getHeight() * GamePanel.SCALE), null);
	}

}
