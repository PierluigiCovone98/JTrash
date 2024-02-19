package it.uniroma1.mdp.view.game;

/**
 * Represents an horizontal card button in the game interface.
 */
public class HCardButton extends CardButton {
	// Constants
	public static final long serialVersionUID = -6598512835975972577L;
	public static final int WIDTH = 130;
	public static final int HEIGHT = 80;

	// Methods
	/**
	 * Constructor.
	 */
	public HCardButton(String iconPath) {
		super(iconPath, WIDTH, HEIGHT);
	}

}
