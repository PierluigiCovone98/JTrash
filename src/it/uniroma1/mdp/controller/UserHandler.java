package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.uniroma1.mdp.model.UserManager;
import it.uniroma1.mdp.view.EffectAudioManager;

/**
 * The handler designed to manage the creation (eventualy) of users.
 */
public class UserHandler implements ActionListener {

	/**
	 * Handles the action event triggered by user actions. Prompts the user to input
	 * a nickname and creates a player instance with the provided nickname.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Saving the reference for the sound
		EffectAudioManager effectAudio = AudioManagerController.getEffectAudioManagerInstance();
		
		
		String nickname = null;
		// 1. Until the user does not insert a valid nickname it continue asking for a
		// valid one
		while (nickname == null) {

			nickname = JOptionPane.showInputDialog(null, "Insert your nickname:", "Insert Nickname",
					JOptionPane.QUESTION_MESSAGE);

			if (nickname != null && nickname.isEmpty()) {
				effectAudio.playError();
				JOptionPane.showMessageDialog(null, "Nickname can't be empty.", "Errore", JOptionPane.ERROR_MESSAGE);
				// Continue asking for the nickname
				nickname = null;
			} 
				
		}
		AudioManagerController.getEffectAudioManagerInstance().playClick();
		

		// 2. After a nickname is given, it is created an instance of the player with
		// that nickname
		UserManager.manageUser(nickname);

	}

}
