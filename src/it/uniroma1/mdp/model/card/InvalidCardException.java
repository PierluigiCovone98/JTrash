package it.uniroma1.mdp.model.card;

public class InvalidCardException extends NullPointerException {
	// Constants
	private static final long serialVersionUID = 7262874776414337844L;

	// Methods
	public InvalidCardException(String message) {
		super(message);
	}

}
