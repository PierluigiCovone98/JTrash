 package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.uniroma1.mdp.model.UserManager;
import it.uniroma1.mdp.view.game.GameFrame;
import it.uniroma1.mdp.view.utilities.ImageUtility;

/**
 * Handles the action event triggered the click of the remove user button.
 */

public class RemoveUserHandler implements ActionListener {

	/**
	 * Displays a confirmation dialog to remove the current user. If confirmed,
	 * removes the user, deletes the associated CSV file, asks for a new user, and
	 * updates the view.
	 * 
	 * @param e The action event.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// playing the sound
		AudioManagerController.getEffectAudioManagerInstance().playClick();

		GameFrame playFrame = JTrash.getGameFrameInstance();

		// Run the dialog in the Event Dispatch Thread
		int userChoice = JOptionPane.showConfirmDialog(null, "Do you really want remove this user?", "Delete User",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
				ImageUtility.resizeIcon("images/gameSessionImages/lose.png", 100, 100));

		if (userChoice == JOptionPane.YES_OPTION) {
			// 1. Removing the current user
			UserManager.deleteNickname(UserManager.humanPlayer.getNickname());
			// 2. Remove the .csv file
			UserManager.removeHumanUser(UserManager.humanPlayer.getNickname());
			// 3. Asking for a new user
			askForAUser();
			
			UserManager.manageUser(UserManager.humanPlayer.getNickname());
			// 4. Updating the view
			updateUserProfilePanel();

		} else
			playFrame.setEnabled(true);

	}

	/**
	 * Initiates the process of asking for a new user.
	 */
	private void askForAUser() {
		UserHandler userHandler = new UserHandler();
		ActionEvent askUserEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Ask for User");
		userHandler.actionPerformed(askUserEvent);
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
