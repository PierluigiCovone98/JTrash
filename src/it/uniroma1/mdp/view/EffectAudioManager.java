package it.uniroma1.mdp.view;

/**
 * The EffectAudioManager class extends AudioManager and provides methods to
 * play various sound effects. It includes methods to play specific audio files
 * for different events or actions.
 */
public class EffectAudioManager extends AudioManager {

	/**
	 * Plays the 'click.wav' audio file.
	 */
	public void playClick() {
		this.playEffect("sounds/click.wav");
	}

	/**
	 * Plays the 'alert.wav' audio file.
	 */
	public void playAlert() {
		this.playEffect("sounds/alert.wav");
	}

	/**
	 * Plays the 'draw.wav' audio file.
	 */
	public void playDraw() {
		this.playEffect("sounds/draw.wav");
	}

	/**
	 * Plays the 'error.wav' audio file.
	 */
	public void playError() {
		this.playEffect("sounds/error.wav");
	}

	/**
	 * Plays the 'win.wav' audio file.
	 */
	public void playWin() {
		this.playEffect("sounds/win.wav");
	}

	/**
	 * Plays the 'winRonaldo.wav' audio file.
	 */
	public void playWinRonaldo() {
		this.playEffect("sounds/winRonaldo.wav");
	}

	/**
	 * Plays the 'lose.wav' audio file.
	 */
	public void playLose() {
		this.playEffect("sounds/lose.wav");
	}

	/**
	 * Plays the 'block.wav' audio file.
	 */
	public void playBlock() {
		this.playEffect("sounds/block.wav");
	}

	/**
	 * Plays the specified audio effect file.
	 */
	private void playEffect(String filename) {
		super.loadFileAudio(filename);
		super.play();
	}

}
