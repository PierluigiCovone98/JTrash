package it.uniroma1.mdp.view.game;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import it.uniroma1.mdp.view.utilities.SpacePanelCreator;

/**
 * Represents the panel displaying human-related information during the game.
 */
public class HuDisplayPanel extends JPanel {
	// Constants
	public static final long serialVersionUID = 744671065129915099L;

	// FIELDS
	private TurnLabel turnLabel;
	private TimerLabel timerLabel;
	private ExitGameButton exitGame;

	/**
	 * Constructor for HuDisplayPanel.
	 */
	public HuDisplayPanel() {
		super(new FlowLayout(FlowLayout.CENTER)); // Setting the layout

		// 1. Initializing fields
		this.turnLabel = new TurnLabel();
		this.timerLabel = new TimerLabel();
		this.exitGame = new ExitGameButton();
	}

	/**
	 * Configure the UI of this panel instance, setting the layout manager, adding
	 * components, eventually action listeners and technical details.
	 */
	protected void setupUI() {
		// 1. Adding the turn label
		add(this.turnLabel);

		// 2. Make space between first and second component
		add(SpacePanelCreator.createSpacePanel(510, getHeight()));

		// 3. Adding the timer label
		add(this.timerLabel);

		// 2. Make space between first and second component
		add(SpacePanelCreator.createSpacePanel(630, getHeight()));

		// 4. Adding the exit game button, after set up
		add(this.exitGame);

		// 5. Adding technical details
		addDetails();
	}

	/**
	 * Adding 'technical details' for this frame instance
	 */
	private void addDetails() {
		// setOpaque(false);
		setBackground(Color.WHITE);
	}

	/**
	 * Makes the timer starts from 0 again.
	 */
	public void resetTimer() {
		// Invoking the reset method
		this.timerLabel.reset();
		this.revalidate();
		this.repaint();
	}

	/**
	 * Gets the TurnLabel instance associated with this HuDisplayPanel.
	 */
	public TurnLabel getTurnLabel() {
		return this.turnLabel;
	}

	/**
	 * Gets the TimerLabel instance associated with this HuDisplayPanel.
	 */
	public TimerLabel getTimerLabel() {
		return this.timerLabel;
	}

	/**
	 * Gets the ExitGameButton instance associated with this HuDisplayPanel.
	 */
	public ExitGameButton getExitGame() {
		return this.exitGame;
	}

}