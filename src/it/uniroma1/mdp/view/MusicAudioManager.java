package it.uniroma1.mdp.view;

import javax.sound.sampled.Clip;

/**
 * The MusicAudioManager class extends AudioManager and provides a method to play music audio files.
 */
public class MusicAudioManager extends AudioManager {

	/**
	 * Plays the 'jazz.wav' audio file.
	 * It is a song.
	 */
	public void playJazzLoop() {
		super.loadFileAudio("sounds/jazz.wav");
		setLoop(true);
		super.play();
	}
	
	
	/**
	 * Stops the playback of the audio clip if it is not null and resets the playback position to the beginning.
	 */
	public void stopAndReset() {
		Clip clip = getClip();
	    if (clip != null && clip.isRunning()) { // Check if the clip is not null and is currently playing
	        if (isLoop())
	        	clip.loop(0); // cease the loop
	        
	    	clip.stop(); // Stop the playback
	        clip.setMicrosecondPosition(0); // Reset the playback position to the beginning
	    }
	}	
}
