package it.uniroma1.mdp.view;

import it.uniroma1.mdp.controller.UserProfileButtonHandler;

/**
 * Represents a button for the "UserProfile" menu entry. Extends the ImageButton class
 * and is equipped with specific images and functionality.
 */
public class UserProfileButton extends ImageButton {
	// Constants
	public static final long serialVersionUID = 7420042704516328287L;
	private static final int WIDTH = 250;
	private static final int HEIGHT = 60;

	// Methods
	public UserProfileButton() {
		super("images/frame/upB.png","images/frame/upG.png", WIDTH, HEIGHT);

		// set ups the UI of this button
		setup();
	}

	/**
	 * Set ups "UserProfile" menu entry button.
	 */
	@Override
	protected void setup() {
		// 1. Adding shared button details
		super.setup();
		
		// 2. Setting the pressed icon
		super.setPressedIcon(this.getRolloverIcon());
		
		// 3. Adding listeners 
		addActionListener(new UserProfileButtonHandler() );
	}
}
