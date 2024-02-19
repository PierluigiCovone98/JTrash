package it.uniroma1.mdp.view.game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * Represents the main frame for the game, containing the human display panel
 * and the game table panel.
 */
public class GameFrame extends JFrame {
	// CONSTANTS
	public static final long serialVersionUID = -6293291337766662579L;

	// FIELDS
	private HuDisplayPanel huDisplay;
	private GameTablePanel gameTable;

	// METHODS
	/**
	 * Constructor for GameFrame.
	 */
	public GameFrame() {
		super();

		// 1. Initialize fields
		this.huDisplay = new HuDisplayPanel();
		this.gameTable = new GameTablePanel();
	}

	/**
	 * Configure the UI of this frame instance, setting the layout manager, adding
	 * components, eventually action listeners and technical details.
	 */
	public void setupUI() {
		// 1. Set up the UI for the game table & adding it to the center of this frame
		this.huDisplay.setupUI();
		add(this.huDisplay, BorderLayout.NORTH);

		// 2. Set up the UI for the game table & adding it to the center of this frame
		add(this.gameTable, BorderLayout.CENTER); // It's setup is chosen based on the user decisions

		// Adding technical details
		addDetails();
	}

	/**
	 * Adding 'technical details' for this frame instance
	 */
	private void addDetails() {
		setUndecorated(true); // This removes the window's title bar and borders.

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setResizable(false);
		pack();
	}

	/**
	 * Getter for HuDisplayPanel.
	 */
	public HuDisplayPanel getHuDisplay() {
		return this.huDisplay;
	}

	/**
	 * Getter for GameTablePanel.
	 */
	public GameTablePanel getGameTable() {
		return this.gameTable;
	}

}
