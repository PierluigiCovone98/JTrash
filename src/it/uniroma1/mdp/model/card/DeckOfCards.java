package it.uniroma1.mdp.model.card;

import java.util.Observable;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Represents a deck of 52 cards, designed as a poker deck of cards. Extends
 * Observable to notify observers of changes in the deck.
 */
public class DeckOfCards extends Observable {

	// Constants
	public static final int MAX_NUMBER_OF_CARDS = 52;

	// Fields
	private Stack<Card> cards; // Useful because of the operation admitted on a LIFO data structure

	// Constructor
	public DeckOfCards() {
		this.cards = new Stack<Card>(); // Create an empty stack
	}

	/**
	 * Allows to look at the top card of this stack without removing it from the
	 * collection. Throws an EmptyDeckException if the deck of cards is empty.
	 */
	public Card peek() throws EmptyDeckException {
		if (!isEmpty()) {
			Card peekedCard = this.cards.peek();
			// notifyDeckChanged(peekedCard);
			return peekedCard;
		} else
			throw new EmptyDeckException();
	}

	/**
	 * Removes the card at the top of this stack and returns that card. Throws an
	 * EmptyDeckException if the deck of cards is empty.
	 */
	public Card pop() throws EmptyDeckException {
		if (!isEmpty()) {
			Card poppedCard = this.cards.pop();
			notifyDeckChanged(poppedCard);
			return poppedCard;
		} else
			throw new EmptyDeckException();
	}

	/**
	 * Pushes a card onto the top of this stack. Throws FullDeckException if the
	 * deck is full. Throws InvalidCardException if the argument passed is null or
	 * already present in the deck.
	 */
	public Card push(Card card) throws FullDeckException, InvalidCardException {
		// 1. Checks if the deck is full
		if (getSize() >= MAX_NUMBER_OF_CARDS)
			throw new FullDeckException("The deck is full");
		// 2. Otherwise:
		else if (!search(card)) { // The search() method eventually throws an InvalidCardException.
			this.cards.push(card);
			notifyDeckChanged(card); // not useful here
			return card;
		} 
		return card;
	}

	/**
	 * Returns true if the Card reference is already present in the deck; false
	 * otherwise. Throws InvalidCardException if the passed Card is null.
	 */
	public boolean search(Card card) throws InvalidCardException {
		if (card != null)
			return cards.stream().anyMatch(c -> c.toString().equals(card.toString())); // Returns true if there is a card with the same values
		else
			throw new InvalidCardException("Searching not existing Card");
	}

	/**
	 * Returns a string representation of the deck by joining each card's string
	 * representation with a newline.
	 */
	@Override
	public String toString() {
		return this.cards.stream().map(Card::toString).collect(Collectors.joining("\n"));
	}

	/**
	 * Notifies observers when the deck changes its state.
	 */
	private void notifyDeckChanged(Card poppedCard) {
		setChanged();
		notifyObservers(poppedCard.toString()); // I pass this instance as object to observers
	}

	/**
	 * Get the stack of cards.
	 * 
	 * @return
	 */
	protected Stack<Card> getCards() {
		return this.cards;
	}

	/**
	 * Returns the card at the specified position.
	 */
	public Card getCard(int index) throws ArrayIndexOutOfBoundsException {
		return this.cards.get(index);
	}

	/**
	 * Tests if this stack is empty.
	 */
	public boolean isEmpty() {
		return this.cards.empty();
	}

	/**
	 * Tests if this stack is full.
	 */
	public boolean isFull() {
		return this.cards.size() == MAX_NUMBER_OF_CARDS;
	}

	/**
	 * Returns the size of the stack.
	 */
	public int getSize() {
		return this.cards.size();
	}

	/**
	 * Set the given card at the specified index. Throws
	 * 'ArrayIndexOutOfBoundsException' if the index is not valid.
	 */
	public void setCard(int index, Card card) throws ArrayIndexOutOfBoundsException {
		this.cards.set(index, card);
		notifyDeckChanged(card);
	}
}
