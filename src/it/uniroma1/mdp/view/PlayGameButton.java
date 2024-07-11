package it.uniroma1.mdp.view;

import it.uniroma1.mdp.controller.PlayGameButtonHandler;
import it.uniroma1.mdp.controller.InitalizeGameFrameHandler;



/**
* Represents a play game button with specific styling for the application.
* Extends the ImageButton class and sets up the UI for instances of this button.
*/
public class PlayGameButton extends ImageButton {
	// Constants
	public static final long serialVersionUID = -5435117181526001624L;
	private static final int WIDTH = 25;
	private static final int HEIGHT = 35;

	// Methods
	/**
	 * Constructor.
	 */
	public PlayGameButton() {
		super("images/frame/playB.png", "images/frame/playG.png", WIDTH, HEIGHT);
		// 2. Setting up the UI for instances of this button
		setup();
	}

	 /**
     * Set ups the "PlayGame" button, including shared button details, pressed icon, listeners, and tooltip text.
     */
	@Override
	protected void setup() {
		// 1. Adding shared button details
		super.setup();

		// 2. Setting the pressed icon
		super.setPressedIcon(this.getRolloverIcon());

		// 3. Adding listeners
		addActionListener(new InitalizeGameFrameHandler());	// Creating the effective instance
		addActionListener(new PlayGameButtonHandler());	// Shows up the window
	
		// 4. Add the tooltip text
		setToolTipText("Play");
	}

}
