package kilobotgame;

public class Projectile {
	
	/*
	 * SECTION: Constants
	 */
	final int moveSpeed = 7;
	
	/*
	 * SECTION: Variables
	 * 
	 * (x,y) is the top-left corner of the painted projectile object.
	 */
	private boolean visible;
	private int x, y, speedX;

	public Projectile( int startX, int startY ) {
		x = startX;
		y = startY;
		speedX = moveSpeed;
		visible = true;
	}
	
	/*
	 * SECTION: Game Loop Methods
	 * 
	 * If bullet is off screen (different Background), bullet is not visible.
	 */
	public void update() {
		x += speedX;
		if( x > GameController.androidWidth ) {
			visible = false;
		}
	}

	/*
	 * SECTION: Getters and Setters
	 */
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

}
