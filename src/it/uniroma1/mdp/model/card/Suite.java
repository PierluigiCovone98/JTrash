package it.uniroma1.mdp.model.card;

/**
 * Enumeration representing possible card suites. Each suite has a label and a
 * color associated with it.
 */
public enum Suite {
	HEARTS("Hearts", SuiteColor.RED), // Cuori
	DIAMONDS("Diamonds", SuiteColor.RED), // Quadri
	CLUBS("Clubs", SuiteColor.BLACK), // Fiori
	SPADES("Spades", SuiteColor.BLACK); // Picche

	// FIELDS (Constants)
	private final String suiteLabel;
	private final SuiteColor suiteColor;

	// METHODS
	/**
	 * Constructor
	 */
	private Suite(String suiteLabel, SuiteColor suiteColor) {
		this.suiteLabel = suiteLabel;
		this.suiteColor = suiteColor;
	}

	/**
	 * Returns the label of the suite.
	 */
	public String getSuiteLabel() {
		return this.suiteLabel;
	}

	/**
	 * Returns the color of the suite.
	 */
	public SuiteColor getSuiteColor() {
		return this.suiteColor;
	}
}
