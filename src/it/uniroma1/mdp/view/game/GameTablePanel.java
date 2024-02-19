package it.uniroma1.mdp.view.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * Represents the main game table panel where players and decks are displayed
 * during the game. Extends JPanel and implements the Observer interface to
 * respond to changes in the game settings.
 */
public class GameTablePanel extends JPanel implements Observer {
	// CONSTANTS
	public static final long serialVersionUID = -8477255102448104778L;

	// FIELDS
	// private BotPanel bot1;
	private HumanPanel human;
	private DecksPanel decksPanel;
	private HorizontalHandPanel bot1;
	private RightVerticalHandPanel bot2;
	private LeftVerticalHandPanel bot3;
	private List<HandPanel> handPlayers;
	private int currentHandIndex;

	// METHODS
	/**
	 * Constructor for GameTablePanel. Initializes fields and sets the layout to
	 * null for precise component placement.
	 */
	public GameTablePanel() {
		super(null); // Placing components where I need

		// 1. Initializing fields
		this.human = new HumanPanel();
		this.decksPanel = new DecksPanel();

		this.bot1 = new HorizontalHandPanel();
		this.bot2 = new RightVerticalHandPanel();
		this.bot3 = new LeftVerticalHandPanel();

		this.handPlayers = new ArrayList<HandPanel>();
		this.currentHandIndex = 0;
	}

	/**
	 * Configure the UI of this panel instance, setting the layout manager, adding
	 * components, eventually action listeners and technical details.
	 */
	private void setupUI() {

		// 1. Add the decks panel to the center of this panel
		this.decksPanel.setupUI();
		add(this.decksPanel);

		// 2. Adding the
		this.human.setupUI();
		add(this.human);

		// 3. Adding the horizontal panel, representing the first bots
		this.bot1.setupUI();
		add(this.bot1);

		// 2. Adding each player to the list of players
		this.handPlayers.add(this.human.getHand());
		this.handPlayers.add(bot1);

		// 5. Adding technical details
		addDetails();
	}

	/**
	 * Adding 'technical details' for this frame instance
	 */
	private void addDetails() {
		// setOpaque(false);
		setBackground(Color.WHITE);
	}

	/**
	 * Two players are selected.
	 */
	private void setup2Players() {
		setupUI();
	}

	/**
	 * Three players are selected.
	 */
	private void setup3Players() {
		// Adding the Human and two AIs
		setup2Players();
		this.bot2.setupUI();
		add(this.bot2);
		this.handPlayers.add(bot2);
	}

	/**
	 * Four players are selected.
	 */
	private void setup4Players() {
		// Adding the Human and three AIs
		setup3Players();
		this.bot3.setupUI();
		add(this.bot3);
		this.handPlayers.add(bot3);
	}

