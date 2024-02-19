package it.uniroma1.mdp.view.game;

import java.util.Observable;
import java.util.Observer;

import it.uniroma1.mdp.controller.TrashButtonHandler;
import it.uniroma1.mdp.view.ImageButton;

/**
 * Represents a button used to trash a card in the game session. Extends
 * ImageButton and implements the Observer interface.
 */
public class TrashButton extends ImageButton implements Observer {
	// CONSTANTS
	public static final long serialVersionUID = 6322263579290959876L;
	public static final int WIDTH = 70;
	public static final int HEIGHT = 35;
	public static final String BLACK_ICON = "images/gameSessionImages/trashB.png";
	public static final String RED_ICON = "images/gameSessionImages/trashR.png";

	// Fields
	private boolean bClickable;

	// METHODS
	// Constructor
	public TrashButton() {
		super(BLACK_ICON, BLACK_ICON, WIDTH, HEIGHT);
		this.bClickable = false;
		// 2. Setting up the UI for instances of this button
		setup();
	}

	/**
	 * Set ups the trash button button. This is used to put the card that must be
	 * trashed.
	 */
	@Override
	protected void setup() {
		// 1. Adding shared button details
		super.setup();

		// 2. Initizlize fields
		this.bClickable = false;

		// must set a border...
		addDetails();
	}

	/**
	 * Adds 'technical details' for instances of this button.
	 */
	public void addDetails() {
		// Adding action listeners
		setBorder(null);
		addActionListener(new TrashButtonHandler(this));
	}

	/**
	 * Checks if the trash button is clickable.
	 */
	public boolean isClickable() {
		return this.bClickable;
	}

	/**
	 * Sets the clickability status of the trash button.
	 */
	public void setClickable(boolean bEnabled) {
		this.bClickable = bEnabled;
	}

	/**
	 * Updates the button's appearance when observed changes occur.
	 */
	@Override
	public void update(Observable o, Object arg) {
		setIcon(RED_ICON);
		setRolloverIcon(RED_ICON);
	}

	/**
	 * Restores the default colors on the button when no more cards are drawn.
	 */
	public void restore() {
		setIcon(BLACK_ICON);
		setRolloverIcon(BLACK_ICON);
	}

}
