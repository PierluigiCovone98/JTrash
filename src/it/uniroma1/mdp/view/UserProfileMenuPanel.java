package it.uniroma1.mdp.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Represents the user profile menu panel containing user information and
 * buttons for profile management.
 */
public class UserProfileMenuPanel extends JPanel {
	// CONSTANTS
	private static final long serialVersionUID = 3369783036085013309L;

	// FIELDS
	private JLabel nickname;
	private JLabel gamesWon;
	private JLabel defeats;

	private BackButton backButton;
	private ResetButton resetButton;
	private ChangeNicknameButton changeNicknameButton;
	private SwitchUserButton switchUserButton;
	private RemoveUserButton removeUserButton;
	private AddUserButton addUserButton;

	// METHODS
	/**
	 * Constructor.
	 */
	public UserProfileMenuPanel() {
		this.nickname = new JLabel();
		this.gamesWon = new JLabel();
		this.defeats = new JLabel();

		this.backButton = new BackButton();
		this.resetButton = new ResetButton();
		this.changeNicknameButton = new ChangeNicknameButton();
		this.switchUserButton = new SwitchUserButton();
		this.removeUserButton = new RemoveUserButton();
		this.addUserButton = new AddUserButton();
	}

	/**
	 * Configure the UI of this panel instance, setting the layout manager, adding
	 * components, eventually action listeners and technical details.
	 */
	protected void setupUI() {
		// 1. Placing component where I need
		setLayout(null);

		// 2. Adding each component in a custom location
		add(this.nickname);
		this.nickname.setBounds(90, 10, 300, 50); // x, y, width, height

		add(this.gamesWon);
		this.gamesWon.setBounds(90, 80, 200, 50); // x, y, width, height

		add(this.defeats);
		this.defeats.setBounds(90, 150, 200, 50); // x, y, width, height

		// Add buttons for managing UserProfile
		addButtons();

		// Add details
		addDetails();
	}

	/**
	 * Adds buttons for managing UserProfile to the panel.
	 */
	private void addButtons() {
		int buttonWidth = 50;
		int buttonHeight = 50;
		int initialX = 20;
		int distanceBetweenButtons = 60;

		this.backButton.setBounds(initialX, 215, buttonWidth, buttonHeight);
		this.resetButton.setBounds(initialX + distanceBetweenButtons, 215, buttonWidth, buttonHeight);
		this.changeNicknameButton.setBounds(initialX + 2 * distanceBetweenButtons, 215, buttonWidth, buttonHeight);
		this.switchUserButton.setBounds(initialX + 3 * distanceBetweenButtons, 215, buttonWidth, buttonHeight);
		this.removeUserButton.setBounds(initialX + 4 * distanceBetweenButtons, 215, buttonWidth, buttonHeight);
		this.addUserButton.setBounds(initialX + 5 * distanceBetweenButtons, 215, buttonWidth, buttonHeight);

		// Aggiungi i bottoni al pannello
		add(this.backButton);
		add(this.resetButton);
		add(this.changeNicknameButton);
		add(this.switchUserButton);
		add(this.removeUserButton);
		add(this.addUserButton);
	}

	/**
	 * Adds technical details for this panel instance
	 */
	private void addDetails() {
		setOpaque(false);
	}

	/**
	 * Returns the JLabel for displaying the nickname.
	 */
	public JLabel getNicknameLabel() {
		return this.nickname;
	}

	/**
	 * Returns the JLabel for displaying the number of games won.
	 */
	public JLabel getGamesWonLabel() {
		return this.gamesWon;
	}

	/**
	 * Returns the JLabel for displaying the number of defeats.
	 */
	public JLabel getDefeatsLabel() {
		return this.defeats;
	}

	/**
	 * Returns the BackButton instance.
	 */
	public BackButton getBackButton() {
		return this.backButton;
	}

	/**
	 * Returns the ResetButton instance.
	 */
	public ResetButton getResetButton() {
		return this.resetButton;
	}

	/**
	 * Returns the SwitchUserButton instance.
	 */
	public SwitchUserButton getSwitchUserButton() {
		return this.switchUserButton;
	}

	/**
	 * Returns the RemoveUserButton instance.
	 */
	public RemoveUserButton getRemoveUserButton() {
		return this.removeUserButton;
	}
}
