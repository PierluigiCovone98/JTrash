package it.uniroma1.mdp.view;

import it.uniroma1.mdp.controller.BackButtonHandler;

/**
* Represents a back button with specific styling for the application.
* Extends the ImageButton class and sets up the UI for instances of this button.
*/
public class BackButton extends ImageButton {
	// Constants
	public static final long serialVersionUID = 7420042704516328287L;
	private static final int WIDTH = 25;
	private static final int HEIGHT = 25;

	// Methods
	/**
	 * Constructor.
	 */
	public BackButton() {
		super("images/frame/backB.png", "images/frame/backO.png", WIDTH, HEIGHT);

		// 2. Setting up the UI for instances of this button
		setup();
	}

	 /**
     * Set ups the "Back" button, including shared button details, pressed icon, listeners, and tooltip text.
     */
	@Override
	protected void setup() {
		// 1. Adding shared button details
		super.setup();

		// 2. Setting the pressed icon
		super.setPressedIcon(this.getRolloverIcon());

		// 3. Adding listeners
		addActionListener(new BackButtonHandler());
		
		// 4. Setting tooltip text
		setToolTipText("Back to Main Menu");
	}
}
