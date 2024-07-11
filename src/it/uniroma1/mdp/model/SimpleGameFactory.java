package it.uniroma1.mdp.model;

/**
 * SimpleGameFactory is a factory class that follows the Simple Factory design
 * pattern. Its purpose is to abstract the creation logic of Game objects. This
 * class offers a static method to create instances of different Game subclasses
 * based on the game mode and number of players. It simplifies object creation
 * and centralizes the instantiation logic of Game objects, promoting code reuse
 * and separation of concerns.
 */
public class SimpleGameFactory {

	/**
	 * Creates an instance of Game based on the specified game mode and number of
	 * players. If the game mode matches SINGLE_GAME, returns an instance of
	 * SingleGame. Otherwise, returns an instance of FullGame.
	 * 
	 * @param modalityGame    The game mode, used to determine the type of Game
	 *                        object to create.
	 * @param numberOfPlayers The number of players for the game.
	 * @return A new instance of Game, specific to the provided mode and number of
	 *         players.
	 */
	public static Game createGame(String modalityGame, int numberOfPlayers) {
		return modalityGame.equals(ModalityGame.SINGLE_GAME.getValue()) ? new SingleGame(numberOfPlayers)
				: new FullGame(numberOfPlayers);
	}
}
