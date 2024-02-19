package it.uniroma1.mdp.view;

import javax.swing.JPanel;
import it.uniroma1.mdp.controller.FullGameModalityHandler;

/**
 * Represents the new game menu panel containing options for game mode, number
 * of players, and buttons for go back to the main menu and for starting a new
 * game.
 */
public class NewGameMenuPanel extends JPanel {
	// Constants
	public static final long serialVersionUID = -4077387015041387245L;

	// Fields
	private LabeledComboBox<String> gameMode;
	private LabeledComboBox<Integer> numberOfPlayers;
	private BackButton backButton;
	private PlayGameButton playGame;

	// Methods
	/**
	 * Constructor.
	 */
	public NewGameMenuPanel() {
		// 1. Creating and assigning the instances (runtime)
		this.gameMode = new LabeledComboBox<String>("Game Mode:", new String[] { "Single Game", "Full Game" });
		this.numberOfPlayers = new LabeledComboBox<Integer>("Number of Players:", new Integer[] { 2, 3, 4 });
		this.backButton = new BackButton();
		this.playGame = new PlayGameButton();
	}

	/**
	 * Configure the UI of this panel instance, setting the layout manager, adding
	 * components, eventually action listeners and technical details.
	 */
	protected void setupUI() {
		// 1. Placing components where I need
		setLayout(null);

		this.gameMode.setupUI();
		this.gameMode.setBounds(110, 20, 200, 60); // x, y, width, height
		add(this.gameMode);
		this.gameMode.getComboBox().addActionListener( new FullGameModalityHandler(this.gameMode.getComboBox()) );

		this.numberOfPlayers.setupUI();
		this.numberOfPlayers.setBounds(70, 140, 300, 60); // x, y, width, height, adjust y for vertical spacing
		add(this.numberOfPlayers);

		this.backButton.setBounds(60, 230, 50, 50); // x, y, width, height
		add(this.backButton);

		this.playGame.setBounds(315, 230, 60, 50); // x, y, width, height, placed next to backButton
		add(this.playGame);

		// Adding details
		addDetails();
	}

	/**
	 * Adds 'technical details' for this panel instance.
	 */
	private void addDetails() {
		setOpaque(false);
	}

	/**
	 * Returns the LabeledComboBox for selecting the game mode.
	 */
	public LabeledComboBox<String> getGameMode() {
		return this.gameMode;
	}

	/**
	 * Returns the LabeledComboBox for selecting the number of players.
	 */
	public LabeledComboBox<Integer> getNumberOfPlayers() {
		return this.numberOfPlayers;
	}

	/**
	 * Returns the BackButton instance.
	 */
	public BackButton getBackButton() {
		return this.backButton;
	}

	/**
	 * Returns the PlayGameButton instance.
	 */
	public PlayGameButton getPlayGame() {
		return this.playGame;
	}

}
