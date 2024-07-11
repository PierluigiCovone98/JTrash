package it.uniroma1.mdp.view.game;

import javax.swing.JLabel;

import it.uniroma1.mdp.view.utilities.StringFormatter;

/**
 * Represents a JLabel for displaying the current turn in the game. By default,
 * the label is initialized with "Your Turn" as the starting turn for the Human
 * player. The text can be customized using the setText method, and it is
 * formatted using StringFormatter. This class provides methods to get and set
 * the text of the label.
 */
public class TurnLabel extends JLabel {
	// CONSTANTS
	public static final long serialVersionUID = 6947736837819198063L;

	// METHODS
	/**
	 * Constructor.
	 */
	public TurnLabel() {
		super();
		// 1. As default is always the Human player to sarts
		setText("Your Turn");
	}

	/**
	 * Override of the getText method to provide a specialized implementation.
	 */
	@Override
	public String getText() {
		return super.getText();
	}

	/**
	 * Override of the setText method to format the text using StringFormatter.
	 */
	@Override
	public void setText(String turnLabel) {
		super.setText(StringFormatter.formatString(turnLabel));
	}

}
