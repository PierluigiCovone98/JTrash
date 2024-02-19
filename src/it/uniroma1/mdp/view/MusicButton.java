package it.uniroma1.mdp.view;

import it.uniroma1.mdp.controller.MusicButtonHandler;
/**
 * The MusicButton class extends the ImageButton class and represents a button for managing music audio.
 * It provides methods to set up the button's appearance, behavior, and functionality.
 */
public class MusicButton extends ImageButton {
	// Constants
	public static final long serialVersionUID = 6999277878116425039L;
	private static final int WIDTH = 100;
	private static final int HEIGHT = 100;
	public static final String ICON_PATH = "images/frame/musicOn.png";
	public static final String PRESSED_ICON_PATH = "images/frame/musicOff.png";

	// Methods
	/**
	 * Constructor.
	 */
	public MusicButton() {
		super(ICON_PATH, PRESSED_ICON_PATH, WIDTH, HEIGHT);

		// 2. Setting up the UI for instances of this button
		setup();
	}

	/**
	 * Constructor. Initializes the MusicButton with the specified icon paths,
	 * width, and height.
	 */
	@Override
	protected void setup() {
		// 1. Adding shared button details
		super.setup();

		// 2. Setting the pressed icon
		super.setPressedIcon(this.getRolloverIcon());

		// 3. Adding listeners
		addActionListener(new MusicButtonHandler());

		// 4. Setting tooltip text
		setToolTipText("Mute/Unmute music");
	}
}
