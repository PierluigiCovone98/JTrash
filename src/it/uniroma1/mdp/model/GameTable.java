package it.uniroma1.mdp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import it.uniroma1.mdp.model.card.Card;
import it.uniroma1.mdp.model.card.DeckOfCards;
import it.uniroma1.mdp.model.card.DiscardPile;

import it.uniroma1.mdp.model.card.InvalidCardException;
import it.uniroma1.mdp.model.card.StockPile;
import it.uniroma1.mdp.model.players.ComputerPlayer;
import it.uniroma1.mdp.model.players.Player;

/**
 * Represents the game table, which includes stock piles, discard piles,
 * players, and cards. It manages the state of the game, dealing with
 * initialization, player actions, and card handling. This class is observable
 * to notify changes to its observers.
 */
public class GameTable extends Observable {
	// FIELDS
	private List<DeckOfCards> stockPiles; // If players are 3 or 4, we need two stock piles
	private List<DeckOfCards> discardPiles; // The same for the discard pile
	private int stockPilesIndex; // 0 as default
	private int discardPilesIndex; // 0 as default

	private List<Player> players;

	private Card poppedCard;
	private Card discardedCard;
	private Card selectedCard;

	private VirtualDealer virtualDealer;

	// METHODS

	// Constructor
	public GameTable() {
		// Initializing decks of cards
		this.stockPiles = new ArrayList<DeckOfCards>(); // initial capacity of 10
		this.discardPiles = new ArrayList<DeckOfCards>(); // initial capacity of 10

		// Initializing players
		this.players = new ArrayList<Player>(); // Empty list of Player elements
		this.players.add(UserManager.humanPlayer); // making the first one as Human

		// Initializing cards
		this.poppedCard = null; // Redundant; is the default value */
		this.discardedCard = null;
		this.selectedCard = null;

		// Virtual dealer
		this.virtualDealer = new VirtualDealer(this);
	}

	/**
	 * Initializes the game table with stock piles, discard piles, and players based
	 * on the number of players. Also, sets up the initial state of the game,
	 * including dealing cards to players.
	 */
	protected void initializeGameTable(int numberOfPlayers) {
		switch (numberOfPlayers) {
		case 3, 4 -> {
			// Two decks of cards
			this.stockPiles.add(new StockPile());
			this.stockPiles.add(new StockPile());

			this.discardPiles.add(new DiscardPile());
			this.discardPiles.add(new DiscardPile());
		}
		default -> {
			// One deck of cards
			this.stockPiles.add(new StockPile());
			this.discardPiles.add(new DiscardPile());
		}
		}
		// Adding Computer Players
		addComputerPlayers(numberOfPlayers, 10);
		// Shuffling deck of cards and dealing cards to each player
		this.virtualDealer.setupGameTable();
		// Notify observers of changes
		setChanged();
		notifyObservers(numberOfPlayers); // In this way I say to observers how many players there are
	}

	/**
	 * Adds computer players to the table based on the specified count.
	 */
	private void addComputerPlayers(int computerPlayers, int cardsInHand) {
		for (int i = 1; i < computerPlayers; i++) {
			ComputerPlayer computerPlayer = new ComputerPlayer("AI " + i, cardsInHand);
			this.players.add(computerPlayer);
		}
	}

	/**
	 * Get the list of StockPiles.
	 */
	public List<DeckOfCards> getStockPiles() {
		return this.stockPiles;
	}

	/**
	 * Get the list of DiscardPiles.
	 */
	public List<DeckOfCards> getDiscardPiles() {
		return this.discardPiles;
	}

	/**
	 * Get the current stock pile.
	 */
	public DeckOfCards getCurrentStockPile() throws IndexOutOfBoundsException {
		while (this.stockPilesIndex < stockPiles.size()) {
			DeckOfCards stockPile = stockPiles.get(this.stockPilesIndex);

			if (!stockPile.isEmpty())
				return stockPile;
			else
				this.stockPilesIndex++;
		} // end of the while loop
		throw new IndexOutOfBoundsException();
	}

