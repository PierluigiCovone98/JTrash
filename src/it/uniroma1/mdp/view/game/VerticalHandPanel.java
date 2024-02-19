package it.uniroma1.mdp.view.game;

import java.awt.Color;
import java.awt.GridLayout;

/**
 * Abstract class representing a vertical hand panel to display a player's hand of cards.
 * Extends HandPanel and provides a specific layout for a vertical orientation of cards.
 */
public abstract class VerticalHandPanel extends HandPanel {
	// CONSTANTS
	private static final long serialVersionUID = -8536555618326601376L;

	// METHODS
	// Constructor
	public VerticalHandPanel() {
		super(new GridLayout(5, 2, 10, 10));
	}

	/**
	 * Adding 'technical details'.
	 */
	protected void addDetails() {
		setBackground(Color.WHITE);
	}
}
