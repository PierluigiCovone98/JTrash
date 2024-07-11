package it.uniroma1.mdp.view;
import java.io.BufferedInputStream
;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The AudioManager class handles the playback of audio files.
 * It provides methods to load, play, and control audio files.
 */
public abstract class AudioManager {

	private boolean bTurnedOn;	// used to manage the play method
	private boolean bLoop;
	private Clip clip;
	
	 /**
     * Constructor.
     * Initializes AudioManager with audio playback enabled and loop disabled.
     */
	public AudioManager() {
		// 1. Sounds are turned on at beginning
		this.bTurnedOn = true;
		this.bLoop = false;
		this.clip = null;
	}
	
	

    /**
     * Loads the audio file specified by the given filename into the audio clip.
     */
	public void loadFileAudio(String filename) {
			try {
				InputStream in = new BufferedInputStream(new FileInputStream(filename));
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
				this.clip = AudioSystem.getClip();
				this.clip.open(audioIn);
	
				in.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
	}
	
	 /**
     * Plays the loaded audio clip if audio playback is enabled.
     * If loop is enabled, the clip will play continuously.
     */
	protected void play() {
		// 1. If the audio playback is enables
		if (this.bTurnedOn && this.clip != null) {
			if (bLoop)
				this.clip.loop(Clip.LOOP_CONTINUOUSLY);
			this.clip.start();
		}
		// Otherwise, no audio are played
	}
	
	
	/**
	 * Checks whether audio playback is enabled.
	 */
	public boolean isTurnedOn() {
		return this.bTurnedOn;
	}
	
	/**
	 * Checks whether audio playback is in loop;
	 */
	public boolean isLoop() {
		return this.bLoop;
	}
	
	/**
	 * Retrieves the audio clip currently being played.
	 */
	public Clip getClip() {
		return this.clip;
	}

	
	
	/**
	 * Sets the state of audio playback.
	 */
	public void setTurnedOn(boolean bTurnedOn) {
		this.bTurnedOn = bTurnedOn;
	}
	
	/**
	 * Sets the loop state of audio playback.
	 */
	public void setLoop(boolean bLoop) {
		this.bLoop = bLoop;
	}
	
	
	/**
	 * Sets the audio clip to be played.
	 */
	public void setClip(Clip clip) {
		this.clip = clip;
	}
	
}

	
	