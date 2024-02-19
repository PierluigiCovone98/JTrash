package it.uniroma1.mdp.view;

import it.uniroma1.mdp.controller.EffectsButtonHandler;

/**
 * The EffectsButton class extends the ImageButton class and represents a button for managing audio effects.
 * It provides methods to set up the button's appearance, behavior, and functionality.
 */
public class EffectsButton extends ImageButton {
	// Constants
	public static final long serialVersionUID = 6999277878116425039L;
	private static final int WIDTH = 100;
	private static final int HEIGHT = 100;
	public static final String ICON_PATH = "images/frame/effectsOn.png";
	public static final String PRESSED_ICON_PATH = "images/frame/effectsOff.png";

	// Methods
	/**
	 * Constructor.
	 */
	public EffectsButton() {
		super(ICON_PATH, PRESSED_ICON_PATH, WIDTH, HEIGHT);

		// 2. Setting up the UI for instances of this button
		setup();
	}

    /**
     * Set ups the EffectsButton, including shared button details, pressed icon, listeners, and tooltip text.
     */
	@Override
	protected void setup() {
		// 1. Adding shared button details
		super.setup();

		// 2. Setting the pressed icon
		super.setPressedIcon(this.getRolloverIcon());

		// 3. Adding listeners
		addActionListener(new EffectsButtonHandler() );
		

		// 4. Setting tooltip text
		setToolTipText("Mute/Unmute effects");
	}
}
