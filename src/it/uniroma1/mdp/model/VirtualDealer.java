package it.uniroma1.mdp.model;

import it.uniroma1.mdp.model.card.Card;
import it.uniroma1.mdp.model.card.DeckOfCards;
import it.uniroma1.mdp.model.card.DiscardPile;
import it.uniroma1.mdp.model.card.StockPile;
import it.uniroma1.mdp.model.players.Player;

import java.util.Random;
import java.util.List;

/**
 * This class represents the Virtual Dealer in the card game. It is responsible
 * for: 1. Shuffling cards in each deck of cards. 2. Dealing the correct number
 * of cards to each player. 3. Assigning cards popped from the current StockPile
 * to players. 4. Resetting collections of both StockPiles & DiscardPiles. 5.
 * Popping cards from the given deck to prepare a player's hand. 6. Reshuffling
 * the DiscardPile and placing them as StockPiles.
 * 
 * It collaborates closely with the GameTable class to manage the game's
 * card-related aspects.
 */
public class VirtualDealer {

	// FIELDS
	private GameTable gameTable;

	// METHODS
	// Constructor
	public VirtualDealer(GameTable gameTable) {
		// Initialize fields
		this.gameTable = gameTable;
	}

	/**
	 * This method sets up the initial status of stock piles in the game table.
	 */
	protected void setupGameTable() {
		// 1. Shuffle Stock Piles
		shuffleStockPiles();
		// 2. Distribute cards to each player
		distributeCardsToPlayers();
	}

	/**
	 * This method shuffles all the stock piles in the game table.
	 */
	public void shuffleStockPiles() {
		this.gameTable.getStockPiles().stream().forEach(stockPile -> shuffleStockPile(stockPile));
		/*
		 * for (DeckOfCards stockPile : this.gameTable.getStockPiles())
		 * shuffleStockPile(stockPile);
		 */
	}

	/**
	 * This method shuffles a given stock pile.
	 * 
	 * @param stockPile The stock pile to be shuffled.
	 */
	public void shuffleStockPile(DeckOfCards stockPile) {
		// 1. Creating a reference to a Random object (at runtime).
		Random random = new Random();
		// 2. Saving the size of each deck of cards
		int deckSize = stockPile.getSize();

		for (int i = deckSize - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);
			// Swaps card in positions i and j
			Card tempCard = stockPile.getCard(i);
			stockPile.setCard(i, stockPile.getCard(j));
			stockPile.setCard(j, tempCard);
		}
	}

	/**
	 * This method is responsible for dealing cards to each player at the beginning
	 * of the match.
	 */

	private void distributeCardsToPlayers() {
		// 1. Saving the instance of the list of StockPile
		List<DeckOfCards> stockPiles = this.gameTable.getStockPiles();

		// 2. Saving players
		List<Player> players = this.gameTable.getPlayers();

		// 3. Distributing Cards
		for (DeckOfCards stockPile : stockPiles)
			if (!stockPile.isEmpty()) {
				// Having a stream of players
				players.stream().forEach(player -> player.setHand(preparePlayerHand(stockPile, 10)));

				if (players.stream().allMatch(player -> player.isPlayerHandSet()))
					break;
			}
	}

	/**
	 * This method assigns cards popped from the current StockPile to the given
	 * player.
	 */
	public void assignCardsToPlayer(Player player, int cards) {
		// Saving the current StockPile.
		DeckOfCards stockPile = this.gameTable.getCurrentStockPile();
		// Assigning to the player cards from the StockPile
		player.setHand(preparePlayerHand(stockPile, cards));
	}

	/**
	 * Reset collections of both StockPiles & DiscardPiles by creating a number of
	 * DeckOfCards that correspond to the current number of DeckOfCards, for each
	 * category of DeckOfCards.
	 */
	public void resetDeckOfCards() {
		// 1. Saving the current size of the number of Stock Piles
		int currentNumberOfDecks = this.gameTable.getStockPiles().size();

		// 2. Creating new instances of StockPiles and DiscardPiles
		for (int i = 0; i < currentNumberOfDecks; i++) {
			// 2.1 Assigning a stock pile to the correct ArrayList
			StockPile stockPile = new StockPile();
			this.gameTable.getStockPiles().set(i, stockPile);

			// 2.2 The same for the discard pile
			DiscardPile discardPile = new DiscardPile();
			this.gameTable.getDiscardPiles().set(i, discardPile);
		}

		// 3. Reset each index for deck of cards
		gameTable.resetStockPilesIndex();
		gameTable.resetDiscardPileIndex();

	} // end of the resetDeckOfCards method

	/**
	 * This method prepares a player's hand by popping cards from the given stock
	 * pile.
	 */
	public Card[] preparePlayerHand(DeckOfCards stockPile, int cards) {
		Card[] playerHand = new Card[cards];
		for (int i = 0; i < cards; i++)
			playerHand[i] = stockPile.pop();
		return playerHand;
	}

	/**
	 * This method reshuffles the DiscardPile and places them as StockPiles.
	 */
	public void reshuffleDiscardPile() {
		// 1. Saving the first card of the current DiscardPile
		Card topCard = this.gameTable.getCurrentDiscardPile().pop();

		// 2. Saving each instance of deck of cards
		int discarPileIndex = this.gameTable.getDiscardPileIndex();
		int stocPileIndex = 0;

		while (discarPileIndex >= 0 && stocPileIndex < this.gameTable.getStockPiles().size()) {
			DeckOfCards discardPile = this.gameTable.getCurrentDiscardPile();
			// Shuffling the deck of cards
			shuffleStockPile(discardPile);
			// Assigning it
			this.gameTable.getStockPiles().set(stocPileIndex, discardPile);
			this.gameTable.getDiscardPiles().set(discarPileIndex, new DiscardPile());
			//
			discarPileIndex--;
			stocPileIndex++;
		}

		// Resetting each index
		this.gameTable.resetStockPilesIndex();
		this.gameTable.resetDiscardPileIndex();

		// Pushing the popped card on the top
		this.gameTable.getCurrentDiscardPile().push(topCard);
	}

	/**
	 * Get the game table of the dealer.
	 */
	public GameTable getGameTable() {
		return this.gameTable;
	}
}