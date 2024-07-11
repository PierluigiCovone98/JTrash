package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.uniroma1.mdp.view.game.GameFrame;
import it.uniroma1.mdp.view.utilities.ImageUtility;
import it.uniroma1.mdp.view.EffectAudioManager;
import it.uniroma1.mdp.view.MainFrame;

/**
 * Handles the action event triggered when press the exit game button.
 */
public class ExitGameHandler implements ActionListener {

	/**
	 * Handles the action event triggered when exiting the game by displaying a
	 * confirmation dialog and performing appropriate actions based on the user's
	 * choice. If the user confirms exiting the game, disposes of the game frame,
	 * sets references to null, and shows the main menu frame; otherwise, re-enables
	 * the game frame.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// playing the sound
		EffectAudioManager effectAudio = AudioManagerController.getEffectAudioManagerInstance();
		effectAudio.playClick();
		
		/*
		 * 1. Using the JOptionPane static method "showConfirmDialog()" to save the
		 * user's chose.
		 */
		GameFrame playFrame = JTrash.getGameFrameInstance();
		MainFrame mainFrame = JTrash.getMainFrameInstance();

		int userChoice = JOptionPane.showConfirmDialog(playFrame, "Do you really want to leave the game?", "BackToMenu",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
				ImageUtility.resizeIcon("images/cardsSprites/2 Spades.png", 70, 100));

		if (userChoice == JOptionPane.YES_OPTION) {
			// playing the sound
			effectAudio.playClick();
			// playFrame.setVisible(false);
			playFrame.dispose();
			JTrash.setToNullTheGameFrameInstance(); // Removes references
			JTrash.setToNullTheGameInstance(); // Remove references
			mainFrame.setVisible(true);
			mainFrame.getMenuManager().getLayout().show(mainFrame.getMenuManager(), "mainMenu");
		
			AudioManagerController.getMusicAudioManagerInstance().stopAndReset();
		} else
			playFrame.setEnabled(true);

	}
}
