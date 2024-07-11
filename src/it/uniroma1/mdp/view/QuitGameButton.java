package it.uniroma1.mdp.view;

import it.uniroma1.mdp.controller.QuitButtonHandler;

/**
 * Represents a button for quitting the game. Extends the ImageButton class
 * and is equipped with specific images and functionality.
 */
public class QuitGameButton extends ImageButton {
	// Constants
	public static final long serialVersionUID = 7420042704516328287L;
	private static final int WIDTH = 150;
	private static final int HEIGHT = 60;

	// Methods
	/**
	 * Constructor.
	 */
	public QuitGameButton() {
		super("images/frame/qgB.png", "images/frame/qgR.png", WIDTH, HEIGHT);
		
		// set ups the UI for this button
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
		addActionListener( new QuitButtonHandler() );
	}
}
