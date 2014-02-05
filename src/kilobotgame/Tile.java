package kilobotgame;

import java.awt.Image;

public class Tile {

	/*
	 * SECTION: Constants
	 * 
	 * - Each tile image is 40 x 40. 40*20 = 800, 40*12 = 480
	 */
	public final static int tileLength = 40;
	final static int speedMultiplier = 5;
	
	/*
	 * SECTION: Varibles
	 * 
	 * - type indicates if a tile is an ocean (1) or dirt (2) tile.
	 * - We'll be mapping Tiles by pixel locattion but creating them by indices.
	 */
	private int tileX, tileY, speedX, type;
	private Background bg = GameController.getBg1();
	public Image tileImage;
	
	public Tile( int x, int y, int typeInt ) {
		tileX = x * tileLength;
		tileY = y * tileLength;
		
		type = typeInt;
		if( typeInt == 1 ) {
			tileImage = GameController.tileocean;
		} else if( typeInt == 2 ) {
			tileImage = GameController.tiledirt;
		}
	}
	
	/*
	 * SECTION: Game Loop Methods
	 * 
	 * - We employ parllax scrolling by having the ocean scroll
	 * 		faster than the background.
	 * 
	 */
	public void update() {
		if( type == 1 ) {
			if( bg.getSpeedX() == 0 ) {
				speedX = -1;
			} else {
				speedX = -2;
			}
		} else {
			speedX = bg.getSpeedX()*speedMultiplier;
		}
		
		tileX += speedX;
	}

	/*
	 * SECTION: Getters and Setters
	 */
	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}
}
