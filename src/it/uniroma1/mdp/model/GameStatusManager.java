package it.uniroma1.mdp.model;

import java.util.List;

import it.uniroma1.mdp.model.players.HumanPlayer;
import it.uniroma1.mdp.model.players.Player;

/**
 * Manages the status and turns of players in the card game.
 */
public class GameStatusManager {

	// Fields
	private List<Player> players;	
	private int currentPlayerIndex;
	
	
	// Methods
	// Constructor
	public GameStatusManager(List<Player> players) {
		// Set fields to 
		this.players = players;
		this.currentPlayerIndex = 0;	// Human player starts
	}
	
	/**
	 * Get the current player.
	 * @return
	 */
	public Player getCurrentPlayer() { 
		return this.players.get(this.currentPlayerIndex);
	}
	
	
	/**
	 * Get the index of the current player in the list.
	 * @return
	 */
	public int getCurrentPlayerIndex() {
		return this.currentPlayerIndex;
	}
	

	/**
	 * This method allows to obtain next player in the turn.
	 */
	public void setNextPlayer() {
		this.currentPlayerIndex = (this.currentPlayerIndex + 1) % players.size();

	}

	/**
	 * Reset to 0 the current player index.
	 */
	public void resetCurrentPlayerIndex() {
		this.currentPlayerIndex = 0;
	}
	
	/**
	 * Returns true if and only if the current player is an instance of the HumanPlayer Class
	 * @return
	 */
	public boolean isCurrentPlayerHuman() {
		return this.players.get(this.currentPlayerIndex).getClass().equals(HumanPlayer.class);
	}
}
