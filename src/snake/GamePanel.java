/**
 * 
 */
package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * @author Aaron
 * @author Ryan
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {
	//Dimensions
	private double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static int WIDTH = 640;
	public static int HEIGHT = 512;
	public static double SCALE;
		
	//Properties
	public static final String TITLE = "Snake";
	public static final String VERSION = "3.0.1";
	private boolean debug = false;
		
	//Image
	private BufferedImage image;
	private Graphics2D g2d;
	
	//Thread
	private Thread thread;
	private boolean running;
	private int TargetFPS = 60;	
	private int FPS;
	private long targetTime = 1000 / TargetFPS;
	
	private Grid grid = new Grid();

	/**
	 * 
	 */
	public GamePanel() {
		//Calculate scaling
		if(Math.abs(screenWidth / screenHeight - 16.0f / 9.0f) / (16.0f / 9.0f) < 0.0005f){
			SCALE = ((int) (screenWidth / 1360 / 0.25)) * 0.25;
		} else if(screenWidth / screenHeight - (16.0f / 9.0f) / (16.0f / 9.0f) > 0.0005f){
			SCALE = ((int) (screenHeight / 1360 / 0.25)) * 0.25;
		} else if(screenWidth / screenHeight - (16.0f / 9.0f) / (16.0f / 9.0f) < -0.0005f){
			SCALE = ((int) (screenWidth / 765 / 0.25)) * 0.25;
		}
		//Scales the width and height
		WIDTH *= SCALE;
		HEIGHT *= SCALE;
		//Sets JPanel size
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setFocusable(true);
		this.requestFocus();
	}
	
	/**
	 * Initializes double buffering and grid
	 */
	public void init(){
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g2d = (Graphics2D) image.getGraphics();
		running = true;
		
		//Initialize grid
		grid.init();
	}
	
	/**
	 * 
	 */
	public void update(){
		//Update the grid
		grid.update();
	}
	
	/**
	 * This method renders to the double buffered image
	 */
	public void render(){		
		//Render the grid
		grid.render(g2d);
		
		//Render the debug menu
		if(debug){
			g2d.setColor(Color.WHITE);
			g2d.drawString("Debug Menu", 5, 15);
			g2d.drawString("fps:" + FPS, 5, 35);
			g2d.drawString("Resolution:" + screenWidth + "x" + screenHeight, 5, 50);
			g2d.drawString("Window Size:" + WIDTH + "x" + HEIGHT + "," + SCALE, 5, 65);
			g2d.drawString("Head Direction:" + grid.getSnake().getSegments().get(0).getDirection(), 5, 80);
			g2d.drawString("Head Position:" + grid.getSnake().getSegments().get(0).getX() + "(" + grid.getSnake().getSegments().get(0).getX() * (WIDTH / Grid.WIDTH) + ")" + "," + grid.getSnake().getSegments().get(0).getY() + "(" + grid.getSnake().getSegments().get(0).getY() * (HEIGHT / Grid.HEIGHT) + ")", 5, 95);
		}
	}
	
	/**
	 * Method to render the double buffered frame
	 */
	public void renderToScreen(){
		Graphics g = this.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
	}

	@Override
	public void run() {
		long start;
		long elapsed;
		long delay;
		
		init();
		
		//Game loop
		while(running){
			start = System.nanoTime();
			
			update();
			render();
			renderToScreen();
			
			elapsed = System.nanoTime() - start;
			delay = targetTime - elapsed / 1000000;
			FPS = (int) (1000000000 / elapsed);
			
			if(delay < 0){
				delay = 0;
			}
			
			try{
				Thread.sleep(delay);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	//Initializes thread and listeners
	@Override
	public void addNotify(){
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			this.addKeyListener(this);
			//this.addMouseListener(this);
			//this.addMouseMotionListener(this);
			//this.addMouseWheelListener(this);
			thread.start();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//Debugging Menu
		if(e.getKeyCode() == KeyEvent.VK_F3){
			if(debug){
				debug = false;
			} else{
				debug = true;
			}
		}
		//Quit
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
			grid.getSnake().setHeadDirectionRequest(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
			grid.getSnake().setHeadDirectionRequest(1);
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
			grid.getSnake().setHeadDirectionRequest(2);
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
			grid.getSnake().setHeadDirectionRequest(3);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
