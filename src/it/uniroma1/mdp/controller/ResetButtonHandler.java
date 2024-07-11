package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.uniroma1.mdp.model.UserManager;
import it.uniroma1.mdp.view.utilities.ImageUtility;

/**
 * Handles the action event triggered by clicking the reset button.
 */
public class ResetButtonHandler implements ActionListener {

	
	/**
	 * Displays a confirmation dialog asking the user if they really want to reset their statistics.
	 * If confirmed, resets the statistics of the current player and updates the user information.
	 * If canceled, re-enables the main menu frame.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// playing the sound
		AudioManagerController.getEffectAudioManagerInstance().playClick();
		
		// 1. Prompting the user if really wants to reset its statistics 
		int userChoise = JOptionPane.showConfirmDialog(JTrash.getMainFrameInstance(), // Parent Component
				"Reset your statistics? ", // Object (casted, to String)
				"Confirm Resetting...", // Title
				JOptionPane.YES_NO_OPTION, // optionType; shows just to option buttons
				JOptionPane.INFORMATION_MESSAGE, // messageType
				ImageUtility.resizeIcon("images/gameSessionImages/lose.png", 100, 100)
				);
		// 2. If yes, statistics of the current player are restored
		if (userChoise == JOptionPane.YES_OPTION) {
			UserManager.humanPlayer.resetGames();
			UserManager.updateUserInformations();
			// Must update the screen of the UserProfileMenu
			updateUserProfilePanel();
		}else
			JTrash.getMainFrameInstance().setEnabled(true); // Re-enable the main menu frame
	}

	/**
	 * Update the screen of the UserProfile menu.
	 */
	private void updateUserProfilePanel() {
		UserProfileButtonHandler userProfileListener = new UserProfileButtonHandler();
		ActionEvent updateUserProfileEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Update");
		userProfileListener.actionPerformed(updateUserProfileEvent);
	}
}
