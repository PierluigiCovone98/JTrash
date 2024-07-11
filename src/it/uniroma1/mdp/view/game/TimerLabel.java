package it.uniroma1.mdp.view.game;

import javax.swing.JLabel;
import javax.swing.Timer;

import it.uniroma1.mdp.controller.GameTimeHandler;
import it.uniroma1.mdp.view.utilities.StringFormatter;

/**
 * Represents a JLabel for displaying the elapsed time during the game as a
 * timer. The timer is updated every second, and the label is formatted using
 * StringFormatter. This class provides methods to update the timer label, reset
 * the timer, and access timer-related information. The gameTimer field is a
 * Timer object that fires events every second to update the elapsed time.
 */
public class TimerLabel extends JLabel {
	// Constants
	public static final long serialVersionUID = 5232157863705595959L;

	// Fields
	private Timer gameTimer;
	private int elapsedTimeInSeconds;

	// Methods
	/**
	 * Constructor for TimerLabel. Initializes the label with the formatted initial
	 * time "0:00". Sets up a Timer to update the elapsed time every second using
	 * the GameTimeHandler.
	 */
	public TimerLabel() {
		super(StringFormatter.formatString("0:00"));

		this.gameTimer = new Timer(1000, new GameTimeHandler(this));
		gameTimer.start(); // Starts the game timer
	}

	/**
	 * Update the label with the elapsed time in the format "mm:ss".
	 */
	public void updateTimerLabel() {
		int minutes = elapsedTimeInSeconds / 60;
		int seconds = elapsedTimeInSeconds % 60;

		setText(String.format(" %d:%02d", minutes, seconds));
	}

	/**
	 * Reset the timer to its initial state.
	 */
	public void reset() {
		elapsedTimeInSeconds = 0; // Reset elapsed time
		updateTimerLabel(); // Update the timer label
		gameTimer.restart(); // Restart the timer
	}

	/**
	 * Get the Timer object associated with the TimerLabel.
	 */
	public Timer getGameTimer() {
		return this.gameTimer;
	}

	/**
	 * Get the total elapsed time in seconds.
	 */
	public int getElapsedTimeInSeconds() {
		return this.elapsedTimeInSeconds;
	}

	/**
	 * Set the Timer object associated with the TimerLabel.
	 */
	public void setGameTimer(Timer gameTimer) {
		this.gameTimer = gameTimer;
	}

	/**
	 * Set the elapsed time in seconds, adding to the current value.
	 */
	public void setElapsedTimeInSeconds(int elapsedTimeInSeconds) {
		this.elapsedTimeInSeconds += elapsedTimeInSeconds;
	}

	/**
	 * Overrides the setText method to set the text of the TimerLabel after
	 * formatting it using StringFormatter.
	 */
	@Override
	public void setText(String turnLabel) {
		super.setText(StringFormatter.formatString(turnLabel));
	}
}
