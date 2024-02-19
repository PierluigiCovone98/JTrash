package it.uniroma1.mdp.view.game;

import java.util.Observable;
import java.util.Observer;

/**
 * Represents the card drawn from the deck in the game interface.
 */
public class DrawnCard extends VCardButton implements Observer {
	// Constants
	public static final long serialVersionUID = 1659058956013800466L;

	// METHODS
	/**
	 * Constructs a new DrawnCard with the specified icon path.
	 */
	public DrawnCard() {
		this("");
	}

	public DrawnCard(String iconPath) {
		super(iconPath);
	}

	/**
	 * Updates the view with the card drawn from the deck.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg != null) {
			String card = (String) arg;
			String cardPath = "images/cardsSprites/" + card + ".png";
			this.updateUI(cardPath);
		}
	}

	/**
	 * Effectively update the view with the given card image path.
	 */
	public void updateUI(String cardPath) {
		setIcon(cardPath);
		setRolloverIcon(cardPath);
		setPressedIcon(cardPath);
		printBorder();
		revalidate();
		repaint();
	}

	/**
	 * Used to restore the image as empty.
	 */
	public void deliteIcons() {
		updateUI("");
		removeBorder();
	}

}
