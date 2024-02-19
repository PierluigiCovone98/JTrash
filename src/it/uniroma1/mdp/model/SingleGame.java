package it.uniroma1.mdp.model;

/**
 * Represents a single-match Trash game, extending the generic Game class.
 */
public class SingleGame extends Game {

	// Constructor
	public SingleGame(int numberOfPlayers) {
		super(numberOfPlayers);
	}

	/**
	 * Overrides the setNumberOfPlayers method from the super class. Calls the super
	 * method to handle the number of players.
	 */
	@Override
	public void setNumberOfPlayers(int numberOfPlayers) {
		super.setNumberOfPlayers(numberOfPlayers);
	}

}
