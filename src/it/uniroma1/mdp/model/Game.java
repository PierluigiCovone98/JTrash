package it.uniroma1.mdp.model;

/**
 * Abstract class representing a card game. Manages the number of players, game
 * table, and game status.
 */
public abstract class Game {
	// CONSTANTS ----------------------------
	public static final int MIN_NUMBER_OF_PLAYERS = 2;
	public static final int MAX_NUMBER_OF_PLAYERS = 4;

	// FIELDS --------------------------------
	private int numberOfPlayers;
	private GameTable gameTable;
	private GameStatusManager gameStatusManager;

	// METHODS --------------------------------
	// Constructor
	public Game(int numberOfPlayers) {
		// 1. Initializing fields
		setNumberOfPlayers(numberOfPlayers);
		this.gameTable = new GameTable();
		this.gameStatusManager = new GameStatusManager(this.gameTable.getPlayers()); // players just contains the human
																						// player
	}

	/**
	 * Initializes the game by setting up the game table.
	 */
	public void initilizeGame() {
		this.gameTable.initializeGameTable(this.numberOfPlayers);
	}

	/**
	 * Advances the game to the next turn by updating the current player.
	 */
	public void nextTurn() {
		this.gameStatusManager.setNextPlayer();
	}

	/**
	 * Abstract method representing the logic for the next match in the game.
	 */
	// public abstract void nextMatch(Player player);

	/**
	 * Retrieves the number of players in the game.
	 */
	public int getNumberOfPlayers() {
		return this.numberOfPlayers;
	}

	/**
	 * Retrieves the game table associated with the game.
	 */
	public GameTable getGameTable() {
		return this.gameTable;
	}

	/**
	 * Retrieves the game status manager associated with the game.
	 */
	public GameStatusManager getGameStatusManager() {
		return this.gameStatusManager;
	}

	/**
	 * Sets the number of players for the game. Throws
	 * InvalidNumberOfPlayersException if the specified number is not within the
	 * allowed range.
	 */
	public void setNumberOfPlayers(int numberOfPlayers) throws InvalidNumberOfPlayersException {
		if (numberOfPlayers >= MIN_NUMBER_OF_PLAYERS && numberOfPlayers <= MAX_NUMBER_OF_PLAYERS) {
			this.numberOfPlayers = numberOfPlayers;
		} else
			throw new InvalidNumberOfPlayersException("Number of players is not allowed:" + numberOfPlayers);
	}
}
