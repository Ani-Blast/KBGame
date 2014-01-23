package kilobotgame;

public class Enemy {

	/*
	 * SECTION: Constants
	 */
	
	/*
	 * SECTION: Variables
	 */
	private int maxHP, currHP, power, speedX, centerX, centerY;
	private Background bg = GameController.getBg1();

	/*
	 * SECTION: Game Loop Method
	 */
	public void update() {
		centerX += speedX;
		speedX = bg.getSpeedX();
	}
	
	/*
	 * SECTION: Behavioral Methods
	 */
	public void die() {
		
	}
	
	public void attack() {
		
	}
	
	/*
	 * SECTION: Getters and Setters
	 */
}
