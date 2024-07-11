package it.uniroma1.mdp.model;

public class InvalidNumberOfPlayersException extends RuntimeException {
	// Constants
	private static final long serialVersionUID = -2042139228396680332L;

	// Methods
	public InvalidNumberOfPlayersException(String message) {
		super(message);
	}
}
