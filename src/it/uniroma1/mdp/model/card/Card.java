package it.uniroma1.mdp.model.card;

public class Card implements Cloneable {

	// FIELDS
	private final Rank rank;
	private final Suite suite;

	// METHODS
	/**
	 * Constructor of a Card instance: are requires an instance of Rank and Suite
	 */
	public Card(Rank rank, Suite suite) {
		this.rank = rank;
		this.suite = suite;
	}

	/**
	 * Checks equality, including null and class conditions. Compares 'rank' and
	 * 'suite' properties, handling nulls. Returns true if both properties are null
	 * or equal, else false.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Card card = (Card) o;
		return this.rank == null ? (card.rank == null ? true : false)
				: this.rank.equals(card.rank) && this.suite == null ? (card.suite == null ? true : false)
						: this.suite.equals(card.suite);
	}

	/**
	 * Generates a hash code using 'rank' and 'suite' properties. Handles null
	 * values to prevent potential issues.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = result * prime + (this.rank == null ? 0 : this.rank.hashCode());
		result = result * prime + (this.suite == null ? 0 : this.suite.hashCode());
		return result;
	}

	/**
	 * Creates and returns a clone of the Card object. The returned instance is a
	 * deep copy.
	 */
	@Override
	public Card clone() {
		return new Card(this.rank, this.suite);
	}

	/**
	 * Returns a string representation of the Card object.
	 */
	@Override
	public String toString() {
		return this.rank.getRankLabel() + " " + this.suite.getSuiteLabel();
	}

	/**
	 * Returns the 'rank' property of the Card.
	 */
	public Rank getRank() {
		return this.rank;
	}

	/**
	 * Returns the 'suite' property of the Card.
	 */
	public Suite getSuite() {
		return this.suite;
	}
}
