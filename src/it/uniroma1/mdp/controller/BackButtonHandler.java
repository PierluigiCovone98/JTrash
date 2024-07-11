package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.uniroma1.mdp.view.MenuManagerPanel;

/**
 * Handles the action event triggered by clicking the back button.
 */
public class BackButtonHandler implements ActionListener {

	/**
	 * Changes the card shown in the menu manager panel to display the main menu.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// playing the sound
		AudioManagerController.getEffectAudioManagerInstance().playClick();
		
		// saving the instance of the menu manger
		MenuManagerPanel menuManager = JTrash.getMainFrameInstance().getMenuManager();
		// changing the card shown
		menuManager.getLayout().show(menuManager, "mainMenu");
	}

}
