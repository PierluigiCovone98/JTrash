package it.uniroma1.mdp.view.game;

import java.awt.Color;

import javax.swing.JPanel;

import it.uniroma1.mdp.controller.SelectedCardFromHandListener;

/**
 * Represents the panel for the Human player in the game.
 */
public class HumanPanel extends JPanel {
	// CONSTANTS
	public static final long serialVersionUID = 1492451040230675319L;

	// FIELDS
	private HorizontalHandPanel hand;
	private TrashButton trashButton;

	// METHODS
	public HumanPanel() {
		// 1. Initializing fields
		this.hand = new HorizontalHandPanel();
		this.trashButton = new TrashButton();
	}

	/**
	 * Configure the UI of this panel instance, setting the layout manager, adding
	 * components, eventually action listeners and technical details.
	 */
	protected void setupUI() {
		setLayout(null); // Allows to place the panel of cards and the button in the right position

		this.hand.setupUI();
		// Adding the panel of card buttons in the upper position
		this.hand.setBounds(0, 0, 650, 280); // x, y, width, height
		add(this.hand);

		this.trashButton.setBounds(280, 280, 70, 35); // x, y, width, height
		add(this.trashButton);

		// Adding listeners to each card button
		addListenersCardInHand();

		// Adding details...
		addDetails();
	}

	/**
	 * Adding technical details for this frame instance.
	 */
	private void addDetails() {
		setBackground(Color.WHITE);
		setOpaque(false);
	}

	/**
	 * This method allows to add listeners over each button in the hand reference.
	 */
	private void addListenersCardInHand() {
		for (int i = 0; i < this.hand.getCards().length; i++) {
			CardButton cardButton = this.hand.getCard(i);
			cardButton.addActionListener(new SelectedCardFromHandListener(cardButton, i));
		}
	}

	/**
	 * Gets the hand panel of the Human player.
	 */
	public HorizontalHandPanel getHand() {
		return this.hand;
	}

	/**
	 * Gets the trash button in the Human panel.
	 */
	public TrashButton getTrashButton() {
		return this.trashButton;
	}
}
