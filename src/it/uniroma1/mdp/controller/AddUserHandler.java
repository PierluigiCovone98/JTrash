package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.uniroma1.mdp.model.UserManager;
import it.uniroma1.mdp.view.EffectAudioManager;

/**
 * Handles the action event triggered by pressing the add user button.
 */
public class AddUserHandler implements ActionListener {

	/**
	 * Displays an input dialog to add a new user. If a valid nickname is provided,
	 * it creates a new user and updates the view.
	 * @param e The action event.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// playing the sound
		EffectAudioManager effectAudio = AudioManagerController.getEffectAudioManagerInstance();
		effectAudio.playClick();

		String nickname = null;

		// 1. Until the user does not insert a valid nickname it continue asking for a
		// valid one
		while (nickname == null) {
			// Play the audio effect
			nickname = JOptionPane.showInputDialog(null, "Add a new user:", "Insert Nickname",
					JOptionPane.QUESTION_MESSAGE);

			// 1.1 If the user press the "cancel" button
			if (nickname == null) {
				return;
			}
			
			// 1.1 If the user press the "cancel" button
			
			if (nickname.isEmpty()) {
				effectAudio.playError();
				JOptionPane.showMessageDialog(null, "Nickname can't be empty.", "Errore",
				JOptionPane.ERROR_MESSAGE);
				// Continue asking for the nickname
				nickname = null;
			} else if (UserManager.isTheNicknameRegistered(nickname.toLowerCase())) {
				effectAudio.playError();
				JOptionPane.showMessageDialog(null, "The user already exists.", "Errore",
				JOptionPane.ERROR_MESSAGE);
				// Continue asking for the nickname
				nickname = null;
			}
		} // end of the loop statement

		// 2. After a nickname is given, invoking this method starts the branch that
		// creates a new user
		UserManager.manageUser(nickname);
		// 3. Update the view
		updateUserProfilePanel();
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
