package it.uniroma1.mdp.view.game;

import java.awt.Color;
import java.awt.GridLayout;

/**
 * Represents a horizontal hand panel in the game, extending the HandPanel
 * class.
 */
public class HorizontalHandPanel extends HandPanel {
	// CONSTATNS
	public static final long serialVersionUID = -4142002730535943533L;

	// METHODS
	// Constructor
	public HorizontalHandPanel() {
		super(new GridLayout(2, 5, 10, 10));
	}

	/**
	 * Configure the UI of this panel instance, setting the layout manager, adding
	 * components, eventually action listeners and technical details.
	 */
	@Override
	public void setupUI() {
		// 0. Saving the instance of the Card Button array
		CardButton[] cards = getCards();
		// 1. This loop is to set cards object inside the array of CardButton & adding
		// each card to the cardPanel

		for (int i = 0; i < getCardsInHand(); i++) {
			cards[i] = new DrawnCard("images/cardsSprites/backV.png");
			CardButton card = cards[i];
			add(card);
		}

		// 2. adding details
		addDetails();
	}

	/**
	 * Adding 'technical details'.
	 */
	protected void addDetails() {
		setBackground(Color.WHITE);
	}

}
