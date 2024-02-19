package it.uniroma1.mdp.model.card;

import java.util.EmptyStackException;

public class EmptyDeckException extends EmptyStackException {
	// Constants
	private static final long serialVersionUID = 2559322963267253801L;

	// Methods
	public EmptyDeckException() {
		super();
	}

	/**
	 * This method return the error message.
	 */
	public String getMessage() {
		return "The deck is empty.";
	}
}