	/**
	 * Get the current discard pile.
	 */
	public DeckOfCards getCurrentDiscardPile() throws IndexOutOfBoundsException {
		while (this.stockPilesIndex < stockPiles.size()) {
			DeckOfCards discardPile = discardPiles.get(this.discardPilesIndex);

			if (!discardPile.isFull())
				return discardPile;
			else
				this.discardPilesIndex++;
		}
		// If no deck are more available
		throw new IndexOutOfBoundsException();
	}

	/**
	 * Get the list of players.
	 * 
	 * @return
	 */
	public List<Player> getPlayers() {
		return this.players;
	}

	/**
	 * Get the specified player.
	 */
	public Player getPlayer(int index) throws IndexOutOfBoundsException {
		if (index >= 0 && index < this.players.size()) {
			return this.players.get(index);
		} else
			throw new IndexOutOfBoundsException();
	}

	/**
	 * Get the popped card.
	 */
	public Card getPoppedCard() {
		return this.poppedCard;
	}

	/**
	 * Get the popped card.
	 */
	public Card getDiscardedCard() {
		return this.discardedCard;
	}

	/**
	 * Get the selected card.
	 */
	public Card getSelectedCard() {
		return this.selectedCard;
	}

	/**
	 * Get the virtual dealer for this game.
	 */
	public VirtualDealer getVirtualDealer() {
		return this.virtualDealer;
	}

	/**
	 * Sets the popped card in the game table and notifies observers about the
	 * change. Throws an InvalidCardException if the provided popped card is not
	 * valid.
	 */
	public void setPoppedCard(Card poppedCard) throws InvalidCardException {
		if (poppedCard != null) {
			this.poppedCard = poppedCard;
			// Notify observers in a custom way
			setChanged();
			notifyObservers(); // I can't pass a Card reference to an instance of the View
		} else
			throw new InvalidCardException("Popped card is not valid");
	}

	/**
	 * Sets the discarded card in the game table and notifies observers about the
	 * change. Throws an InvalidCardException if the provided discarded card is not
	 * valid.
	 */
	public void setDiscardedCard(Card discardedCard) throws InvalidCardException {
		if (discardedCard != null) {
			this.discardedCard = discardedCard;
			// Notify observers
			setChanged();
			notifyObservers(this.discardedCard.toString());
		} else
			throw new InvalidCardException("Discarded card is not valid");
	}

	/**
	 * Sets the selected card in the game table and notifies observers about the
	 * change. Throws an InvalidCardException if the provided selected card is not
	 * valid.
	 */
	public void setSelectedCard(Card selectedCard) throws InvalidCardException {
		if (selectedCard != null) {
			this.selectedCard = selectedCard;
			// Notify observers
			setChanged();
			notifyObservers(this.selectedCard.toString());
		} else
			throw new InvalidCardException("Selected card is not valid");
	}

	/**
	 * This method is used to make the poppedCard reference to null
	 */
	public void poppedCardToNull() {
		this.poppedCard = null;
	}

	/**
	 * This method is used to make the selectedCard reference to null
	 */
	public void selectedCardToNull() {
		this.selectedCard = null;
	}

	/**
	 * This method is used to make the discardedCard reference to null
	 */
	public void discardedCardToNull() {
		this.discardedCard = null;
	}

	/**
	 * Resets the index of the current stock pile, allowing iteration over stock
	 * piles.
	 */
	protected void resetStockPilesIndex() {
		this.stockPilesIndex = 0;
	}

	/**
	 * Resets the index of the current discard pile, allowing iteration over stock
	 * piles.
	 */
	protected void resetDiscardPileIndex() {
		this.discardPilesIndex = 0;
	}
	
	/**
	 * Get the index of which stock pile of the list is currently used.
	 */
	protected int getStockPileIndex() {
		return this.stockPilesIndex;
	}
	
	/**
	 * Get the index of which discard pile of the list is currently used.
	 */
	protected int getDiscardPileIndex() {
		return this.discardPilesIndex;
	}

}
