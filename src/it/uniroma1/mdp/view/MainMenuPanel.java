package it.uniroma1.mdp.view;

import javax.swing.JPanel;

/**
 * Represents the main menu panel containing buttons for main actions.
 */
public class MainMenuPanel extends JPanel {
	// Constants
	public static final long serialVersionUID = 5119244775425511828L;

	// Fields
	private NewGameButton newGame;
	private UserProfileButton userProfile;
	private SettingsButton settings;
	private QuitGameButton quit;

	// Methods
	/**
	 * Constructor.
	 */
	public MainMenuPanel() {
		super();

		// 1. Initializing fields (Runtime)
		this.newGame = new NewGameButton();
		this.userProfile = new UserProfileButton();
		this.settings = new SettingsButton();
		this.quit = new QuitGameButton();
	}

	/**
	 * Configure the UI of this panel instance, setting the layout manager, adding
	 * components, eventually action listeners and technical details.
	 */
	protected void setupUI() {
		setLayout(null); // 1. Placing component where I need

		// Setting the UI for each button and adding them as component for this panel
		// this.newGame.setup();
		add(this.newGame);
		this.newGame.setBounds(10, 10, 230, 50);

		// this.userProfile.setUps();
		add(this.userProfile);
		this.userProfile.setBounds(60, 90, 250, 60);
		this.userProfile.repaint();

		// this.settings.setUps();
		add(this.settings);
		this.settings.setBounds(120, 180, 230, 45);

		// this.quit.setUps();
		add(this.quit);
		this.quit.setBounds(135, 290, 150, 60);

		// Add details
		addDetails();

	}

	/**
	 * Adds 'technical details' for this panel instance
	 */
	private void addDetails() {
		setOpaque(false);
	}

	/**
	 * Returns the NewGameButton instance.
	 */
	public NewGameButton getNewGame() {
		return this.newGame;
	}

	/**
	 * Returns the UserProfileButton instance.
	 */
	public UserProfileButton getUserProfile() {
		return this.userProfile;
	}

	/**
	 * Returns the SettingsButton instance.
	 */
	public SettingsButton getSettings() {
		return this.settings;
	}

	/**
	 * Returns the QuitGameButton instance.
	 */
	public QuitGameButton getQuit() {
		return this.quit;
	}

}
