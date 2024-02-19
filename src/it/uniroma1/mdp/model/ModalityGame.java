package it.uniroma1.mdp.model;

/**
 * Enum representing the available modalities for the card game.
 * It includes Single Game and Full Game modalities.
 */
public enum ModalityGame {
	SINGLE_GAME("Single Game"),
	FULL_GAME("Full Game");
	
	// FIELDS
	private String modalityGameLabel;

	// METHODS
	// Constructor
	private ModalityGame(String modalityGameLabel) {
		this.modalityGameLabel = modalityGameLabel;
	}
	
	/**
	 * Get the string value for the ModalityGame instance.
	 * @return
	 */
	public String getValue() {
		return this.modalityGameLabel;
	}
}
