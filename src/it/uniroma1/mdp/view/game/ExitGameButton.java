package it.uniroma1.mdp.view.game;

import it.uniroma1.mdp.controller.ExitGameHandler;
import it.uniroma1.mdp.view.ImageButton;

/**
 * Represents an exit game button displayed in the game session. Extends the
 * ImageButton class and provides functionality for exiting the current game
 * session.
 */
public class ExitGameButton extends ImageButton {
	// CONSTANTS
	public static final long serialVersionUID = -7745479512033480313L;
	private static final int WIDTH = 30;
	private static final int HEIGHT = 30;

	// METHOD
	/**
	 * Constructor for ExitGameButton. Initializes the button with images for the
	 * normal and pressed states, along with specified width and height.
	 */
	public ExitGameButton() {
		super("images/gameSessionImages/HomeB.png", "images/gameSessionImages/HomeR.png", WIDTH, HEIGHT);

		// 2. Setting up the UI for instances of this button
		setup();
	}

	/**
	 * Set ups the UI for the "Exit Button." Overrides the setup method in the
	 * ImageButton class to add specific behavior for the exit button. It sets the
	 * pressed icon and adds an action listener to handle the exit game action.
	 */
	@Override
	protected void setup() {
		// 1. Adding shared button details
		super.setup();

		// 2. Setting the pressed icon
		super.setPressedIcon(this.getRolloverIcon());

		// 3. Adding listeners
		addActionListener(new ExitGameHandler());
	}
}
