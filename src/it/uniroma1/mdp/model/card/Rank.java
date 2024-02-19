package it.uniroma1.mdp.model.card;

/**
 * Enumeration representing possible card ranks.
 * Each rank has a label and an integer value associated with it.
 * For Queen and King, the integer value is set to -1 as they are considered unplayable cards.
 * @author pierluigicovone
 */
public enum Rank {
	
	ACE("Ace",1),
	TWO("2",2),
	THREE("3",3),
	FOUR("4",4),
	FIVE("5",5),
	SIX("6",6),
	SEVEN("7",7),
	EIGHT("8",8),
	NINE("9",9),
	TEN("10",10),
	JACK("Jack",11),
	QUEEN("Queen",-1),	// Queen is an unplayable card; I decided it has the 0 integer value
	KING("King",-1);		// King is an unplayable card; I decided it has the 0 integer value
	
	
	// FIELDS
	private final String rankLabel;
	private final int rankValue;
	
	// METHODS
	
	 // Constructor
	private Rank(String rankLabel, int rankValue) {
		this.rankLabel = rankLabel;
		this.rankValue = rankValue;
	}

	/**
     * Returns the label of the rank.
     */	
	public String getRankLabel() {
		return this.rankLabel;
	}
	
	/**
     * Returns the integer value associated with the rank.
     */
	public int getRankValue() {
		return this.rankValue;
	}
}
