package it.uniroma1.mdp.view.game;

import it.uniroma1.mdp.controller.DrawFromStockPileListener;

/**
 * Represents the stock pile button in the game interface.
 */
public class StockPileButton extends VCardButton {
	// CONSTANTS
	public static final long serialVersionUID = 8692453878134108735L;
	public static final String IMAGE_PATH = "images/cardsSprites/backV.png";

	// METHODS
	public StockPileButton() {
		super(IMAGE_PATH);
	}

	/**
	 * Set ups "Draw Deck button". Adds an action listener to allow drawing a card
	 * from this stock pile.
	 */
	@Override
	protected void setup() {
		super.setup();

		// 2. Allows to draw a card from this stock pile
		addActionListener(new DrawFromStockPileListener(this));
	}

	/**
	 * Restores the icon of this button to the specified image path.
	 */
	@Override
	public void restoreIcon(String string) {
		super.restoreIcon(string);
	}
}
