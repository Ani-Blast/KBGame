package kilobotgame.framework;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import kilobotgame.GameController;

public class Renderer extends Applet {

	/*
	 * SECTION: Constats
	 * 
	 * - Each pixel will be 16 x 16. 50*16=800, 30*16=480.
	 */
	static int[][] tilemap;
	static int rows, columns;
	public final static int boardWidth = 50;
	public final static int boardLength = 30;
	public final static int pixelLength = 16;
	
	/*
	 * SECTION: Variables
	 */
	public Renderer() {
	}
	
	/*
	 * SECTION: Applet Framework Methods
	 */
	@Override
	public void init() {
		
		setSize( GameController.androidWidth, 
				GameController.androidLength );
		setBackground(Color.BLACK);
		createTilemap();
		
	}
	
	@Override
	public void paint( Graphics palette ) {
		for( int row = 0; row < rows; row++ ) {
			for( int col = 0; col < columns; col++ ) {
				int row_pos = pixelLength*row;
				int col_pos = pixelLength*col;
				
				switch( tilemap[row][col] ) {
					case 0:
						palette.setColor(Color.RED);
						palette.fillRect(row_pos, col_pos, pixelLength, pixelLength);
						break;
					case 1:
						palette.setColor(Color.BLUE);
						palette.fillRect(row_pos, col_pos, pixelLength, pixelLength);
						break;
					case 2:
						palette.setColor(Color.YELLOW);
						palette.fillRect(row_pos, col_pos, pixelLength, pixelLength);
						break;
					case 3:
						palette.setColor(Color.WHITE);
						palette.fillRect(row_pos, col_pos, pixelLength, pixelLength);
						break;
					case 4:
						palette.setColor(Color.GREEN);
						palette.fillRect(row_pos, col_pos, pixelLength, pixelLength);
						break;
				}
			}
		}
	}
	
	/*
	 * SECTION: Behavioral Methods
	 * 
	 * - createTilemap will make a 2D array. Then, using a RNG, fill the array with ints btwn 0-4
	 */
	private void createTilemap() {
		tilemap = new int[boardWidth][boardLength];
		rows = boardWidth;
		columns = boardLength;
		
		Random rng = new Random();
		for( int row = 0; row < rows; row++ ) {
			for( int col = 0; col < columns; col++ ) {
				tilemap[row][col] = rng.nextInt(5);
			}
		}
	}

}
