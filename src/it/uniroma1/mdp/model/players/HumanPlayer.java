package it.uniroma1.mdp.model.players;

import java.io.Serializable;

/**
 * Represents a human player in a card game, inheriting from the Player class.
 * Implements Serializable for object serialization.
 */
public class HumanPlayer extends Player implements Serializable {
	// CONSTANTS
	public static final long serialVersionUID = -6863291316550002862L;

	// FIELDS
	private int won;
	private int lost;

	// Methods
	// Constructor
	public HumanPlayer(String nickname, int cardsInHand) {
		super(nickname, cardsInHand);
	}

	/**
	 * Get the count of games won by the player.
	 */
	public int getWon() {
		return this.won;
	}

	/**
	 * Get the count of games lost by the player.
	 */
	public int getLost() {
		return this.lost;
	}

	/**
	 * Increases the count of games won by the player.
	 */
	public void increaseWon() {
		this.won++;
	}

	/**
	 * Increases the count of games lost by the player.
	 */
	public void increaseLost() {
		this.lost++;
	}

	/**
	 * Sets the count of games won by the player.
	 * 
	 * @param won The new count of games won.
	 */
	public void setWon(int won) {
		this.won = won;
	}

	/**
	 * Sets the count of games lost by the player.
	 */
	public void setLost(int lost) {
		this.lost = lost;
	}

	/**
	 * Resets the counts of games won and lost to zero.
	 */
	public void resetGames() {
		this.won = 0;
		this.lost = 0;
	}

}
