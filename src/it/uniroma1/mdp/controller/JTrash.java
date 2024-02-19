package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;

import it.uniroma1.mdp.model.Game;
import it.uniroma1.mdp.model.SimpleGameFactory;
import it.uniroma1.mdp.view.MainFrame;
import it.uniroma1.mdp.view.game.GameFrame;

/**
 * The entry point for the application. Manages initialization and access to
 * main instances of the model and view. Supports the singleton pattern for
 * instances of both the main instance of the model and main instances of the
 * view. Acts as a controller to handle user interactions and manage the flow of
 * the application.
 */
public class JTrash {

	// MODEL ---
	/**
	 * The main instance of the game model.
	 */
	private static Game gameInstance;

	/**
	 * Initializes the main instance of the game model.
	 */
	public static void initializeGameInstance(String modalityGame, int numberOfPlayers) {
		if (gameInstance == null) {
			gameInstance = SimpleGameFactory.createGame(modalityGame, numberOfPlayers);
		} else {
			throw new IllegalStateException("Game instance is already initialized.");
		}
	}

	/**
	 * Retrieves the main instance of the game model.
	 */
	public static Game getGameInstance() {
		if (gameInstance == null) {
			throw new IllegalStateException("Game instance is not initialized yet.");
		}
		return gameInstance;
	}

	/**
	 * Sets the main instance of the game model to null.
	 */
	public static void setToNullTheGameInstance() {
		gameInstance = null;
	}

	// VIEW ---
	/**
	 * The main instance of the application's main frame.
	 */
	private static MainFrame mainFrameInstance;

	/**
	 * Retrieves the main instance of the application's main frame, creating it if
	 * necessary.
	 */
	public static MainFrame getMainFrameInstance() {
		if (mainFrameInstance == null)
			mainFrameInstance = new MainFrame();
		return mainFrameInstance;
	}

	/**
	 * The main instance of the game frame.
	 */
	private static GameFrame gameFrameInstance;

	/**
	 * Retrieves the main instance of the game frame, creating it if necessary.
	 */
	public static GameFrame getGameFrameInstance() {
		if (gameFrameInstance == null)
			gameFrameInstance = new GameFrame();
		return gameFrameInstance;
	}

	/**
	 * Sets the main instance of the game frame to null.
	 */
	public static void setToNullTheGameFrameInstance() {
		gameFrameInstance = null;
	}

	/**
	 * Prompts the user for their nickname.
	 */
	private static void askForNickname() {
		UserHandler userController = new UserHandler();
		ActionEvent askForNicknameEvent = new ActionEvent(getMainFrameInstance(), ActionEvent.ACTION_PERFORMED,
				"Ask for Nickname");
		userController.actionPerformed(askForNicknameEvent);
	}
	
	/**
	 * Initializes the sound controller, ensuring singleton instances of AudioManager subclasses are created.
	 */
	private static void initializeSoundController() {
		// Getting instances of the AudioManagerController
		AudioManagerController.getEffectAudioManagerInstance();
		AudioManagerController.getMusicAudioManagerInstance();
	}

	/**
	 * The entry point for the application. Initializes the main frame, sets up its
	 * UI, and makes it visible. Prompts the user for their nickname.
	 * 
	 * @param args Command-line arguments (not used).
	 */
	public static void main(String args[]) {
		// 1. Creating the principle frame instance
		getMainFrameInstance();

		// 2. Setting the UI of the frame
		mainFrameInstance.setupUI();

		// 3. Making it visible
		mainFrameInstance.setVisible(true);

		// 4. Asks for the nickname
		askForNickname();
	
		// 5. Initialize the sound controller
		initializeSoundController();
	}

}