	/**
	 * Allows to set up the UI for this panel, based on the user decisions about
	 * settings of the game.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg != null && arg.getClass() == Integer.class) {
			int numberOfPalyers = (int) arg;
			switch (numberOfPalyers) {
			case 3 -> setup3Players();
			case 4 -> setup4Players();
			default -> setup2Players();
			}
		}
	}

	/**
	 * Paints the components of the game table panel. It is not usual to calculate
	 * bounds alongside the paintComponent() method but in this case it was a
	 * necessity because dimensions of the frame are not available if not during
	 * runtime. And getWidth() & getHeight() returns a value different from 0 only
	 * if invoked here.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int width = getWidth();
		int height = getHeight();

		// humanPanel dimensions configuration
		int humanPanelHeight = 310;
		int humanPanelWidth = 650;
		int humanX = (width - humanPanelWidth) / 2;
		int humanY = height - humanPanelHeight - 10;
		human.setBounds(humanX, humanY, humanPanelWidth, humanPanelHeight);

		// decksPanel configuration
		int decksPanelWidth = 470;
		int decksPanelHeight = 170;
		int decksPanelX = (width - decksPanelWidth) / 2;
		int decksPanelY = humanY - decksPanelHeight - 20;
		decksPanel.setBounds(decksPanelX, decksPanelY, decksPanelWidth, decksPanelHeight);

		// Configuration of newHorizontalPanel
		int newHorizontalPanelWidth = 650;
		int newHorizontalPanelHeight = 280;
		int newHorizontalPanelX = (width - newHorizontalPanelWidth) / 2;
		int newHorizontalPanelY = decksPanelY - newHorizontalPanelHeight - 20; // Posiziona sopra decksPanel con margine
		bot1.setBounds(newHorizontalPanelX, newHorizontalPanelY, newHorizontalPanelWidth, newHorizontalPanelHeight);

		// Configuration of AI2
		int bot2PanelWidth = 280; 
		int bot2PanelHeight = 650;
		int bot2X = width - bot2PanelWidth - 20; 
		int bot2Y = (height - bot2PanelHeight) / 2; 
		bot2.setBounds(bot2X, bot2Y, bot2PanelWidth, bot2PanelHeight);

		// Configuration of AI3 
		int bot3PanelWidth = 280; 
		int bot3PanelHeight = 650;
		int bot3X = 20;
		int bot3Y = (height - bot3PanelHeight) / 2;
		bot3.setBounds(bot3X, bot3Y, bot3PanelWidth, bot3PanelHeight);
	}

	
	 /**
     * Restores the game table by resetting the decks panel and restoring each hand panel.
     * The method is currently not used because it was thought for the
	 * FullGame mode.
     */
	public void restoreGameTable(HandPanel handPanelWinner) {
		// 1. Restoring the instance of DecksPanel
		this.decksPanel.restoreDecksPanel();

		// 2. Restoring each HandPanel
		restoreHandPanels(handPanelWinner);

	}

	/**
	 * Restores the game table by resetting the decks panel and restoring each hand
	 * panel. The method is currently not used because it was thought for the
	 * FullGame mode.
	 * 
	 */
	private void restoreHandPanels(HandPanel handPanelWinner) {
		// 1. Clear existing components
		removeAll();

		// 2. Update handPlayers list
		// this.handPlayers.clear();

		for (int i = 0; i < this.handPlayers.size(); i++) {
			HandPanel handPanel = this.handPlayers.get(i);
			// 1.1 Checking if the player is the winner
			if (handPanel.equals(handPanelWinner)) {
				handPanel.restore(handPanelWinner.getCardsInHand() - 1);
			} else {
				handPanel.restore(handPanelWinner.getCardsInHand());
			}
		}

		// 1. Looping on hand panels
		for (HandPanel handPanel : this.handPlayers) {
			// 1.1 Checking if the player is the winner
			if (handPanel.equals(handPanelWinner)) {
				handPanel.restore(handPanelWinner.getCardsInHand() - 1);
			} else {
				handPanel.restore(handPanelWinner.getCardsInHand());
			}

			// add(handPanel);
			handPanel.setupUI();

		}

		this.revalidate();
		this.repaint();

	}

	/**
	 * Returns the decks panel instance.
	 */
	public DecksPanel getDecksPanel() {
		return this.decksPanel;
	}

	/**
	 * Returns the human panel instance.
	 */
	public HumanPanel getHumanPanel() {
		return this.human;
	}

	/**
	 * Returns the list of hand panels representing all players.
	 */
	public List<HandPanel> getHandsPlayers() {
		return this.handPlayers;
	}

	/**
	 * Returns the hand panel at the specified index.
	 */
	public HandPanel getHandPlayer(int index) {
		return this.handPlayers.get(index);
	}

	/**
	 * Returns the hand panel of the current player based on the current hand index.
	 */
	public HandPanel getCurrentHandPlayer() {
		return this.handPlayers.get(this.currentHandIndex);
	}

	/**
	 * Sets the index of the current hand player.
	 */
	public void setCurrentHandIndex(int index) {
		this.currentHandIndex = index;
	}
}
