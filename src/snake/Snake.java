/**
 * 
 */
package snake;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * @author Aaron
 * @author Ryan
 *
 */
public class Snake {
	//Segments
	private ArrayList<Segment> snake = new ArrayList<Segment>();
	
	private ArrayList<Poop> poops = new ArrayList<Poop>();
	private int chanceToPoop = 0;
	private Random random = new Random();
	
	//Timing
	private long timeSinceLastMove = System.currentTimeMillis();
	private long timeInterval = 50;
	
	//Sounds
	private Sound eat = new Sound("/sounds/eat.wav");
	private Sound death = new Sound("/sounds/death.wav");
	
	private boolean headDirectionRequested = false;
	private int requestedHeadDirection;
	
	//Sprites
	private Sprite sprites = new Sprite("/images/snake.png", 16, 16);
	private BufferedImage[] heads = {
			sprites.getSprite(0, 0),
			sprites.getSprite(1, 0),
			sprites.getSprite(2, 0),
			sprites.getSprite(3, 0)
	};
	private BufferedImage[][] bodies = {
			{sprites.getSprite(0, 1), sprites.getSprite(0, 2), sprites.getSprite(0, 3), sprites.getSprite(0, 4)},
			{sprites.getSprite(1, 1), sprites.getSprite(1, 2), sprites.getSprite(1, 3), sprites.getSprite(1, 4)},
			{sprites.getSprite(2, 1), sprites.getSprite(2, 2), sprites.getSprite(2, 3), sprites.getSprite(2, 4)},
			{sprites.getSprite(3, 1), sprites.getSprite(3, 2), sprites.getSprite(3, 3), sprites.getSprite(3, 4)}
	};
	private BufferedImage[][] thickTails = {
			{sprites.getSprite(0, 5), sprites.getSprite(0, 6), sprites.getSprite(0, 7), sprites.getSprite(0, 8)},
			{sprites.getSprite(1, 5), sprites.getSprite(1, 6), sprites.getSprite(1, 7), sprites.getSprite(1, 8)},
			{sprites.getSprite(2, 5), sprites.getSprite(2, 6), sprites.getSprite(2, 7), sprites.getSprite(2, 8)},
			{sprites.getSprite(3, 5), sprites.getSprite(3, 6), sprites.getSprite(3, 7), sprites.getSprite(3, 8)}
	};
	private BufferedImage[][] thinTails = {
			{sprites.getSprite(0, 9), sprites.getSprite(0, 10), sprites.getSprite(0, 11), sprites.getSprite(0, 12)},
			{sprites.getSprite(1, 9), sprites.getSprite(1, 10), sprites.getSprite(1, 11), sprites.getSprite(1, 12)},
			{sprites.getSprite(2, 9), sprites.getSprite(2, 10), sprites.getSprite(2, 11), sprites.getSprite(2, 12)},
			{sprites.getSprite(3, 9), sprites.getSprite(3, 10), sprites.getSprite(3, 11), sprites.getSprite(3, 12)}
	};

	/**
	 * 
	 */
	public Snake() {
	}
	
	/**
	 * 
	 */
	public void init(){
		//Adds the first segment (head)
		snake.add(new Segment(20, 16, 0));
		addSegment(2);
	}
	
	/**
	 * 
	 */
	public void update(){
		//Check poop collision
		for(int i = 0; i < poops.size(); i++){
			if(snake.get(0).getX() == poops.get(i).getX() && snake.get(0).getY() == poops.get(i).getY()){
				gameOver();
			}
		}
		//Update poop
		for(int i = 0; i < poops.size(); i++){
			poops.get(i).update();
			if(poops.get(i).done()){
				poops.remove(i);
			}
		}
		//Snake moves at certain time intervals
		if(System.currentTimeMillis() - timeSinceLastMove > timeInterval){
			for(int i = snake.size() - 1; i > 0; i--){ //Sets the directions
				snake.get(i).setDirection(snake.get(i - 1).getDirection());
			}
			//Set the head direction if requested
			if(headDirectionRequested){
				snake.get(0).setDirection(requestedHeadDirection);
				headDirectionRequested = false;
			}
			//Moves segments
			for(int i = 0; i < snake.size(); i++){
				switch(snake.get(i).getDirection()){
				case 0: snake.get(i).moveY(-1); break; //Up
				case 1: snake.get(i).moveX(1); break; //Right
				case 2: snake.get(i).moveY(1); break; //Down
				case 3: snake.get(i).moveX(-1); break; //Left
				}
			}
			//Checks collision witb wall
			if(snake.get(0).getX() < 0 || snake.get(0).getX() >= Grid.WIDTH || snake.get(0).getY() < 0 || snake.get(0).getY() >= Grid.HEIGHT){
				gameOver();
			}
			//Checks collision with segments
			if(snake.size() > 4){
				for(int i = 4; i < snake.size(); i++){
					if(snake.get(0).getX() == snake.get(i).getX() && snake.get(0).getY() == snake.get(i).getY()){
						gameOver();
					}
				}
			}
			//Resets the time since last move
			timeSinceLastMove = System.currentTimeMillis();
		}
	}
	
