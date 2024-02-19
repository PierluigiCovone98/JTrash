package it.uniroma1.mdp.view.game;

/**
 * Represents a vertical card button in the game interface.
 */
public class VCardButton extends CardButton {
	// Constants
	public static final long serialVersionUID = -3475849137793202862L;
	public static final int WIDTH = 80;
	public static final int HEIGHT = 130;

	
	// Methods
	/**
	 * Constructor.
	 */
	public VCardButton(String iconPath) {
		super(iconPath, WIDTH, HEIGHT);
	}
	
}
