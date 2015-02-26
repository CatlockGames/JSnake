/**
 * 
 */
package snake;

import java.awt.image.BufferedImage;

/**
 * @author Aaron
 * @author Ryan
 *
 */
public class Animation {
	private int frameDelay;
	private int currentFrame;
	private long timeSinceLastFrame;
	private boolean running;
	private boolean loop;
	private BufferedImage[] frames;
	
	/**
	 * 
	 */
	public Animation(BufferedImage[] frames, int frameDelay) {
		this.frameDelay = frameDelay;
		this.currentFrame = 0;
		this.timeSinceLastFrame = System.currentTimeMillis();
		this.running = false;
		this.loop = false;
		this.frames = frames;
	}
	
	/**
	 * This method starts the animation
	 */
	public void start(){
		timeSinceLastFrame = System.currentTimeMillis();
		running = true;
	}
	
	/**
	 * This method stops the animation
	 */
	public void stop(){
		running = false;
	}
	
	/**
	 * This method restarts the animation
	 */
	public void restart(){
		currentFrame = 0;
		start();
	}
	
	/**
	 * This method resets the animation
	 */
	public void reset(){
		currentFrame = 0;
		loop = false;
		stop();
	}
	
	/**
	 * This method loops the animation
	 */
	public void loop(){
		loop = true;
		restart();
	}
	
	/**
	 * This method updates the animation
	 */
	public void update(){
		if(running){
			if(System.currentTimeMillis() - timeSinceLastFrame > frameDelay){
				timeSinceLastFrame = System.currentTimeMillis();
				currentFrame++;
				
				if(loop){
					if(currentFrame > frames.length - 1){
						currentFrame = 0;
					}
				}else if(currentFrame > frames.length - 1){
					currentFrame = frames.length - 1;
					running = false;
					stop();
				}
			}
		}
	}
	
	/**
	 * This method gets the current frame of the animation
	 * @return
	 */
	public BufferedImage getSprite(){
		return frames[currentFrame];
	}
	
	/**
	 * This method gets if the animation is done
	 * @return
	 */
	public boolean done(){
		return !running;
	}
}
