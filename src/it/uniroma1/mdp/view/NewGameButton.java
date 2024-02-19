package it.uniroma1.mdp.view;

import it.uniroma1.mdp.controller.NewGameButtonHandler;

/**
 * Represents a button for the "New Game" menu entry. Extends the ImageButton class
 * and is equipped with specific images and functionality.
 */
public class NewGameButton extends ImageButton {
	// Constants
	public static final long serialVersionUID = 7420042704516328287L;
	private static final int WIDTH = 230;
	private static final int HEIGHT = 50;


	// Methods
	/**
	 * Constructor.
	 */
	public NewGameButton() {
		super("images/frame/ngB.png", "images/frame/ngG.png", WIDTH, HEIGHT);
		
		// 2. Setting up the UI for instances of this button
		setup();
	}

	/**
	 * Set ups "New Game" menu entry button.
	 */
	@Override
	protected void setup() {
		// 1. Adding shared button details
		super.setup();
		
		// 2. Setting the pressed icon
		super.setPressedIcon(this.getRolloverIcon());
		
		// 3. Adding listeners 
		addActionListener(new NewGameButtonHandler() );
	}
}
