package it.uniroma1.mdp.view;

import it.uniroma1.mdp.controller.SettingsButtonHandler;

public class SettingsButton extends ImageButton {
	// Constants
	public static final long serialVersionUID = 7420042704516328287L;
	private static final int WIDTH = 230;
	private static final int HEIGHT = 45;


	// Methods
	public SettingsButton() {
		super("images/frame/sB.png", "images/frame/sG.png", WIDTH, HEIGHT);
		
		// Setting up the UI of this button
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
		addActionListener( new SettingsButtonHandler() );
	}
	
	
}
