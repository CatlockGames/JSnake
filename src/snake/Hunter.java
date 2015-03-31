/**
 * 
 */
package snake;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Aaron
 * @author Ryan
 * @author Dylan
 *
 */
public class Hunter {
	private int x;
	private int y;
	private int targetX;
	private int targetY;
	
	private Random random = new Random();
	
	private ArrayList<Trap> traps = new ArrayList<Trap>();
	
	//Timing
	private long timeSinceLastMove;
	private long timeInterval = 20;
	
	//Sprites
	private Sprite sprites = new Sprite("/images/hunter.png", 16, 48);
	private BufferedImage[] walkingN = {
			sprites.getSprite(3, 0),
			sprites.getSprite(4, 0),
			sprites.getSprite(3, 0),
			sprites.getSprite(5, 0)
	};
	private BufferedImage[] walkingE = {
			sprites.getSprite(3, 1),
			sprites.getSprite(4, 1),
			sprites.getSprite(3, 1),
			sprites.getSprite(5, 1)
	};
	private BufferedImage[] walkingS = {
			sprites.getSprite(0, 0),
			sprites.getSprite(1, 0),
			sprites.getSprite(0, 0),
			sprites.getSprite(2, 0)
	};
	private BufferedImage[] walkingW = {
			sprites.getSprite(0, 1),
			sprites.getSprite(1, 1),
			sprites.getSprite(0, 1),
			sprites.getSprite(2, 1),
	};

	//Animations
	private Animation walkN = new Animation(walkingN, 250);
	private Animation walkE = new Animation(walkingE, 250);
	private Animation walkS = new Animation(walkingS, 250);
	private Animation walkW = new Animation(walkingW, 250);
	private Animation animation = walkN;
	
	/**
	 * 
	 */
	public Hunter() {
	}
	
	public void init(){
		x = -(int) (animation.getSprite().getWidth() * GamePanel.SCALE * 4);
		y = GamePanel.HEIGHT / 2 - (int) (animation.getSprite().getHeight() * GamePanel.SCALE);
		timeSinceLastMove = System.currentTimeMillis();
		walkN.loop();
		walkE.loop();
		walkS.loop();
		walkW.loop();
		
		traps.clear();
		targetX = random.nextInt(LevelState.WIDTH - 1) + 1;
		targetY = random.nextInt(LevelState.HEIGHT - 1) + 1;
	}
	
	/**
	 * 
	 */
	public void update() {
		//Update traps
		for (int i = 0; i < traps.size(); i++) {
			traps.get(i).update();
		}

		animation.update();

		//Hunter moves at certain time intervals
		if (System.currentTimeMillis() - timeSinceLastMove > timeInterval) {
			//AI Behavior
			if (x < targetX * (GamePanel.WIDTH / LevelState.WIDTH)) {
				x++;
				setAction(1);
			}else if (x > targetX * (GamePanel.WIDTH / LevelState.WIDTH)) {
				x--;
				setAction(3);
			}
			if (y < targetY * (GamePanel.HEIGHT / LevelState.HEIGHT)) {
				y++;
				setAction(2);
			}else if (y > targetY * (GamePanel.HEIGHT / LevelState.HEIGHT)) {
				y--;
				setAction(0);
			}
			if (x == targetX * (GamePanel.WIDTH / LevelState.WIDTH) && y == targetY * (GamePanel.HEIGHT / LevelState.HEIGHT)) {
				traps.add(new Trap(targetX, targetY));
				targetX = random.nextInt(LevelState.WIDTH - 1) + 1;
				targetY = random.nextInt(LevelState.HEIGHT - 1) + 1;
			}

			//Resets the time since last move
			timeSinceLastMove = System.currentTimeMillis();
		}
	}
	
	/**
	 * 
	 * @param g2d
	 */
	public void render(Graphics2D g2d) {
		//Render traps
		for (int i = 0; i < traps.size(); i++) {
			traps.get(i).render(g2d);
		}

		g2d.drawImage(animation.getSprite(), x - (animation.getSprite().getWidth() / 2), y - (animation.getSprite().getHeight() / 2), (int) (animation.getSprite().getWidth() * GamePanel.SCALE), (int) (animation.getSprite().getHeight() * GamePanel.SCALE), null);
	}
	
	/**
	 * Up = 0
	 * Right = 1
	 * Down = 2
	 * Left = 3
	 * Setting Trap = 4
	 * @param action
	 */
	private void setAction(int action) {
		switch(action) {
		case 0: action = 0; animation = walkN; break;
		case 1: action = 1; animation = walkE; break;
		case 2: action = 2; animation = walkS; break;
		case 3: action = 3; animation = walkW; break;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int getX() {
		return x / 16;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getY() {
		return y / 16;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Trap> getTraps() {
		return traps;
	}

}
