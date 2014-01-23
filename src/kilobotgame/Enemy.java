package kilobotgame;

public class Enemy {
	
	/*
	 * SECTION: Variables
	 */
	private int maxHP, currHP, power, speedX, centerX, centerY;
	private Background bg = GameController.getBg1();

	/*
	 * SECTION: Game Loop Methods
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

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getCurrHP() {
		return currHP;
	}

	public void setCurrHP(int currHP) {
		this.currHP = currHP;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
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

	public Background getBg() {
		return bg;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}
}
