package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.uniroma1.mdp.view.MenuManagerPanel;

/**
 * The SettingsButtonHandler class implements the ActionListener interface to handle events
 * triggered by the SettingsButton.
 * It manages the actions to be performed when the settings button is clicked, such as playing a sound
 * and displaying the settings menu.
 */
public class SettingsButtonHandler implements ActionListener {

	  /**
     * Handles the actionPerformed event triggered by the SettingsButton.
     * This method is called when the settings button is clicked. 
     * It switches the view to the settings menu panel.
     */
	@Override
	public void actionPerformed(ActionEvent e) {	
		// playing the sound
		AudioManagerController.getEffectAudioManagerInstance().playClick();
		
		// saving the instance of the menu manger 
		MenuManagerPanel menuManager = JTrash.getMainFrameInstance().getMenuManager();
		// changing the card shown
		menuManager.getLayout().show(menuManager, "settingsMenu");	
	}

}