	/**
	 * This method renders to the double buffered image
	 * @param g2d
	 */
	public void render(Graphics2D g2d){
		//Render poop
		for(int i = 0; i < poops.size(); i++){
			poops.get(i).render(g2d);
		}
		//Render the head
		snake.get(0).render(g2d, heads[snake.get(0).getDirection()]);
		//Render the body
		for(int i = 1; i < snake.size() - 2; i++){
			snake.get(i).render(g2d, bodies[snake.get(i).getDirection()][snake.get(i - 1).getDirection()]);
		}
		//Render the thick tail
		snake.get(snake.size() - 2).render(g2d, thickTails[snake.get(snake.size() - 2).getDirection()][snake.get(snake.size() - 3).getDirection()]);
		//Render the thin tail
		snake.get(snake.size() - 1).render(g2d, thinTails[snake.get(snake.size() - 1).getDirection()][snake.get(snake.size() - 2).getDirection()]);
	}
	
	/**
	 * This method adds a new segment to the end of the snake
	 */
	private void addSegment(int n){
		for(int i = 0; i < n; i++){
			switch(snake.get(snake.size() - 1).getDirection()){
			case 0: snake.add(new Segment(snake.get(snake.size() - 1).getX(), snake.get(snake.size() - 1).getY() + 1, snake.get(snake.size() - 1).getDirection())); break;
			case 1: snake.add(new Segment(snake.get(snake.size() - 1).getX() - 1, snake.get(snake.size() - 1).getY(), snake.get(snake.size() - 1).getDirection())); break;
			case 2: snake.add(new Segment(snake.get(snake.size() - 1).getX(), snake.get(snake.size() - 1).getY() - 1, snake.get(snake.size() - 1).getDirection())); break;
			case 3: snake.add(new Segment(snake.get(snake.size() - 1).getX() + 1, snake.get(snake.size() - 1).getY(), snake.get(snake.size() - 1).getDirection())); break;
			}
		}
	}
	
	/**
	 * This method sets the requested head direction of the snake
	 * @param direction
	 */
	public void setHeadDirectionRequest(int direction){
		headDirectionRequested = true;
		switch(snake.get(0).getDirection()){
		case 0: if(direction == 2){requestedHeadDirection = 0;} else{requestedHeadDirection = direction;} break;
		case 1: if(direction == 3){requestedHeadDirection = 1;} else{requestedHeadDirection = direction;} break;
		case 2: if(direction == 0){requestedHeadDirection = 2;} else{requestedHeadDirection = direction;} break;
		case 3: if(direction == 1){requestedHeadDirection = 3;} else{requestedHeadDirection = direction;} break;
		}
	}
	
	
	public boolean munch(Food food){
		boolean canEat = false;
		if(food.getX() == snake.get(0).getX() && food.getY() == snake.get(0).getY()){
			eat.play();
			addSegment(3);
			
			if(random.nextInt(99) + 1 <= chanceToPoop){
				poops.add(new Poop(snake.get(snake.size() - 1).getX(), snake.get(snake.size() - 1).getY()));
				chanceToPoop = 0;
			}else{
				chanceToPoop += 50;
			}
			
			canEat = true;
		}
		
		return canEat;
	}
	
	/**
	 * This method gets the current segments of the snake
	 * @return
	 */
	public ArrayList<Segment> getSegments() {
		return snake;
	}
	
	/**
	 * This method gets the current poops of the snake
	 * @return
	 */
	public ArrayList<Poop> getPoops(){
		return poops;
	}
	
	/**
	 * 
	 */
	public void gameOver() {
		death.play();
		JOptionPane.showMessageDialog(null, "Your Score: " + Grid.SCORE);
		System.exit(0);
	}

}
