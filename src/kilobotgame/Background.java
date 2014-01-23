package kilobotgame;

public class Background {

	/*
	 * SECTION: Constants
	 */
	final static int bgWidth = 2160;

	/*
	 * SECTION: Variables
	 */
	private int bgX, bgY, speedX; 
	
	// Background start static.
	public Background( int x, int y ) {
		bgX = x;
		bgY = y;
		speedX = 0;
	}
	
	/*
	 * SECTION: Game Loop Methods
	 */
	
	public void update() {
		bgX += speedX;
		
		if( bgX <= -bgWidth ) {
			bgX += ( bgWidth*2 );
		}
	}
	
	/*
	 * SECTION: Getters and Setters
	 */
	public int getBgX() {
		return bgX;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
}
