package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.uniroma1.mdp.model.UserManager;
import it.uniroma1.mdp.view.EffectAudioManager;

/**
 * Handles the action event triggered by clicking the reset button.
 */
public class ChangeNicknameHandler implements ActionListener {

	/**
	 * Displays a confirmation dialog asking the user if they really want to reset
	 * their statistics. If confirmed, resets the statistics of the current player
	 * and updates the user information. If canceled, re-enables the main menu
	 * frame.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// playing the sound
		EffectAudioManager effectAudio = AudioManagerController.getEffectAudioManagerInstance();
		effectAudio.playClick();
				
		
		String oldNickname = UserManager.humanPlayer.getNickname();
		String newNickname = null;
		// 1. Until the user does not insert a valid nickname it continue asking for a
		// valid one
		while (newNickname == null) {
			newNickname = JOptionPane.showInputDialog(null, "New Nickname", "New Nickname",
					JOptionPane.QUESTION_MESSAGE);

			// 1.1 Cancel button has been pressed
			if (newNickname == null) {
				return;
			}

			if (newNickname.isEmpty()) {
				effectAudio.playError();
				JOptionPane.showMessageDialog(null, "Nickname can't be empty.", "Errore", JOptionPane.ERROR_MESSAGE);
				// Continue asking for the nickname
				newNickname = null;
			} else if (oldNickname.equals(newNickname.toLowerCase())) {
				effectAudio.playError();
				JOptionPane.showMessageDialog(null, "Inserted nickname is the same\nof the previous one.", "Errore",
						JOptionPane.ERROR_MESSAGE);
				// Continue asking for the nickname
				newNickname = null;
			} else if (UserManager.isTheNicknameRegistered(newNickname.toLowerCase())) {
				effectAudio.playError();
				JOptionPane.showMessageDialog(null, "Nickname already exists", "Errore", JOptionPane.ERROR_MESSAGE);
				// Continue asking for the nickname
				newNickname = null;
			}
		}

		// 2. Making it to lower case
		newNickname = newNickname.toLowerCase();

		// 3. After the new nickname has been inserted, updating it
		UserManager.humanPlayer.setNickname(newNickname);

		// 4. Switching nickname into the list of nicknames
		UserManager.switchNicknamesInTheList(oldNickname, newNickname);
		// 5. Removing the old .csv file
		UserManager.removeHumanUser(oldNickname);
		// 6. Creating a .csv file with new informations
		UserManager.registerHumanUser(newNickname);

		// 7. Updating the view
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
