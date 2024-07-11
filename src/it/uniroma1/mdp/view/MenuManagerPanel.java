package it.uniroma1.mdp.view;

import java.awt.CardLayout;

import javax.swing.JPanel;

/**
 * Manages the switching between different menus using a CardLayout within a JPanel.
 */
public class MenuManagerPanel extends JPanel {
	// CONSTANTS
	public static final long serialVersionUID = 3901357234358862440L;

	
	// FIELDS
	private CardLayout cardLayout;
	
	private MainMenuPanel mainMenu;
	private NewGameMenuPanel newGameMenu;
	private UserProfileMenuPanel userProfileMenu;
	private SettingsMenuPanel settingsMenu;
	
	
	
	// METHODS
	/**
     * Constructs an instance of this panel initializing menu panels.
     */
	public MenuManagerPanel() {
		super();
	
		// 1. Initializing fields
		this.cardLayout = new CardLayout();
		this.mainMenu = new MainMenuPanel();
		this.newGameMenu = new NewGameMenuPanel();
		this.settingsMenu = new SettingsMenuPanel();
		this.userProfileMenu = new UserProfileMenuPanel();
	}
	
	
	/**
	 * Configure the UI of this panel instance, setting the layout manager, adding
	 * components, eventually action listeners and technical details.
	 */
	protected void setupUI() {
		// Useful to easy switch between menus
		setLayout(this.cardLayout); 

		// Setting fields UI & Adding menus as cards for this panel 
		this.mainMenu.setupUI();
		this.add(this.mainMenu, "mainMenu");
		
		this.newGameMenu.setupUI();
		this.add(this.newGameMenu, "newGameMenu");
		
		this.userProfileMenu.setupUI();
		this.add(this.userProfileMenu, "userProfileMenu");

		this.settingsMenu.setupUI();
		this.add(this.settingsMenu, "settingsMenu");
		
		// Adding technical details
		addDetails();
	}


	/**
	 * Adds technical details for this panel instance. 
	 */
	private void addDetails() {
		setOpaque(false);
	}
	
	
	 /**
     * Returns the CardLayout used by this panel.
     */
	@Override
	public CardLayout getLayout() {
		return this.cardLayout;
	}
	
	 /**
     * Returns the MainMenuPanel instance.
     */
	public MainMenuPanel getMainMenu() {
		return this.mainMenu;
	}
	
	 /**
     * Returns the NewGameMenuPanel instance.
     */
	public NewGameMenuPanel getNewGameMenu() {
		return this.newGameMenu;
	}
	
    /**
     * Returns the UserProfileMenuPanel instance.
     */
	public UserProfileMenuPanel getUserProfileMenu() {
		return this.userProfileMenu;
	}
	
	 /**
     * Returns the SettingsMenuPanel instance.
     */
	public SettingsMenuPanel getSettingsMenu() {
		return this.settingsMenu;
	}
}

