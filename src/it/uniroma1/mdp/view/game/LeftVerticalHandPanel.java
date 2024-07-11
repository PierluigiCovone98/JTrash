package it.uniroma1.mdp.view.game;

/**
 * Represents a left-oriented vertical hand panel to display a player's hand of
 * cards. Extends VerticalHandPanel and provides specific configuration for the
 * left orientation.
 */
public class LeftVerticalHandPanel extends VerticalHandPanel {
	// Constants
	private static final long serialVersionUID = 987530461927063848L;

	// Methods
	// Constructor.
	public LeftVerticalHandPanel() {
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
			for (int i = 0, j = 5; i < getCardsInHand() / 2 && j < getCardsInHand(); i++, j++) {
				cards[j] = new HCardButton("images/cardsSprites/backH.png");
				cards[i] = new HCardButton("images/cardsSprites/backH.png");

				CardButton cardJ = cards[j];
				CardButton cardI = cards[i];

				// card.setPressedIcon(card.getIcon());
				add(cardJ);
				add(cardI);
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("No more cards can be added");
		}

		// 2. adding details
		super.addDetails();
	}

}
