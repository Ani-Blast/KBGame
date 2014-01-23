package kilobotgame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

public class GameController extends Applet implements Runnable, KeyListener {

	/*
	 * SECTION: Constants
	 */
	private static final long serialVersionUID = 1L;
	final static int androidWidth = 800;
	final static int androidLength = 480;
	
	/*
	 * SECTION: Variables
	 * 
	 * Image and Graphics for use in double buffering
	 */
	private Robot robot;
	private Heliboy hb1, hb2;
	private Image image, currentSprite, character, characterDown, 
		characterJumped, heliboy, background;
	private URL base;
	private Graphics second;
	private static Background bg1, bg2;
	
	/*
	 * SECTION: Applet/Runnable Framework Methods
	 * 
	 * See Robot.java for explanation behind Projectiles.
	 */
	@Override
	public void init() {
		setSize( androidWidth, androidLength ); //800p x 480p (Standard Android res)
		setBackground(Color.BLACK);
		setFocusable(true); //Sets the environment's controls into the applet
		addKeyListener(this);
		
		Frame frame = (Frame)this.getParent().getParent();
		frame.setTitle("Q-Bot Alpha");
		try {
			base = getDocumentBase();
		} catch( Exception e ) {
		}
		
		character = getImage( base, "data/character.png" ); //122x126
		characterDown = getImage( base, "data/down.png" ); //122x126
		characterJumped = getImage( base, "data/jumped.png" ); //122x126
		heliboy = getImage( base, "data/heliboy.png" ); //96 x 96
		background = getImage( base, "data/background.png" ); //2160x480
		currentSprite = character;
	}

	@Override
	public void start() {
		bg1 = new Background( 0, 0 );
		bg2 = new Background( Background.bgWidth, 0 );
		
		hb1 = new Heliboy(340, 360);
		hb2 = new Heliboy(700, 360);
		
		robot = new Robot();
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		while(true) {
			robot.update();
			if( robot.hasJumped() ) {
				currentSprite = characterJumped;
			} else if( !robot.hasDucked() && !robot.hasJumped() ) {
				currentSprite = character;
			}
			
			ArrayList<Projectile> projectiles = robot.getProjectiles();
			for( int i = 0; i < projectiles.size(); i++ ) {
				Projectile p = projectiles.get(i);
				if( p.isVisible() ) {
					p.update();
				} else {
					projectiles.remove(i);
				}
			}
			
			hb1.update();
			hb2.update();
			bg1.update();
			bg2.update();
			
			repaint(); //update Graphics [See AWT and Swing]
			
			try {
				Thread.sleep(17);
			} catch( InterruptedException e ) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void stop() {
	}

	@Override
	public void destroy() {
	}


	/*
	 * SECTION: Game Loop Methods
	 * 
	 * drawImage places the top left corner of the image at the (x,y) coordinates
	 * 	you provide.
	 */
	@Override
	public void paint(Graphics scene) {
		scene.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
		scene.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
		
		ArrayList<Projectile> projectiles = robot.getProjectiles();
		for( int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			scene.setColor(Color.YELLOW);
			scene.fillRect(p.getX(), p.getY(), 10, 5);
		}
		
		scene.drawImage(currentSprite, robot.getCenterX()-61, 
				robot.getCenterY()-63, this);
		
		scene.drawImage(heliboy, hb1.getCenterX()-48, hb1.getCenterY()-48, this);
		scene.drawImage(heliboy, hb2.getCenterX()-48, hb2.getCenterY()-48, this);
	}

	public static Background getBg1() {
		return bg1;
	}
	
	public static Background getBg2() {
		return bg2;
	}
	
	@Override
	public void update(Graphics scene) {
		if( image == null ) {
			image = createImage( getWidth(), getHeight() );
			second = image.getGraphics();
		}
		
		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);
		
		scene.drawImage(image, 0, 0, this);
	}

	/*
	 * SECTION: Control Handling
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				robot.moveLeft();
				robot.setMovingLeft(true);
				break;
			case KeyEvent.VK_RIGHT:
				robot.moveRight();
				robot.setMovingRight(true);
				break;
			case KeyEvent.VK_UP:
				// Look up?
				break;
			case KeyEvent.VK_DOWN:
				currentSprite = characterDown;
				if( !robot.hasJumped() ) {
					robot.setDucked(true);
					robot.setSpeedX(0);
				}
				break;
			case KeyEvent.VK_SPACE:
				robot.jump();
				break;
			case KeyEvent.VK_CONTROL:
				if( !robot.hasDucked() && !robot.hasJumped() ) {
					robot.shoot();
				}
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				robot.stopMovingLeft();
				break;
			case KeyEvent.VK_RIGHT:
				robot.stopMovingRight();
				break;
			case KeyEvent.VK_UP:
				System.out.println("Stop moving up!");
				break;
			case KeyEvent.VK_DOWN:
				currentSprite = character;
				robot.setDucked(false);
				System.out.println("Stop moving down!");
				break;
			case KeyEvent.VK_SPACE:
				if( robot.hasDucked() ) {
					robot.setDucked(false);
				}
				break;
		}		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {		
	}
}
