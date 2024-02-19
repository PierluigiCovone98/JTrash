package it.uniroma1.mdp.model;

import it.uniroma1.mdp.model.players.Player;

/**
 * Represents a full game mode, consisting of multiple matches. Extends the
 * generic Game class and is observable.
 */
public class FullGame extends Game {

	// Constructor
	public FullGame(int numberOfPlayers) {
		super(numberOfPlayers);
	}

	/**
	 * Overrides the setNumberOfPlayers method from the base class. Calls the super
	 * method to handle the number of players.
	 */
	@Override
	public void setNumberOfPlayers(int numberOfPlayers) {
		super.setNumberOfPlayers(numberOfPlayers);
	}

	/**
	 * Initialize settings to allow to play the next match in this game.
	 */
	public void nextMatch(Player winner) {
		// -1. Saving the current number of cards
		int currentNumberOfCards = winner.getHandSize();

		// 0. Saving fields for seek of simplicity
		VirtualDealer virtualDealer = getGameTable().getVirtualDealer();

		// 1. Restore each DeckOfCards (Both: StockPiles & DiscardPiles)
		virtualDealer.resetDeckOfCards();

		// 2. Shuffle StockPiles
		virtualDealer.shuffleStockPiles();

		// 3. Assigning cards to each player, except for the winner of previous match
		for (Player player : getGameTable().getPlayers()) {
			// 3.1 If the player is not the winner
			if (!player.equals(winner)) {
				int cardsPlayer = player.getHandSize();
				virtualDealer.assignCardsToPlayer(player, cardsPlayer);
				System.out.println(player.getHandSize());
			} else if (player.equals(winner)) {
				int numberCardsWinner = currentNumberOfCards - 1;
				virtualDealer.assignCardsToPlayer(player, numberCardsWinner);
				System.out.println(player.getHandSize());
			}
		} // end of the for-loop statement

		// 4. Resetting each reference for cards
		getGameTable().poppedCardToNull();
		getGameTable().selectedCardToNull();
		getGameTable().discardedCardToNull();

		// 5. Reset the index of the next player to play
		getGameStatusManager().resetCurrentPlayerIndex(); // currentPlayerIndex = 0

		// 6. Notify observers that the instance of the gameTable has been changed
		// Makes a correspondence between the number of players in the model & in the
		// view
		// getGameTable().notifyGameTableChanges( getNumberOfPlayers());
	}
}
