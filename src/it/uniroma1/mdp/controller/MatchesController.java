package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;
import it.uniroma1.mdp.model.GameStatusManager;
import it.uniroma1.mdp.model.FullGame;

import it.uniroma1.mdp.view.game.CardButton;

import it.uniroma1.mdp.view.game.GameFrame;

import it.uniroma1.mdp.view.game.HandPanel;
import it.uniroma1.mdp.view.utilities.ImageUtility;

/**
 * Controller class responsible for managing matches within a game session.
 * It checks if the current match can be terminated and prepares for the next match if necessary.
 */
public class MatchesController implements ActionListener {

	// FIELDS
	// --- model
	private GameStatusManager gameStatusManger;

	// --- view
	private HandPanel currentHand;

	// MEHTODS
	/**
	 * Constructor.
	 */
	public MatchesController() {
		// Initialize fields
		// --- model
		this.gameStatusManger = JTrash.getGameInstance().getGameStatusManager();
		// --- view
		this.currentHand = JTrash.getGameFrameInstance().getGameTable().getCurrentHandPlayer();

	}

	/**
	 * Handles the action event, checking if the current match can be terminated or
	 * not.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// Instances from the view package ---
		CardButton[] cards = this.currentHand.getCards(); // Array of card buttons in the hand of the current player

		// 1. Checks if the current player has only one card in his hand
		if (cards.length == 1)
			// 1.1 If true, checking if the game can terminate
			endFullGame();
		else
			// 2. If not so, checking if the current player wins the turn
			checksForMatchEnds(cards);

	} // end of the actionPerformed() method

	
	/**
	 * Invokes the GameTerminarionController to check if the game is actually ended.
	 */
	private void endFullGame() {
		// Creates the instance of the GameTerminationController
		GameTerminationController terminationController = new GameTerminationController();
		// The action event passed as argument for the actionPerformed() method
		ActionEvent terminationEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
				"Checks if the game is Over");
		// Invokes the actionPerformed() method
		terminationController.actionPerformed(terminationEvent);
	}

	/**
	 * Checks if the current match can be terminated.
	 */
	private void checksForMatchEnds(CardButton[] cards) {
		// 0. Saving the reference to the GameFrame instance
		GameFrame playFrame = JTrash.getGameFrameInstance();

		// 1. If all card in the hand are faced up
		if (Arrays.stream(cards).allMatch(card -> !card.isClickable())) {
			// 1.1 It means the current player wins the match. We must check if it is the human or not
			if (gameStatusManger.isCurrentPlayerHuman())
				// 1.1.1 Informs the user of the win
				JOptionPane.showMessageDialog(playFrame, "YOU WIN THE MATCH", "Game Over",
						JOptionPane.INFORMATION_MESSAGE,
						ImageUtility.resizeIcon("images/gameSessionImages/winner.png", 100, 100));
			// 1.1.2 Informs the user of the lose
			else
				JOptionPane.showMessageDialog(playFrame, "YOU LOSE THE MATCH", "Game Over",
						JOptionPane.INFORMATION_MESSAGE,
						ImageUtility.resizeIcon("images/gameSessionImages/LOSE.png", 100, 100));
			// 1.2 Preparing and starting the new match
			nextMatch();
		} else {
			System.out.println("There are cards that are not faced up");
		}
	} // end of the 'checksForMatchEnds' method

	
	
	/**
	 * Initializing settings for the new match.
	 */
	private void nextMatch() {
			
		// Adding observers
			
		// 1. Restoring settings of classes of the model to play the next match
		FullGame fullGame = (FullGame)JTrash.getGameInstance(); 
		fullGame.nextMatch(this.gameStatusManger.getCurrentPlayer());
	
		
		// MAKING DISAPPARING & REAPPARING THE WINDOW
		JTrash.getGameFrameInstance().dispose();
		JTrash.getGameFrameInstance().setVisible(false);
		
		
		// TIMER
		JTrash.getGameFrameInstance().getHuDisplay().resetTimer();
		// PLAYER HANDS
		JTrash.getGameFrameInstance().getGameTable().restoreGameTable(this.currentHand);
	
		
		// MAKING THE WINDOW APPEARED AGAIN
		JTrash.getGameFrameInstance().setVisible(true);
	
		// 999. Starting the next match
		startsNewMatch();

	} // end of the nextMatch method

	
	/**
	 * Starts the new match.
	 */
	private void startsNewMatch() {
		// Create the instance of the handler for turns
		TurnsHandler turnsHandler = new TurnsHandler();
		// Create the action event, passed as argument for the action performed method
		ActionEvent startsTheMatchEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Starts next turn");
		// Starts the event
		turnsHandler.actionPerformed(startsTheMatchEvent);
	}
	


}
