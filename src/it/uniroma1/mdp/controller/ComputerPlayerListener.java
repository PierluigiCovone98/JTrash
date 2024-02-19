package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import it.uniroma1.mdp.model.GameTable;
import it.uniroma1.mdp.model.card.DeckOfCards;
import it.uniroma1.mdp.model.card.Card;
import it.uniroma1.mdp.model.card.EmptyDeckException;
import it.uniroma1.mdp.model.players.ComputerPlayer;
import it.uniroma1.mdp.model.players.Player;
import it.uniroma1.mdp.view.game.CardButton;
import it.uniroma1.mdp.view.game.DiscardPileButton;
import it.uniroma1.mdp.view.game.GameTablePanel;
import it.uniroma1.mdp.view.game.HandPanel;
import it.uniroma1.mdp.view.game.StockPileButton;
import it.uniroma1.mdp.view.game.TrashButton;


/**
 * Controller responsible for managing computer player actions during the game.
 * This class handles actions such as drawing cards, switching cards, and discarding cards for the computer player.
 */
public class ComputerPlayerListener implements ActionListener {

	// Fields
	// --- model
	private ComputerPlayer computerPlayer;
	private GameTable gameTableM;
	private int currentPlayerIndex;

	// --- view
	private GameTablePanel gameTableV;
	private StockPileButton stockPileB;
	private DiscardPileButton discardPileB;

	private TrashButton trashB;
	private HandPanel currentHand; // This is an array of CardButton
	
	private Timer timerSwitch;

	// Methods
	/**
	 * Constructor.
	 */
	public ComputerPlayerListener(Player computerPlayer) {
		// --- model
		this.computerPlayer = (ComputerPlayer) computerPlayer;
		this.gameTableM = JTrash.getGameInstance().getGameTable();
		this.currentPlayerIndex = JTrash.getGameInstance().getGameStatusManager().getCurrentPlayerIndex();

		// --- view
		this.gameTableV = JTrash.getGameFrameInstance().getGameTable();
		this.stockPileB = this.gameTableV.getDecksPanel().getStockPile();
		this.discardPileB = this.gameTableV.getDecksPanel().getDiscardPile();

		this.trashB = this.gameTableV.getHumanPanel().getTrashButton();
		this.currentHand = this.gameTableV.getHandPlayer(this.currentPlayerIndex);
	}

	
	 /**
     * Handles the action event triggered by computer player actions during the game.
     * Initiates the process of drawing cards, switching cards, and discarding cards.
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		 SwingUtilities.invokeLater(() -> {
		        // 1. Draw a card
		        Timer timerDraw = new Timer(1000, event -> {
		            drawCard(); // It should be improved ...
		        });
		        timerDraw.setRepeats(false); // Execute the timer only once
		        timerDraw.start();

		        // 2. Switch cards (this should be done with repetitions)
		        this.timerSwitch = new Timer(2000, event -> {
		            switchCards();

		            if (!isDrawable(this.gameTableM.getPoppedCard())) {
		                this.timerSwitch.stop();
		                discardCard();
		            }
		        });
		        timerSwitch.setRepeats(true); // Execute the timer only once
		        timerSwitch.setDelay(1300);
		        timerSwitch.start();
		    });
	}

	
	
	   /**
     * Draws a card from the discard pile or stock pile.
     */
	private void drawCard() {
		try {
			// 1. Saving the current instance of the discarded pile
			DeckOfCards discardPile = this.gameTableM.getCurrentDiscardPile();

			// 2. Peeking the card on the top of the discard pile
			Card peekedCard = discardPile.peek();
			// 3. If this card can be popped
			if (isDrawable(peekedCard)) {
				DrawFromDiscardPileListener discardCardListener = new DrawFromDiscardPileListener(this.discardPileB);
				ActionEvent drawFromDiscardEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
						"Draw From Discar Pile");
				discardCardListener.actionPerformed(drawFromDiscardEvent);
			} else // Otherwise, we pop from the stock pile button
				throw new EmptyDeckException();

		} catch (EmptyDeckException emptyExcemption) { // Otherwise draw from the stock pile
			System.out.println("Discarded pile is empty");
			// Saving the instance of StockPile
			//DeckOfCards stockPile = this.gameTableM.getCurrentStockPile();

			DrawFromStockPileListener drawCardListenter = new DrawFromStockPileListener(this.stockPileB);
			ActionEvent drawFromStockEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
					"Draw From Stock Pile");
			drawCardListenter.actionPerformed(drawFromStockEvent);

		}

	}

	 /**
     * Checks if a card is drawable by the computer player.
     */
	private boolean isDrawable(Card cardToCheck) {
		int peekedCardRank = cardToCheck.getRank().getRankValue();

		if (peekedCardRank == 11)
			return Arrays.stream(this.currentHand.getCards()).anyMatch(cardB -> cardB.isClickable());
		else if (peekedCardRank >= 0 && peekedCardRank <= 10) {
			// 1. Checking if the card at that index is playable
			int peekedIndex = peekedCardRank - 1;
			CardButton cardB = this.currentHand.getCard(peekedIndex);
			Card card = this.computerPlayer.getCardAt(peekedIndex);
			if (cardB.isClickable() || card.getRank().getRankValue() == 11)
				return true;
			else
				return false;
		}

		// This is the case the card rank value is -1
		return false;
	}

	  /**
     * Switches cards in the computer player's hand.
     */
	private void switchCards() {
		// 1.
		int poppedCardRank = this.gameTableM.getPoppedCard().getRank().getRankValue();
		if (poppedCardRank == -1)
			return;

		int cardButtonIndex = -1;

		// If the card is a Jack
		if (poppedCardRank == 11) {

			while (true) {
				Random random = new Random();
				cardButtonIndex = random.nextInt(HandPanel.MAX_CARDS_IN_HAND);
				CardButton cardB = this.currentHand.getCard(cardButtonIndex);
				if (cardB.isClickable())
					break;
			
			}
		} else
			cardButtonIndex = poppedCardRank - 1;

		CardButton cardB = this.currentHand.getCard(cardButtonIndex);

		SelectedCardFromHandListener switchCardListener = new SelectedCardFromHandListener(cardB, cardButtonIndex);
		ActionEvent switchCardEvent = new ActionEvent(this.currentHand, ActionEvent.ACTION_PERFORMED, "Switch Card");
		switchCardListener.actionPerformed(switchCardEvent);
	}


	/**
	 * Discards a card from the computer player's hand.
	 */
	private void discardCard() {
		Timer discardTimer = new Timer(1300, event -> {
			TrashButtonHandler trashButtonH = new TrashButtonHandler(this.trashB);
			ActionEvent discardCardEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Discard Card");
			trashButtonH.actionPerformed(discardCardEvent);
		});
		discardTimer.setRepeats(false);
		discardTimer.start();

	}
}
