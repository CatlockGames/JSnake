/**
 * 
 */
package snake;

import java.awt.image.BufferedImage;

/**
 * @author Ryan
 *
 */
public class Animation {
	private int frameDelay;
	private int currentFrame;
	private long timeSinceLastFrame;
	private boolean running;
	private boolean done;
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
		this.done = false;
		this.loop = false;
		this.frames = frames;
	}
	
	/**
	 * 
	 */
	public void start(){
		timeSinceLastFrame = System.currentTimeMillis();
		running = true;
	}
	
	/**
	 * 
	 */
	public void stop(){
		running = false;
	}
	
	/**
	 * 
	 */
	public void restart(){
		currentFrame = 0;
		done = false;
		start();
	}
	
	/**
	 * 
	 */
	public void reset(){
		currentFrame = 0;
		done = false;
		loop = false;
		stop();
	}
	
	/**
	 * 
	 */
	public void loop(){
		loop = true;
		restart();
	}
	
	/**
	 * 
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
					done = true;
					stop();
				}
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public BufferedImage getSprite(){
		return frames[currentFrame];
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean done(){
		return done;
	}
}
