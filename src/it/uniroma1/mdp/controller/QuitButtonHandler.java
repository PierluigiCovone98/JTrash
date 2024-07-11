package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.uniroma1.mdp.view.EffectAudioManager;
import it.uniroma1.mdp.view.utilities.ImageUtility;

/**
 * Handles actions triggered by clicking the "Quit" button in the menu.
 * Displays a confirmation dialog to confirm quitting the game.
 */
public class QuitButtonHandler implements ActionListener {
	
	
	/**
	 * Handles the action event triggered by clicking the "Quit" button from the
	 * MainMenuPanel. Displays a confirmation dialog to confirm quitting the game.
	 * If the user confirms, the application is terminated.
	 */
	@Override 
	public void actionPerformed(ActionEvent e) {
		// playing the sound
		EffectAudioManager effectAudio = AudioManagerController.getEffectAudioManagerInstance();
		effectAudio.playClick();
		
		// 1. Using the JOptionPane static method "showConfirmDialog()" to save the user's chose.
		int userChoise = JOptionPane.showConfirmDialog(JTrash.getMainFrameInstance(), // Parent Component
				"Do you really want to quit the game? ", // Object (casted, to String)
				"Confirm Quitting...", // Title
				JOptionPane.YES_NO_OPTION, // optionType; shows just to option buttons
				JOptionPane.INFORMATION_MESSAGE, // messageType
				ImageUtility.resizeIcon("images/cardsSprites/2 Spades.png", 70, 100)
				);
		/* Pressed yes, it terminates the application... */
		if (userChoise == JOptionPane.YES_OPTION) {
			effectAudio.playClick();
			System.exit(0);
		}
		else
			JTrash.getMainFrameInstance().setEnabled(true); // Re-enable the main menu frame
	}
}
