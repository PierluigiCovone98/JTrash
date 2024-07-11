package it.uniroma1.mdp.view.game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * This class represents the panel containing the decks and the drawn card in
 * the game interface.
 */
public class DecksPanel extends JPanel {
	// CONSTANTS
	public static final long serialVersionUID = 3710127267970759107L;

	// FIELDS
	private StockPileButton drawDeck;
	private DiscardPileButton discardDeck;
	private DrawnCard drawnCard;

	// METHODS
	// Constructor
	public DecksPanel() {
		super(null); // Placing components where needed

		// initialize fields
		this.drawDeck = new StockPileButton();
		this.discardDeck = new DiscardPileButton();

		this.drawnCard = new DrawnCard(); // Makes it not visible
	}

	/**
	 * Configure the UI of this panel instance, setting the layout manager, adding
	 * components, eventually action listeners. This method is invoked by the parent
	 * of this panel
	 */
	protected void setupUI() {

		// 1. Adding each button as component in this panel
		add(this.drawDeck);
		add(this.discardDeck);

		// 2. Adding the drawn card
		add(this.drawnCard);

		// 3. Adding technical details
		addDetails();
	}

	/**
	 * Adding technical details for this frame instance
	 */
	private void addDetails() {
		setBackground(Color.WHITE);
	}

	/**
	 * Renders components; we make them centralized. It is not usual to set bounds
	 * of each component alongside the paintComponent method; but in this case it
	 * was necessary because getWidth and getHeight methods returns a value
	 * different from 0 only if they are invoked in the paintComponent method
	 * (runtime).
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int panelWidth = getWidth();
		int panelHeight = getHeight();

		int buttonWidth = VCardButton.WIDTH;
		int buttonHeight = VCardButton.HEIGHT;
		int spaceBetweenButtons = 270; // Space between buttons

		// Calculating central positions for drawDeck and discardDeck
		int totalWidth = 2 * buttonWidth + spaceBetweenButtons;
		int startingX = (panelWidth - totalWidth) / 2;

		int drawDeckX = startingX;
		int discardDeckX = startingX + buttonWidth + spaceBetweenButtons;
		int buttonsY = (panelHeight - buttonHeight) / 2;

		// Places drawDeck e discardDeck
		drawDeck.setBounds(drawDeckX, buttonsY, buttonWidth, buttonHeight);
		discardDeck.setBounds(discardDeckX, buttonsY, buttonWidth, buttonHeight);

		// Calculate the position of drawnCard
		int drawnCardX = drawDeckX + buttonWidth + 10; // Placing it at the right of the draw deck
		int drawnCardY = buttonsY + 20; // Placing it at the bottom of the draw deck

		// Places drawCard 
		drawnCard.setBounds(drawnCardX, drawnCardY, buttonWidth, buttonHeight);
	}

	/**
	 * Restore components in this panel.
	 */
	public void restoreDecksPanel() {
		// 1. Making the instance of DrawnCard empty
		this.drawnCard.deliteIcons();

		// 2. Restoring the StockPileButton
		this.drawDeck.restoreIcon(StockPileButton.IMAGE_PATH);
		this.drawDeck.setClickable(true);
		this.drawDeck.setClicked(false);

		this.discardDeck.restoreIcon(DiscardPileButton.TRASH_ICON_PATH);
		this.discardDeck.setClickable(true);
		this.discardDeck.setClicked(false);

	}

	/**
	 * Returns the stock pile button.
	 */
	public StockPileButton getStockPile() {
		return this.drawDeck;
	}

	/**
	 * Returns the discard pile button.
	 */
	public DiscardPileButton getDiscardPile() {
		return this.discardDeck;
	}

	/**
	 * Returns the drawn card.
	 */
	public DrawnCard getDrawnCard() {
		return this.drawnCard;
	}

}
