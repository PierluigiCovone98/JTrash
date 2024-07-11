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
 * Handles the action event when the player clicks on the stock pile button to
 * draw a card.
 */
public class DrawFromStockPileListener implements ActionListener {

	private StockPileButton stockPileB;
	private int currentDeckIndex; // In case of more than one deck of cards

	// Methods
	/**
	 * Constructs a DrawFromStockPileListener with the specified StockPileButton
	 * instance.
	 */
	public DrawFromStockPileListener(StockPileButton stockPileB) {
		// Initialize fields
		this.stockPileB = stockPileB;
		this.currentDeckIndex = 0;
	}

	/**
	 * Handles the action event triggered when the player clicks on the stock pile
	 * button to draw a card. This method allows the player to draw a card from the
	 * stock pile and visualizes it on the drawn card button. If the stock pile is
	 * empty, it reshuffles the discard piles and continues drawing cards.
	 * 
	 * @param e The action event.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// playing the sound
		EffectAudioManager effectAudio = AudioManagerController.getEffectAudioManagerInstance();

		// REFERENCES TO INSTANCES OF CLASSES FROM THE MODEL
		GameTable gameTable = JTrash.getGameInstance().getGameTable(); // game table
		List<DeckOfCards> stockPiles = gameTable.getStockPiles(); // Stock piles

		// REFERENCES TO INSTANCES OF CLASSES FROM THE VIEW
		DiscardPileButton discardPileB = JTrash.getGameFrameInstance().getGameTable().getDecksPanel().getDiscardPile();
		DrawnCard drawnCardB = JTrash.getGameFrameInstance().getGameTable().getDecksPanel().getDrawnCard();
		TrashButton trashB = JTrash.getGameFrameInstance().getGameTable().getHumanPanel().getTrashButton();

		// For a generic number of Deck Of cards
		int numberStockPiles = stockPiles.size();
		if (!this.stockPileB.isClicked()) {// If is has not been already clicked
			effectAudio.playDraw();
			while (this.currentDeckIndex < numberStockPiles) {
				// Saving the current stock pile
				DeckOfCards stockPile = stockPiles.get(currentDeckIndex);

				stockPile.addObserver(drawnCardB); // Visualize the peeked card as Drawn Card
				gameTable.addObserver(trashB); // This to make the Trash button RED

				try {
					Card poppedCard = stockPile.pop(); // Visualizing the card on the DrawnCardButton
					gameTable.setPoppedCard(poppedCard); // Making the trash button red
					trashB.setClickable(true); // Making the trash button enabled

					// Making the stock pile button not clickable yet
					this.stockPileB.setClicked(true);
					this.stockPileB.setClickable(false);

					// Making the discard pile button not selectionable
					discardPileB.setClicked(true);
					discardPileB.setClickable(false);

					// Deleting observers
					stockPile.deleteObserver(drawnCardB); // Visualize the peeked card as Drawn Card
					gameTable.deleteObserver(trashB); // This to make the Trash button RED

					break; // breaking the while loop
				} catch (EmptyDeckException exception) {
					// System.out.println(exception.getMessage());
					this.currentDeckIndex++; // increase the current value of the deck

					// Checking if there are no more deck of cards
					if (this.currentDeckIndex >= numberStockPiles) {
						gameTable.addObserver(discardPileB);
						System.out.println("There are no more deck of cards");
						// Using DiscarPiles ad StockPiles again
						JTrash.getGameInstance().getGameTable().getVirtualDealer().reshuffleDiscardPile();
						// Making discardPile button updated
						this.currentDeckIndex = 0;
						gameTable.deleteObserver(discardPileB);

					}
				} // end of the catch
			} // end of while
		} else
			effectAudio.playBlock();

	}

}
