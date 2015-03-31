/**
 * 
 */
package snake;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * @author Aaron
 *
 */
public class Trap {
	private int x;
	private int y;
	
	private Sprite sprites = new Sprite("/images/trap.png", 16, 16);
	private BufferedImage[] traps = {
			sprites.getSprite(0, 0),
			sprites.getSprite(0, 0),
			sprites.getSprite(1, 0),
			sprites.getSprite(2, 0)
	};
	private Animation trap = new Animation(traps, 300);

	/**
	 * 
	 */
	public Trap(int x, int y) {
		this.x = x;
		this.y = y;
		
		trap.start();
	}
	
	/**
	 * 
	 */
	public void update(){
		trap.update();
	}
	
	/**
	 * 
	 * @param g2d
	 */
	public void render(Graphics2D g2d){
		g2d.drawImage(trap.getSprite(), x * (GamePanel.WIDTH / LevelState.WIDTH), y * (GamePanel.HEIGHT / LevelState.HEIGHT), (int) (trap.getSprite().getWidth() * GamePanel.SCALE), (int) (trap.getSprite().getHeight() * GamePanel.SCALE), null);
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

}
