package it.uniroma1.mdp.controller;

import it.uniroma1.mdp.view.game.VerticalHandPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.uniroma1.mdp.model.GameTable;

import it.uniroma1.mdp.model.card.Card;
import it.uniroma1.mdp.model.players.Player;
import it.uniroma1.mdp.view.EffectAudioManager;
import it.uniroma1.mdp.view.game.CardButton;
import it.uniroma1.mdp.view.game.DrawnCard;


/**
 * Handles the action event when a player selects a card from their hand.
 * This listener allows the player to switch the selected card with the popped card from the game table.
 */
public class SelectedCardFromHandListener implements ActionListener {

	// Fields
	
	private CardButton selectedCardB; // From the view
	private int selectedCardIndex;

	// --- model
	private GameTable gameTableM;
	private Player currentPlayer;

	// --- view
	private DrawnCard drawnCard;

	// Methods
	/**
     * Constructs a SelectedCardFromHandListener with the specified parameters.
     * Initializes fields and obtains model and view instances.
     */
	public SelectedCardFromHandListener(CardButton selectedCardB, int selectedCardIndex) {
		// Initialize fields
		this.selectedCardB = selectedCardB;
		this.selectedCardIndex = selectedCardIndex;

		// model
		this.gameTableM = JTrash.getGameInstance().getGameTable();
		this.currentPlayer = JTrash.getGameInstance().getGameStatusManager().getCurrentPlayer();
		// System.out.println(currentPlayer.getClass());

		// view
		this.drawnCard = JTrash.getGameFrameInstance().getGameTable().getDecksPanel().getDrawnCard();
	}

	
	 /**
     * Performs the necessary actions when a player selects a card from their hand.
     * This method allows the player to switch the selected card with the popped card from the game table.
     * It also handles game termination based on the game mode.
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Saving the instance of the audio controller
		EffectAudioManager effectAudio = AudioManagerController.getEffectAudioManagerInstance();

		// Adding observers
		gameTableM.addObserver(drawnCard); // Should visualize the card switched from the human hand

		if (gameTableM.getPoppedCard() != null) {
			// Saving the value of the popped card
			int poppedCardValue = gameTableM.getPoppedCard().getRank().getRankValue();

			// Takes action if and only if the card is covered
			if (this.selectedCardB.isClickable()) {
				
				
				// Takes actions based on the value of the popped card
				switch (poppedCardValue) {
				case -1 -> {
					effectAudio.playBlock();
					System.out.println("Unplayable card");
				}
				case 11 -> { // JACK
					effectAudio.playDraw();
					switchCards(e);
				}
				default -> {
					if (this.selectedCardIndex == poppedCardValue - 1) {
						effectAudio.playDraw();
						switchCards(e);
					}
						
					else 
						effectAudio.playBlock();
				}
				} // end of the switch-case statement
			} else if (!this.selectedCardB.isClickable() // To switch a card with a Jack already placed
					&& currentPlayer.getCardAt(this.selectedCardIndex).getRank().getRankValue() == 11
					&& this.selectedCardIndex == poppedCardValue - 1) {
				effectAudio.playDraw();
				switchCards(e);
			}
				
			else
				System.out.println("UnplayableCard");

		}

		// Delete Observers
		gameTableM.deleteObserver(drawnCard);
		
		
		

		// 2.1 At this point I should control if the game is in a SingleGame or in a FullGame mode.
		//	   NOTE: I commented following lines of code because I was no able to complete the MatchesController
		// 			 with which both instances of Game & GameFrame should have reset.
		//			 I faced problems by trying to update the view.
		/*
		if (JTrash.getGameInstance() instanceof SingleGame) {
			System.out.println("Single game");
			// Checking if the match must be terminated
			endGame();
			
		} else {
			System.out.println("Full game");
			MatchesController matchesController = new MatchesController();
			ActionEvent terminateFullGameEvent = new ActionEvent(this,  ActionEvent.ACTION_PERFORMED, "Terminate Full Game");
			matchesController.actionPerformed(terminateFullGameEvent);
		}
		*/
		
		// 2.2 Instead, we act as the Game is in Single Game modality
		endGame();
	}

	
	/**
	 * Method responsible for terminating the current game session.
	 * It creates an instance of the GameTerminationController and triggers its actionPerformed method
	 * to terminate the single game session.
	 */
	private void endGame() { 
		GameTerminationController gameTerminatorController = new GameTerminationController();
		ActionEvent terminateSingleGameEvent = new ActionEvent(this,  ActionEvent.ACTION_PERFORMED, "Terminate Single Game");
		gameTerminatorController.actionPerformed(terminateSingleGameEvent);
		
	}
	
	
	
	/**
	 * Switches the selected card with the popped card from the game table.
	 */
	private void switchCards(ActionEvent e) {
		System.out.println("Posso sostituire");
		// 1. Saving the popped card from the game table instance, from the model
		Card tmpCard = gameTableM.getPoppedCard();

		// 2. Changing the poppedCard in the game table as the one from the hand
		gameTableM.setPoppedCard(currentPlayer.getCardAt(this.selectedCardIndex)); // Logical switch
		gameTableM.setDiscardedCard(currentPlayer.getCardAt(this.selectedCardIndex)); // Update the UI of the drawn card
		// I must make this button not clickable yet
		this.selectedCardB.setClicked(true);
		this.selectedCardB.setClickable(false);

		// 3. Saving the previous popped card in the right position of the human hand
		currentPlayer.setCardAt(this.selectedCardIndex, tmpCard);

		// Making it directly: Here we are going to make the check
		String tmpStringValue = tmpCard.toString();
		if (e.getSource() instanceof VerticalHandPanel) {
			tmpStringValue += "H";
		}
		
		this.selectedCardB.updateIcons(tmpStringValue);
	}

}
