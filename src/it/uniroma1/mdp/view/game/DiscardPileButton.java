package it.uniroma1.mdp.view.game;

import java.util.Observable;
import java.util.Observer;

import it.uniroma1.mdp.controller.DrawFromDiscardPileListener;

/**
 * Represents the discard pile button in the game interface.
 */
public class DiscardPileButton extends VCardButton implements Observer {
	// CONSTANTS
	public static final long serialVersionUID = 8692453878134108735L;
	public static final String TRASH_ICON_PATH = "images/frame/cestino.png";

	// FIELDS
	private boolean bBorder; // False as default

	// METHODS
	/**
	 * Constructor.
	 */
	public DiscardPileButton() {
		super(TRASH_ICON_PATH);
	}

	/**
	 * Set ups "Discard Deck button". Adds an action listener to allow discarding a
	 * card from this discard pile.
	 */
	protected void setup() {
		super.setup();

		// 1. Adding an action listener
		addActionListener(new DrawFromDiscardPileListener(this));
		// addActionListener( new SelectableCardListener(this) );
	}

	/**
	 * Updates the view when a card is discarded.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg != null && arg.getClass() == String.class) {
			String card = (String) arg; // The argument is a card
			String cardPath = "images/cardsSprites/" + card + ".png";
			updateUI(cardPath);
		}
	}

	/**
	 * Effectively update the view with the given card image path.
	 */
	public void updateUI(String cardPath) {
		setIcon(cardPath);
		setRolloverIcon(cardPath);
		setPressedIcon(cardPath);
		revalidate();
		repaint();
	}

	/**
	 * Restores the icon of this button to the specified image path.
	 */
	@Override
	public void restoreIcon(String string) {
		super.restoreIcon(string);
	}

	/**
	 * Used to restore the image as empty.
	 */
	public void deliteIcons() {
		updateUI("");
		removeBorder();
	}

	/**
	 * Returns the value of the bBorder property of the DiscardPileButton instance.
	 */
	public boolean hasBorder() {
		return this.bBorder;
	}

	/**
	 * Sets the value of the bBorder property of the DiscardPileButton instance.
	 */
	public void setBBorder(boolean bBorder) {
		this.bBorder = bBorder;
	}
}
