package it.uniroma1.mdp.model.card;

/**
 * Represents a stockpile from which players must draw a card during a match.
 * Extends the DeckOfCards class to inherit card-related functionalities.
 */
public class StockPile extends DeckOfCards {
	// Fields
	
	
	// Methods
	public StockPile() {
		super();
		initializeDeckOfCards();
	}

	/**
	 * This method makes the effective initialization of each card in the stack.
	 * It uses the for-each loop statement over lists of values of enums, returned
	 * by the call to the "values()" method .
	 */
	private void initializeDeckOfCards() {
		for (Suite suite : Suite.values())
			for (Rank rank : Rank.values())
				getCards().add( new Card(rank, suite) );
	}
}
