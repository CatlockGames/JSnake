/**
 * 
 */
package snake;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * @author Aaron
 *
 */
public class Snake {
	//Segments
	private ArrayList<Segment> snake = new ArrayList<Segment>();
	
	//Timing
	private long timeSinceLastMove = System.currentTimeMillis();
	private long timeInterval = 75;
	
	private boolean headDirectionRequested = false;
	private char requestedHeadDirection;
	

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
		snake.add(new Segment(20, 16, 'u'));
		addSegment(2);
	}
	
	/**
	 * 
	 */
	public void update(){
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
				case 'r': snake.get(i).moveX(1); break;
				case 'l': snake.get(i).moveX(-1); break;
				case 'u': snake.get(i).moveY(-1); break;
				case 'd': snake.get(i).moveY(1); break;
				}
			}
			//Checks collision witb wall
			if(snake.get(0).getX() < 0 || snake.get(0).getX() >= Grid.WIDTH || snake.get(0).getY() < 0 || snake.get(0).getY() >= Grid.HEIGHT){
				System.exit(0);
			}
			//Checks collision with segments
			if(snake.size() > 4){
				for(int i = 4; i < snake.size(); i++){
					if(snake.get(0).getX() == snake.get(i).getX() && snake.get(0).getY() == snake.get(i).getY()){
						System.exit(0);
					}
				}
			}
			//Resets the time since last move
			timeSinceLastMove = System.currentTimeMillis();
		}
	}
	
	/**
	 * 
	 * @param g2d
	 */
	public void render(Graphics2D g2d){
		for(int i = 0; i < snake.size(); i++){
			snake.get(i).render(g2d);
		}
	}
	
	/**
	 * 
	 */
	private void addSegment(int n){
		for(int i = 0; i < n; i++){
			switch(snake.get(snake.size() - 1).getDirection()){
			case 'r': snake.add(new Segment(snake.get(snake.size() - 1).getX() - 1, snake.get(snake.size() - 1).getY(), snake.get(snake.size() - 1).getDirection())); break;
			case 'l': snake.add(new Segment(snake.get(snake.size() - 1).getX() + 1, snake.get(snake.size() - 1).getY(), snake.get(snake.size() - 1).getDirection())); break;
			case 'u': snake.add(new Segment(snake.get(snake.size() - 1).getX(), snake.get(snake.size() - 1).getY() + 1, snake.get(snake.size() - 1).getDirection())); break;
			case 'd': snake.add(new Segment(snake.get(snake.size() - 1).getX(), snake.get(snake.size() - 1).getY() - 1, snake.get(snake.size() - 1).getDirection())); break;
			}
		}
	}
	
	/**
	 * 
	 * @param direction
	 */
	public void setHeadDirectionRequest(char direction){
		headDirectionRequested = true;
		switch(snake.get(0).getDirection()){
		case 'r': if(direction == 'l'){requestedHeadDirection = 'r';} else{requestedHeadDirection = direction;} break;
		case 'l': if(direction == 'r'){requestedHeadDirection = 'l';} else{requestedHeadDirection = direction;} break;
		case 'u': if(direction == 'd'){requestedHeadDirection = 'u';} else{requestedHeadDirection = direction;} break;
		case 'd': if(direction == 'u'){requestedHeadDirection = 'd';} else{requestedHeadDirection = direction;} break;
		}
	}
	
	/**
	 * 
	 * @param food
	 */
	public void munch(Food food){
		if(food.getX() == snake.get(0).getX() && food.getY() == snake.get(0).getY()){
			addSegment(3);
			food.regen();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Segment> getSnake() {
		return snake;
	}

}
