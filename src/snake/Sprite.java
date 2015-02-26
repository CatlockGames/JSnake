/**
 * 
 */
package snake;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Aaron
 * @author Ryan
 *
 */
public class Sprite {
	private BufferedImage sheet;
	private String fileName;
	private int width;
	private int height;
	
	/**
	 * 
	 */
	public Sprite(String fileName, int width, int height) {
		this.sheet = null;
		this.fileName = fileName;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * This method loads the sprite sheet
	 * @return
	 */
	private BufferedImage loadSpriteSheet(){
		try {
			sheet = ImageIO.read(this.getClass().getResource(fileName));
		} catch(IOException e){
			e.printStackTrace();
		}
		return sheet;
	}
	
	/**
	 * This method gets a sprite from the sprite sheet
	 * @param x
	 * @param y
	 * @return
	 */
	public BufferedImage getSprite(int x, int y){
		if(sheet == null){
			sheet = loadSpriteSheet();
		}
		return sheet.getSubimage(width * x, height * y, width, height);
	}

}
