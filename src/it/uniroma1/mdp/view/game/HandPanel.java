package it.uniroma1.mdp.view.game;

import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * Abstract class representing a panel to display a player's hand of cards.
 * Extends JPanel and provides a common interface for subclasses to implement.
 */
public abstract class HandPanel extends JPanel {

	// CONSTANTS
	public static final long serialVersionUID = -4142002730535943533L;
	public static final int MAX_CARDS_IN_HAND = 10;

	// FIEL
	private int cardsInHand; // The size of the array of cards
	private CardButton[] cards; // The actual array of cards

	// Methods
	// Constructors
	public HandPanel(GridLayout gridLayout) {
		this(gridLayout, MAX_CARDS_IN_HAND);
	}

	public HandPanel(GridLayout gridLayout, int cardsInHand) {
		super(gridLayout); // GridLayout changed for vertical orientation

		// 1. Initializing fields
		this.cardsInHand = cardsInHand;
		this.cards = new CardButton[this.cardsInHand]; // Empty array of null instances
	}

	/**
	 * This method is implemented by subclasses of the hand panel; they decide if
	 * the card orientation must be vertical or horizontal.
	 */
	public abstract void setupUI();

	/**
	 * Restores the hand panel with a new number of cards. The method is currently
	 * not used because it was thought for the FullGame mode.
	 */
	public void restore(int numberOfCards) {
		removeAll();

		// 1. Setting the new number of cards & the related array of CardButton
		this.cardsInHand = numberOfCards;
		this.cards = new CardButton[this.cardsInHand];

		revalidate();
		repaint();
		// 2. Setting it up: this calls the specification
		// setupUI();

	}

	/**
	 * Gets the current number of cards in the hand.
	 */
	public int getCardsInHand() {
		return cardsInHand;
	}

	/**
	 * Gets the array of CardButtons representing the cards in the hand.
	 */
	public CardButton[] getCards() {
		return this.cards;
	}

	/**
	 * Gets a specific CardButton at the given index in the hand.
	 */
	public CardButton getCard(int index) {
		return this.cards[index];
	}

	/**
	 * Sets a CardButton at the specified index in the hand.
	 */
	public void setCardAt(int index, CardButton cardB) {
		this.cards[index] = cardB;
	}

	/**
	 * Modify the number of cards in hand.
	 */
	public void setCardsInHand(int cardsInHand) {
		this.cardsInHand = cardsInHand;
	}

	/**
	 * Allows to modify the array of CardButton.
	 */
	public void setCards(int numberOfCards) {
		this.cards = new CardButton[numberOfCards];
	}

}
