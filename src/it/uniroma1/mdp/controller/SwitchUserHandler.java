package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import it.uniroma1.mdp.model.UserManager;
import it.uniroma1.mdp.view.utilities.ImageUtility;

/**
 * Handles the action event triggered by changing the user's nickname.
 */
public class SwitchUserHandler implements ActionListener {

	/**
	 * Displays a dialog box with a dropdown list of available users. Upon selecting
	 * a user, uploads information about the existing user and updates the view.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// playing the sound
		AudioManagerController.getEffectAudioManagerInstance().playClick();

		// 1. Saving available users & making an array of them
		List<String> listOfUsers = UserManager.getNicknames();
		String[] options = listOfUsers.toArray(new String[0]);

		JComboBox<String> nicknameComboBox = new JComboBox<>(options);

		int result = JOptionPane.showConfirmDialog(null, nicknameComboBox, "Choose a Nickname",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				ImageUtility.resizeIcon("images/gameSessionImages/happyB.png", 100, 100));

		if (result == JOptionPane.OK_OPTION) {
			String selectedNickname = (String) nicknameComboBox.getSelectedItem();
			// 1. Uploading informations about the existing user
			UserManager.uploadHumanUser(selectedNickname);
			// 2. Update the view
			updateUserProfilePanel();
		}
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
