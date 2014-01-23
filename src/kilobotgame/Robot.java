package kilobotgame;

public class Robot {
	
	/*
	 * SECTION: Constants
	 */
	final int walkingLimitX = 200;
	final int robotStartY = 382;
	final int robotStartX = 100;
	final int moveSpeed = 6;
	final int jumpSpeed = 15;
	
	/*
	 * SECTION: Variables
	 * 
	 * (0,0) is top left corner of the screen.
	 * 440 pixels is robot's feet touch the ground.
	 * 382 pixels is robot's center.
	 * Moving variables fix issue of robot stopping whenever a key is
	 * 	released regardless of whether another key is still pressed.
	 * No movement while robot is ducking.
	 */
	/* 

	 */
    private boolean ducked = false;
	private boolean jumped = false; // false on ground, true in air
    private boolean movingLeft = false;
    private boolean movingRight = false;
	private int centerX = robotStartX;
	private int centerY = robotStartY;
	private int speedX = 0; // x-velocity; negative means going left
	private int speedY = 1; // y-velocity; y1 > y2 means rising into the air
	private static Background bg1 = GameController.getBg1();
	private static Background bg2 = GameController.getBg2();

	/*
	 * SECTION: Control Handling
	 */

	public void moveRight() {
		if( !ducked ) {
			speedX = moveSpeed;
		}
	}

	public void moveLeft() {
		if( !ducked ) {
			speedX = -moveSpeed;
		}
	}
	
	public void stopMovingRight() {
		setMovingRight(false);
		stop();
	}
	
	public void stopMovingLeft() {
		setMovingLeft(false);
		stop();
	}
	
	public void stop() {
		if( isMovingRight() && !isMovingLeft() ) {
			moveRight();
		}
		if( !isMovingRight() && isMovingLeft() ) {
			moveLeft();
		}
		if( !isMovingRight() && !isMovingLeft() ) {
			speedX = 0;
		}
	}

	public void jump() {
		if( !jumped ) {
			speedY = -jumpSpeed;
			jumped = true;
		}
	}
	
	/*
	 * SECTION: Game Loop Method
	 */
	public void update() {
		
		// Moves Char or scrolls background accordingly
		
		if( speedX < 0 ) {
			centerX += speedX;
		} 
		if( speedX == 0 || speedX < 0 ) {
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);
		} 
		if( centerX <= walkingLimitX && speedX > 0 ) {
			centerX += speedX;
		}
		if ( speedX > 0 && centerX > walkingLimitX ){
			bg1.setSpeedX( -moveSpeed );
			bg2.setSpeedX( -moveSpeed );
		}
		
		// Updates Y Position
		centerY += speedY;
		if( centerY + speedY >= robotStartY ) {
			centerY = robotStartY;
		}
		
		// Handles Jumping
		if( jumped ) {
			speedY += 1; // Start the process of falling
			
			// He's touched the ground
			if( centerY + speedY >= robotStartY ) {
				centerY = robotStartY;
				speedY = 0;
				jumped = false;
			}
		}
		
		/*
		 *  Prevents going beyond X coordinate of 0
		 *  Robot's leftmost hand touches the wall at centerX = 61
		 */
		
		if( centerX + speedX <= robotStartX-40 ) {
			centerX = robotStartX-39;
		}
	}
	
	/*
	 * SECTION: Getters and Setters
	 */
	public boolean hasJumped() {
		return jumped;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean hasDucked() {
		return ducked;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}

}
