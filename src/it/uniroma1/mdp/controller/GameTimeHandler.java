package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.uniroma1.mdp.view.game.TimerLabel;

/**
 * Handles the game time and updates the timer label accordingly.
 * This listener is responsible for incrementing the elapsed time and updating the timer label to display the current game duration.
 */
public class GameTimeHandler implements ActionListener {
	// FIELDS
	private TimerLabel timerLabel;

	// METHODS
	/**
	 * Constructor.
	 */
	public GameTimeHandler(TimerLabel timerLabel) {
		this.timerLabel = timerLabel;
	}
	

    /**
     * Increments the elapsed time by one second and updates the timer label to display the current game duration.
     * This method is triggered by an action event, typically associated with a timer.
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.timerLabel.setElapsedTimeInSeconds(1);
		this.timerLabel.updateTimerLabel();
	}

}
