package it.uniroma1.mdp.view;

import it.uniroma1.mdp.controller.ChangeNicknameHandler;

/**
* Represents a change nickname button with specific styling for the application.
* Extends the ImageButton class and sets up the UI for instances of this button.
*/
public class ChangeNicknameButton extends ImageButton {
	// Constants
	public static final long serialVersionUID = 7420042704516328287L;
	private static final int WIDTH = 25;
	private static final int HEIGHT = 25;

	// Methods
	/**
	 * Constructor.
	 */
	public ChangeNicknameButton() {
		super("images/frame/changeB.png", "images/frame/changeR.png", WIDTH, HEIGHT);

		// 2. Setting up the UI for instances of this button
		setup();
	}

	/**
     * Set ups the "ChangeNickanme" button, including shared button details, pressed icon, listeners, and tooltip text.
     */
	@Override
	protected void setup() {
		// 1. Adding shared button details
		super.setup();

		// 2. Setting the pressed icon
		super.setPressedIcon(this.getRolloverIcon());

		// 3. Adding listeners
		addActionListener(new ChangeNicknameHandler() );
		
		// 4. Setting tooltip text
		setToolTipText("Change Nickname");
	}
}
