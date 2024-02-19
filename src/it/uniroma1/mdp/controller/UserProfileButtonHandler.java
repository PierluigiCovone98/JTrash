package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import it.uniroma1.mdp.model.UserManager;
import it.uniroma1.mdp.view.MenuManagerPanel;
import it.uniroma1.mdp.view.utilities.StringFormatter;


/**
 * ActionListener implementation for handling clicks on the "User Profile" button.
 * It updates the user profile information displayed in the menu manager panel
 * and switches the displayed card to the "userProfileMenu".
 */
public class UserProfileButtonHandler implements ActionListener {
	
	 /**
     * Handles the action event triggered by clicking the "User Profile" button.
     * Retrieves the menu manager panel instance from the main frame,
     * updates the user profile information, and switches the displayed card to the "userProfileMenu".
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		// playing the sound
		AudioManagerController.getEffectAudioManagerInstance().playClick();
		
		// saving the instance of the menu manger
		MenuManagerPanel menuManager = JTrash.getMainFrameInstance().getMenuManager();

		// Saving labels of the UserProfileMenu
		JLabel nicknameLabel = menuManager.getUserProfileMenu().getNicknameLabel();
		JLabel gamesWonLabel = menuManager.getUserProfileMenu().getGamesWonLabel();
		JLabel defeatsLabel = menuManager.getUserProfileMenu().getDefeatsLabel();

		// Formatting strings

		String nickname = StringFormatter.formatString("Username: " + UserManager.humanPlayer.getNickname() );
		String gamesWon = StringFormatter.formatString("Games Won: " + String.valueOf(UserManager.humanPlayer.getWon()) );
		String defeats = StringFormatter.formatString("Defeats: " + String.valueOf(UserManager.humanPlayer.getLost()) );

		
		// Adding Current player values
		nicknameLabel.setText(nickname);
		gamesWonLabel.setText(gamesWon);
		defeatsLabel.setText(defeats);

		// changing the card shown
		menuManager.getLayout().show(menuManager, "userProfileMenu");
	}

}
