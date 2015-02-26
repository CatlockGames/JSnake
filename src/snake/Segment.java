/**
 * 
 */
package snake;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * @author Aaron
 *
 */
public class Segment {
	private int x;
	private int y;
	
	private char direction;

	/**
	 * 
	 */
	public Segment(int x, int y, char direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	/**
	 * 
	 * @param g2d
	 */
	public void render(Graphics2D g2d){
		g2d.setColor(Color.GREEN);
		g2d.fillRect(x * (GamePanel.WIDTH / Grid.WIDTH), y * (GamePanel.HEIGHT / Grid.HEIGHT), GamePanel.WIDTH / Grid.WIDTH, GamePanel.HEIGHT / Grid.HEIGHT);
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
	public char getDirection(){
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
	public void setDirection(char direction){
		this.direction = direction;
	}
	
	@Override
	public String toString(){
		return "(" + x + "," + y + "," + direction + ")";
	}

}
