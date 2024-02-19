package it.uniroma1.mdp.view;

import it.uniroma1.mdp.controller.SwitchUserHandler;


/**
* Represents a switch user button with specific styling for the application.
* Extends the ImageButton class and sets up the UI for instances of this button.
*/
public class SwitchUserButton extends ImageButton {
	// Constants
	public static final long serialVersionUID = 7420042704516328287L;
	private static final int WIDTH = 25;
	private static final int HEIGHT = 25;

	// Methods
	/**
	 * Constructor.
	 */
	public SwitchUserButton() {
		super("images/frame/switchB.png", "images/frame/switchR.png", WIDTH, HEIGHT);

		// 2. Setting up the UI for instances of this button
		setup();
	}

	/**
     * Set ups the "SwitchUser" button, including shared button details, pressed icon, listeners, and tooltip text.
     */
	@Override
	protected void setup() {
		// 1. Adding shared button details
		super.setup();

		// 2. Setting the pressed icon
		super.setPressedIcon(this.getRolloverIcon());

		// 3. Adding listeners
		addActionListener(new SwitchUserHandler() );
		
		// 4. Setting tooltip text
		setToolTipText("Switch to an existing user");
	}
}
