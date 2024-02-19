package it.uniroma1.mdp.view.game;

/**
 * Represents a right-oriented vertical hand panel to display a player's hand of
 * cards. Extends VerticalHandPanel and provides specific configuration for the
 * right orientation.
 */
public class RightVerticalHandPanel extends VerticalHandPanel {
	// Constants
	private static final long serialVersionUID = 7745217155531500970L;

	// Methods
	// Constructor.
	public RightVerticalHandPanel() {
		super();
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

		try {
			for (int i = getCardsInHand() / 2 - 1, j = getCardsInHand() - 1; i >= 0 && j >= 5; i--, j--) {
				cards[i] = new HCardButton("images/cardsSprites/backH.png");
				cards[j] = new HCardButton("images/cardsSprites/backH.png");
				CardButton cardI = cards[i];
				CardButton cardJ = cards[j];
				// card.setPressedIcon(card.getIcon());
				add(cardI);
				add(cardJ);
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("No more cards can be added");
		}
		// 2. adding details
		super.addDetails();
	}

}
