package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import it.uniroma1.mdp.model.GameTable;
import it.uniroma1.mdp.model.card.Card;
import it.uniroma1.mdp.model.card.DeckOfCards;
import it.uniroma1.mdp.model.card.EmptyDeckException;
import it.uniroma1.mdp.view.EffectAudioManager;
import it.uniroma1.mdp.view.game.DiscardPileButton;
import it.uniroma1.mdp.view.game.DrawnCard;
import it.uniroma1.mdp.view.game.StockPileButton;
import it.uniroma1.mdp.view.game.TrashButton;


/**
 * Handles the action event when the player clicks on the discard pile button to
 * draw a card.
 */
public class DrawFromDiscardPileListener implements ActionListener {

	private DiscardPileButton discardPileB;
	private int currentDeckIndex;

	public DrawFromDiscardPileListener(DiscardPileButton discardPileB) {
		// Initialize fields
		this.discardPileB = discardPileB;
		this.currentDeckIndex = 0;
	}

	/**
	 * Handles the action event triggered when the player clicks on the discard pile
	 * button to draw a card. This method allows the player to draw a card from the
	 * discard pile and visualizes it on the drawn card button. If the discard pile
	 * is empty, it checks for the next discard pile and continues drawing cards.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// playing the sound
		EffectAudioManager effectAudio = AudioManagerController.getEffectAudioManagerInstance();

		
			

		// REFERENCES TO INSTANCES OF CLASSES FROM THE MODEL
		GameTable gameTable = JTrash.getGameInstance().getGameTable(); // game table
		List<DeckOfCards> discardPiles = gameTable.getDiscardPiles(); // Stock piles

		// REFERENCES TO INSTANCES OF CLASSES FROM THE VIEW
		StockPileButton stockPileB = JTrash.getGameFrameInstance().getGameTable().getDecksPanel().getStockPile();
		DrawnCard drawnCardB = JTrash.getGameFrameInstance().getGameTable().getDecksPanel().getDrawnCard();
		TrashButton trashB = JTrash.getGameFrameInstance().getGameTable().getHumanPanel().getTrashButton();

		
		// For a generic number of Deck Of cards
		int numberDiscardPiles = discardPiles.size();
		if (!this.discardPileB.isClicked()) { // If is has not been already clicked
			effectAudio.playDraw();
			while (this.currentDeckIndex < numberDiscardPiles) {
				// Saving the current stock pile
				DeckOfCards discardPile = discardPiles.get(currentDeckIndex);
				
				
				discardPile.addObserver(drawnCardB); // Visualize the peeked card as Drawn Card
				gameTable.addObserver(trashB); // This to make the Trash button RED
				gameTable.addObserver(discardPileB); 	// This to visualize the previous card in the DiscardPileButton
				
				if (!discardPile.isEmpty())
					try {
						Card poppedCard = discardPile.pop();	// Visualize the card in the DrawnCardButton
						gameTable.setPoppedCard(poppedCard);	// Makes the Trash button red
						trashB.setClickable(true); 				// Making it enabled
						System.out.println(discardPile+"\n");
						
						// Making the stock pile button not clickable yet
						this.discardPileB.setClicked(true);
						this.discardPileB.setClickable(false);
	
						// Making the discard pile button not selectionable
						stockPileB.setClicked(true);
						stockPileB.setClickable(false);

						// I'm gonna do an huge porcata...Just to visualize previous discarded card
						if (!discardPile.isEmpty()) { 
							Card discardedCard = discardPile.peek();	// Saves the previous card in discard pile
							gameTable.setDiscardedCard(discardedCard);	// Visualize it on the DrawnCardButton
						} else	// If the DiscardPile is empty, just visualize the trash image
							discardPileB.updateUI(DiscardPileButton.TRASH_ICON_PATH);
						
						// Deleting observers
						discardPile.deleteObserver(drawnCardB); 
						gameTable.deleteObserver(trashB);
						gameTable.deleteObserver(discardPileB);
						
						// breaking the while loop
						break;
	
					} catch (EmptyDeckException exception) {
						System.out.println(exception.getMessage());
						this.currentDeckIndex++; // increase the current value of the deck
	
						// Checking if there are no more deck of cards
						if (this.currentDeckIndex >= numberDiscardPiles)
							System.out.println("There are no more deck of cards");
					} // end of the catch
				else {
					effectAudio.playBlock();
					System.out.println("Discard Deck is empty");
					break;
				}
			} // end of while
		} else
			effectAudio.playBlock();
	
	}
	
	
		
	
}


