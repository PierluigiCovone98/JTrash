package it.uniroma1.mdp.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import it.uniroma1.mdp.view.game.DiscardPileButton;
import it.uniroma1.mdp.view.game.DrawnCard;
import it.uniroma1.mdp.view.game.GameTablePanel;
import it.uniroma1.mdp.view.game.StockPileButton;
import it.uniroma1.mdp.view.game.TrashButton;
import it.uniroma1.mdp.model.GameTable;
import it.uniroma1.mdp.model.card.Card;
import it.uniroma1.mdp.model.card.DeckOfCards;
import it.uniroma1.mdp.model.card.FullDeckException;
import it.uniroma1.mdp.model.card.InvalidCardException;


/**
 * Handles the action event when the trash button is clicked, allowing the player to discard cards.
 * The player can discard cards from the drawn card to any of the available discard piles.
 * After discarding a card, the player's turn is automatically passed to the next player.
 */
public class TrashButtonHandler implements ActionListener {

	private TrashButton trashB;
	private int currentDeckIndex;

	
	  /**
     * Constructs a TrashButtonHandler with the specified TrashButton instance.
     * @param trashB The TrashButton instance to be associated with this handler.
     */
	public TrashButtonHandler(TrashButton trashB) {
		// Initialize fields
		this.trashB = trashB;
		this.currentDeckIndex = 0;
	}

	
	   /**
     * Handles the action event triggered when the trash button is clicked.
     * This method allows the player to discard cards from the drawn card to the current available discard piles.
     * After discarding a card, the player's turn is automatically passed to the next player.
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		// playing the sound
		AudioManagerController.getEffectAudioManagerInstance().playClick();
		
		// REFERENCES TO INSTANCES OF CLASSES FROM THE MODEL
		List<DeckOfCards> discardPiles = JTrash.getGameInstance().getGameTable().getDiscardPiles();
		GameTable gameTableM = JTrash.getGameInstance().getGameTable(); // Game Table model instance

		// REFERENCES TO INSTANCES OF CLASSES FROM THE VIEW
		StockPileButton stockPileB = JTrash.getGameFrameInstance().getGameTable().getDecksPanel().getStockPile();
		DiscardPileButton discardPileB = JTrash.getGameFrameInstance().getGameTable().getDecksPanel().getDiscardPile();
		GameTablePanel gameTableV = JTrash.getGameFrameInstance().getGameTable(); // Game table panel instance
		DrawnCard drawnCardB  = gameTableV.getDecksPanel().getDrawnCard();
		
		
		// For a generic number of Deck Of cards ------
		int numberOfDiscardPiles = discardPiles.size();
		if (this.trashB.isClickable()) {			
			while (currentDeckIndex < numberOfDiscardPiles) {
				// Saving the current discard pile
				DeckOfCards discardPile = discardPiles.get(currentDeckIndex);

				// Making the discard pile button as observer of the game table
				discardPile.addObserver(discardPileB);
				
				try {
					if (discardPile.isEmpty()) 
						System.out.println("Discard Pile was empty");
					else 
						System.out.println("Previous card in the DiscardPile: " + discardPile.peek());	
				
					// 1. Saving the popped card
					Card poppedCard = gameTableM.getPoppedCard();
					// 2. Pushing it to the discard pile button
					discardPile.push(poppedCard); // Makes the pushed card visible on the DiscardPileButton
					System.out.println("Just Pushed: " + poppedCard + "\n");
					System.out.println(discardPile+"\n");

					// Making the reference to the poppedCard to null
					gameTableM.poppedCardToNull();
							
					// 3. Making disappeared the card in the DrawnCard button
					drawnCardB.deliteIcons();
			
					// 4. Making it not yet RED
					this.trashB.restore();
					this.trashB.setClickable(false); // Making the trash button not enable yet
					
					// 5. Making possible to draw another card
					stockPileB.setClicked(false);
					stockPileB.setClickable(true);

					// 6. Making the discard pile button selectable again
					discardPileB.setClicked(false);
					discardPileB.setClickable(true);

					// Delete Observers
					discardPile.deleteObserver(discardPileB);
					
					// Pass the turn
					passTheTurn();
					break;
					
				} catch (FullDeckException exception1) {
					System.out.println(exception1.getMessage());
					this.currentDeckIndex++; // use next deck

					// Checking if there are no more deck of cards
					if (this.currentDeckIndex >= discardPiles.size())
						System.out.println("There are no more deck of cards");
					break;

				} catch (InvalidCardException exception2) {
					System.out.println(exception2.getMessage());
					break;
				}
			} 
		} else
			System.out.println("Cannot press the trash button");
	}
	
	/**
	 * Passes the turn to the next player after discarding a card.
	 */
	private void passTheTurn() {
		Timer timer = new Timer(1000, event -> {
			// Switching to the next player
			JTrash.getGameInstance().nextTurn(); // Changes computer turn to the next in the list
			// Setting the next hand panel
			int currentPlayerIndex = JTrash.getGameInstance().getGameStatusManager().getCurrentPlayerIndex();
			JTrash.getGameFrameInstance().getGameTable().setCurrentHandIndex(currentPlayerIndex);
			// Fires up the handler of turns
			TurnsHandler turnsHandler = new TurnsHandler();
			ActionEvent nextTurnEvent = new ActionEvent(this.trashB, ActionEvent.ACTION_PERFORMED, "NextTurn");
			turnsHandler.actionPerformed(nextTurnEvent);
		});
		timer.setRepeats(false); // Execute the timer only once
		timer.start();
	}

}
