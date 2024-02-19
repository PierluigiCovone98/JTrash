package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import it.uniroma1.mdp.model.Game;
import it.uniroma1.mdp.view.NewGameMenuPanel;

/**
 * Handles the action event triggered by clicking the play game button.
 */
public class PlayGameButtonHandler implements ActionListener {

	/**
	 * Performs the necessary actions when the play game button is clicked.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// playing the sound
		AudioManagerController.getMusicAudioManagerInstance().playJazzLoop();
		
				
		// 1. Saving the instance of NewGameMenuPanel
		NewGameMenuPanel newGameMenu = JTrash.getMainFrameInstance().getMenuManager().getNewGameMenu();

		// 2. Saving options selected from the user
		String modalityGame = (String) newGameMenu.getGameMode().getComboBox().getSelectedItem();
		int numberOfPlayers = (int) newGameMenu.getNumberOfPlayers().getComboBox().getSelectedItem();

		// 3. CREATING THE UNIQUE INSTANCE OF THE GAME CLASS, from the model
		JTrash.initializeGameInstance(modalityGame, numberOfPlayers);
		Game gameInstance = JTrash.getGameInstance();		
		
		// 4. Adding the instance of GameTablePanel as observer of the game table, from
		// the view
		gameInstance.getGameTable().addObserver(JTrash.getGameFrameInstance().getGameTable());

		// 5. Setting the game & Updating the view
		gameInstance.initilizeGame();

		// 6. Invoking an handler that manages turns between players
		TurnsHandler turnsHandler = new TurnsHandler();
		ActionEvent nextTurnEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Starts The Turn");
		turnsHandler.actionPerformed(nextTurnEvent);
	}

}
