package it.uniroma1.mdp.view.game;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import it.uniroma1.mdp.view.ImageButton;

/**
 * Represents a card button in the game interface.
 */
public class CardButton extends ImageButton {
	// CONSTANTS
	public static final long serialVersionUID = 8870507394751487640L;

	// FIELDS
	private boolean bClicked; // false
	private boolean bClickable;

	// METHODS
	/**
	 * Constructor a new CardButton with the specified image path, width, and
	 * height.
	 */
	public CardButton(String imagePath, int width, int heigth) {
		super(imagePath, imagePath, width, heigth);

		// 1. Initialize fields
		this.bClicked = false; // It is not clicked
		this.bClickable = true; // You can click the button

		// 2. Setting up the UI for instances of this button
		setup();
	}

	/**
	 * Set ups the card button.
	 */
	@Override
	protected void setup() {
		// 1. Adding shared button details
		super.setup();
	}

	/**
	 * Prints a yellow border around the button if it is clickable and not clicked.
	 */
	public void printBorder() {
		if (this.bClickable) {
			Border yellowBorder = BorderFactory.createLineBorder(new Color(255, 215, 0), 3); // 2 is the thickness
			this.setBorder(yellowBorder);
			setClicked(true); // The button has been selected
		}
	}

	/**
	 * Removes the border from the button.
	 */
	public void removeBorder() {
		if (this.bClickable) {
			this.setBorder(null);
			setClicked(false); // The button has been deselected
		}
	}

	/**
	 * Updates the icon of the button with the provided card.
	 */
	public void updateIcons(String card) {
		String cardPath = "images/cardsSprites/" + card + ".png";
		super.setIcon(cardPath);
		super.setRolloverIcon(cardPath);
		super.setPressedIcon(cardPath);
		setBorder(null);
		revalidate();
		repaint();
	}

	/**
	 * Restores the icon of the button to the provided image path.
	 */
	public void restoreIcon(String imagePath) {
		setIcon(imagePath);
		setRolloverIcon(imagePath);
		setPressedIcon(imagePath);
		setBorder(null);
		revalidate();
		repaint();
	}

	/**
	 * Checks if the button has been clicked.
	 */
	public boolean isClicked() {
		return bClicked;
	}

	/**
	 * Sets the clicked state of the button.
	 */
	public void setClicked(boolean bClicked) {
		this.bClicked = bClicked;
	}

	/**
	 * Checks if the button is clickable.
	 * 
	 */
	public boolean isClickable() {
		return this.bClickable;
	}

	/**
	 * Sets the clickable state of the button.
	 */
	public void setClickable(boolean bPlayable) {
		this.bClickable = bPlayable;
	}

}
