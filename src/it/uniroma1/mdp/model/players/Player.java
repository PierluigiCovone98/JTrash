package it.uniroma1.mdp.model.players;

import java.util.Observable;
import java.util.stream.Stream;

import it.uniroma1.mdp.model.card.Card;

/**
 * Abstract class representing a player in a card game.
 * Inherits from Observable for potential notifications.
 * Implements Cloneable for creating deep copies of player instances.
 */
public abstract class Player extends Observable implements Cloneable {

	// FIELDS
	private String nickname;
	private Card[] hand; 	// cards in the hand of the player

	
	
	// METHODS
	// Constructor
	public Player(String nickname, int cardsInHand) {
		this.nickname = nickname;
		this.hand = new Card[cardsInHand]; // Array of 10
	}



	/**
     * Checks equality between this Player and the given object.
     * Considers nickname and the hand array for comparison.
     */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Player player = (Player)o;
		
		return this.nickname == null ? (player.nickname == null ? true : false) : this.nickname.equals(player.nickname) &&
				this.hand == null ? (player.hand == null ? true : false) : this.hand.equals(player.hand);
	}
	
	/**
     * Generates a hash code using nickname and the hand array.
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = result * prime + (this.nickname == null ? 0 : this.nickname.hashCode());
		result = result * prime + (this.hand == null ? 0 : this.hand.hashCode());
		return result;
	}
	
	  /**
     * Creates a deep copy of the Player object.
     * Clones both the array of cards and each card within it.
     */
	@Override
	public Player clone() throws CloneNotSupportedException {
		// Calls the clone() method of the superclass
		Player clonedPlayer = (Player) super.clone();

		// Making the effective deep copy
		if (this.hand != null) {
			// 1. Clone the array of cards
			clonedPlayer.hand = this.hand.clone(); 
			// 2. Clone each card in the array
			for (int i = 0; i < this.hand.length; i++) {
				if (this.hand[i] != null) {
					clonedPlayer.hand[i] = (Card) this.hand[i].clone(); 
				}
			}
		}
		// 3. Returns the copy 
		return clonedPlayer;
	}

	
	/**
	 * This method check if all position in the array of cards are set with a Card.
	 */
	public boolean isPlayerHandSet() {
		return Stream.of(this.hand).allMatch(card -> card != null);
	}

	
	/**
	 * Returns the 'nickname' property of the Player.
	 */
	public String getNickname() {
		return this.nickname;
	}

	/**
	 * Returns the array of Card of this Player.
	 */
	public Card[] getHand() {
		return this.hand;
	}

	/**
	 * Get the card from the 'hand' property of the Player, at the specified index.
	 * If the index is not valid, it throws an  IndexOutOfBoundsException.
	 * Returns the Card.
	 */
	public Card getCardAt(int index) throws IndexOutOfBoundsException {
		if (index >= 0 && index < this.hand.length)
			return this.hand[index];
		else
			throw new IndexOutOfBoundsException();
	}

	/**
	 * Get the size of the cards in the hand of the Player.
	 * @return
	 */
	public int getHandSize() {
		return this.hand.length;
	}
	
	
	/**
	 * Sets the nickname of the player.
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
	/**
	 * Sets the card at the specified index in the player's hand.
	 * Throws IndexOutOfBoundsException if the index is not valid.
	 */
	public void setCardAt(int index, Card card) throws IndexOutOfBoundsException {
		if (index >= 0 && index < this.hand.length) {
			this.hand[index] = card;
		} else
			throw new IndexOutOfBoundsException();
	}
	
	/**
	 * Sets the entire hand of the player.
	 * If the provided hand is not null, updates the player's hand with it.
	 */
	public void setHand(Card[] hand) {
		if (hand != null)
			this.hand = hand;
	}

}
