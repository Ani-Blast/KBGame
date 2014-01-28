package kilobotgame.framework;

import java.awt.Image;
import java.util.ArrayList;

public class Animation {

	/*
	 * SECTION: Variables
	 * 
	 * - frames contains the images and their display durations
	 * - animTime is how much time has passed since last image was displayed
	 * - totalDuration is time displayed per frame
	 * - Command order in synchronized ensures what's run before what when it comes
	 * 		to assigning tasks via multiple Threads.
	 */
	private ArrayList<AnimFrame> frames;
	private int currentFrame;
	private long animTime; // For more accurate numbers
	private long totalDuration;
	
	public Animation() {
		frames = new ArrayList<AnimFrame>();
		totalDuration = 0;
		
		synchronized(this) {
			animTime = 0;
			currentFrame = 0;
		}
	}
	
	private class AnimFrame {
		/*
		 * SECTION: AF Variables
		 */
		Image image;
		long endTime;
		
		public AnimFrame( Image image, long endTime ) {
			this.image = image;
			this.endTime = endTime;
		}
	}
	
	/*
	 * SECTION: Game Loop Methods
	 */
	public synchronized void update( long elapsedTime ) {
		if( frames.size() > 1 ) {
			animTime += elapsedTime;
			if( animTime >= totalDuration ) {
				animTime = animTime % totalDuration;
				currentFrame = 0;
			}
			
			while( animTime > getFrame( currentFrame ).endTime ) {
				currentFrame++;
			}
		}
	}
	
	/*
	 * SECTION: Behavioral Methods
	 */
	public synchronized void addFrame( Image image, long duration ) {
		totalDuration += duration;
		frames.add( new AnimFrame( image, totalDuration ) );
	}
	
	public synchronized Image getImage() {
		if( frames.size() == 0 ) {
			return null;
		} else { 
			return getFrame( currentFrame ).image;
		}
	}
	
	private AnimFrame getFrame( int i ) {
		return frames.get(i);
	}

}
