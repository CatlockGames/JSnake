/**
 * 
 */
package snake;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * @author Aaron
 * @author Ryan
 *
 */
public class Segment {
	private int x;
	private int y;
	
	/*
	 * Up = 0
	 * Right = 1
	 * Down = 2
	 * Left = 3
	 */
	private int direction;

	/**
	 * 
	 */
	public Segment(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	/**
	 * This method renders to the double buffered image
	 * @param g2d
	 */
	public void render(Graphics2D g2d, BufferedImage image){
		g2d.drawImage(image, x * (GamePanel.WIDTH / Grid.WIDTH), y * (GamePanel.HEIGHT / Grid.HEIGHT), (int) (image.getWidth() * GamePanel.SCALE), (int) (image.getHeight() * GamePanel.SCALE), null);
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
	
	/**
	 * 
	 * @return
	 */
	public int getDirection(){
		return direction;
	}
	
	/**
	 * 
	 * @param x
	 */
	public void moveX(int x){
		this.x += x;
	}
	
	/**
	 * 
	 * @param y
	 */
	public void moveY(int y){
		this.y += y;
	}
	
	/**
	 * 
	 * @param direction
	 */
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	@Override
	public String toString(){
		return "(" + x + "," + y + "," + direction + ")";
	}

}
