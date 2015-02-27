/**
 * 
 */
package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	public static double SCALE;
	public static int WIDTH = 640;
	public static int HEIGHT = 512;
		
	//Properties
	public static final String TITLE = "Snake";
	public static final double VERSION = 1.0;
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
		/*
		if(Math.abs(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 16.0f / 9.0f) / (16.0f / 9.0f) < 0.0005f){
			System.out.println("16 / 9");
			SCALE = 1.0f;
		} else if(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / Toolkit.getDefaultToolkit().getScreenSize().getHeight() - (16.0f / 9.0f) / (16.0f / 9.0f) > 0.0005f){
			System.out.println("Above");
		} else if(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / Toolkit.getDefaultToolkit().getScreenSize().getHeight() - (16.0f / 9.0f) / (16.0f / 9.0f) < -0.0005f){
			System.out.println("Below");
		}
		*/
		SCALE = 1;
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
		if(e.getKeyCode() == KeyEvent.VK_BACK_QUOTE){
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
