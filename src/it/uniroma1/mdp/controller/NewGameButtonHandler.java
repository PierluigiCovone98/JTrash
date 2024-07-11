package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.uniroma1.mdp.view.MenuManagerPanel;

/**
* ActionListener implementation for handling clicks on the "New Game" button from the MainMenuPanel.
* It switches the displayed card in the menu manager panel to the "newGameMenu" card.
*/
public class NewGameButtonHandler implements ActionListener {

	 /**
     * Handles the action event triggered by clicking the "New Game" button.
     * Retrieves the menu manager panel instance from the main frame and switches the displayed card.
     */
	@Override
	public void actionPerformed(ActionEvent e) {	
		// playing the sound
		AudioManagerController.getEffectAudioManagerInstance().playClick();
		
		// saving the instance of the menu manger 
		MenuManagerPanel menuManager = JTrash.getMainFrameInstance().getMenuManager();
		// changing the card shown
		menuManager.getLayout().show(menuManager, "newGameMenu");	
	}

}
