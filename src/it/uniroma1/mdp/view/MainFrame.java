package it.uniroma1.mdp.view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The main frame of the application, serving as the principal user interface.
 */
public class MainFrame extends JFrame {
	// CONSTATNS
	public static final long serialVersionUID = 6900906504386845121L;
	public static final int MIN_WIDTH = 750;
	public static final int MIN_HEIGHT = 865;

	// FIELDS
	private MenuManagerPanel menuManager;

	// METHODS
	
	
	/**
     * Constructor.
     * Creates an instance of this JFrame subclass with the title "Trash Card Game."
     * Initializes the menu manager panel.
     */
	public MainFrame() {
		super("Trash Card Game");

		// 1. Initializing fields
		this.menuManager = new MenuManagerPanel();
	}

	 /**
     * Configures the UI of this frame instance, setting the layout manager, adding
     * components, and setting up technical details.
     */
	public final void setupUI() {
		// Set the content pane with a panel that has an image ad background
		setContentPane(new BackgroundImagePanel()); 

		setLayout(null); // Place components where I need

		// Set up the UI for the menu manager
		this.menuManager.setupUI();

		// Set the precise position of this component in this frame
		this.menuManager.setBounds(175, 230, 400, 540); // In order: x-coordinate, y-coordinate, width, height

		add(this.menuManager); // Adding the menuManager as component for this frame

		// Showing the first panel user interacts with
		this.menuManager.getLayout().show(this.menuManager, "mainMenu");

		// Adding technical details
		addDetails();
	}

	/**
	 * Adds 'technical details' for this frame instance
	 */
	private void addDetails() {
		setDefaultCloseOperation(EXIT_ON_CLOSE); // If the upper left "red-x" is pressed, the application exits.
		setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT)); // Minimum dimension for a frame
		setLocationRelativeTo(null); // The frame will appear in the center of the screen
		setResizable(false); // Making the frame not resizable
		pack();
	}

	/**
	 * Returns the reference to the instance of the MenuManagerPanel class.
	 */
	public MenuManagerPanel getMenuManager() {
		return this.menuManager;
	}

	/**
     * Inner class used to set the content pane of the outside JFrame subclass with a panel that
     * has an image as a background. Useful when switching from one card to another in the
     * menu manager layout.
     */
	private class BackgroundImagePanel extends JPanel {
		// CONSTANTS
		private static final long serialVersionUID = -7488649645140150405L;

		private static final String BACKGROUND_IMAGE_PATH = "images/frame/bg.png";

		// METHODS
		private BackgroundImagePanel() {
			super();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			// Drawing the image as the background of this panel
			g.drawImage(new ImageIcon(BACKGROUND_IMAGE_PATH).getImage(), 0, 0, getWidth(), getHeight(), this);
		}
	} // end of the inner class

}
