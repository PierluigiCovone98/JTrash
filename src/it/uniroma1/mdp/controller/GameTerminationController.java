package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;

import it.uniroma1.mdp.model.GameStatusManager;
import it.uniroma1.mdp.model.UserManager;
import it.uniroma1.mdp.view.EffectAudioManager;
import it.uniroma1.mdp.view.MainFrame;
import it.uniroma1.mdp.view.MusicAudioManager;
import it.uniroma1.mdp.view.game.CardButton;
import it.uniroma1.mdp.view.game.GameFrame;
import it.uniroma1.mdp.view.game.HandPanel;
import it.uniroma1.mdp.view.utilities.ImageUtility;
import it.uniroma1.mdp.view.utilities.StringFormatter;

/**
 * Controller responsible for managing game termination conditions and ending
 * the game accordingly. This class checks if all cards in the current hand are
 * face up and takes appropriate actions to end the game.
 */
public class GameTerminationController implements ActionListener {

	// FIELDS
	// --- model
	private GameStatusManager gameStatusManger;

	// --- view
	private HandPanel currentHand;

	// MEHTODS
	/**
	 * Constructs a GameTerminationController instance. Initializes the fields
	 * related to game status and the current hand panel.
	 */
	public GameTerminationController() {
		// Initialize fields
		// --- model
		this.gameStatusManger = JTrash.getGameInstance().getGameStatusManager();

		// --- view
		this.currentHand = JTrash.getGameFrameInstance().getGameTable().getCurrentHandPlayer();
	}

	/**
	 * Handles the action event triggered when the game termination conditions are
	 * met. Checks if all cards in the current hand are face up, and if so,
	 * determines the winner and ends the game.
	 * 
	 * @param e The action event triggered by the game termination conditions
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// playing the sound
		EffectAudioManager effectAudio = AudioManagerController.getEffectAudioManagerInstance();
		MusicAudioManager musicAudio = AudioManagerController.getMusicAudioManagerInstance();
		
		
		// 1. Saving the array of cards
		CardButton[] cards = this.currentHand.getCards();
		System.out.println(JTrash.getGameInstance().getClass().toString());

		// 2. Checking if they are all faced up; if true, the game must be stopped.
		if (Arrays.stream(cards).allMatch(card -> !card.isClickable())) {
			// Stop the music
			musicAudio.stopAndReset();
			
			if (gameStatusManger.isCurrentPlayerHuman()) {
				UserManager.humanPlayer.increaseWon();
				effectAudio.playWinRonaldo();
				endGame(StringFormatter.formatString("YOU WIN", "green"), "images/gameSessionImages/winner.png");

			} else {
				UserManager.humanPlayer.increaseLost();
				effectAudio.playLose();
				endGame(StringFormatter.formatString("YOU LOSE", "red"), "images/gameSessionImages/lose.png");

			} 
		} else
			System.out.println("Nessuno ha vinto");
	}

	/**
	 * Ends the game by displaying the appropriate message and returning to the main
	 * menu.
	 */
	private void endGame(String winner, String iconPath) {

		// Updating the .csv file
		UserManager.updateUserInformations();

		// Saving here references to both frames
		GameFrame playFrame = JTrash.getGameFrameInstance();
		MainFrame mainFrame = JTrash.getMainFrameInstance();

		// Informs the user the game is over
		JOptionPane.showMessageDialog(playFrame, winner, "Game Over", JOptionPane.INFORMATION_MESSAGE,
				ImageUtility.resizeIcon(iconPath, 100, 100));

		// Close the GameFrame
		playFrame.dispose();

		// Removing references to the GameFrame instance
		JTrash.setToNullTheGameFrameInstance();
		// Removing references to the Game instance
		JTrash.setToNullTheGameInstance();

		// Go back to the main menu
		mainFrame.setVisible(true);
		mainFrame.getMenuManager().getLayout().show(mainFrame.getMenuManager(), "mainMenu");

	}

}
