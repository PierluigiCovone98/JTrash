package it.uniroma1.mdp.view;

import javax.swing.JPanel;

/**
 * The SettingsMenuPanel class represents a panel in the application's settings menu.
 * It provides options to configure effects and music settings.
 */
public class SettingsMenuPanel  extends JPanel {
	
	// CONSTANTS
	public static final long serialVersionUID = 9210995060204497483L;
	
	// FIELDS
	private BackButton backB;
	private EffectsButton effectsB;
	private MusicButton musicB;
	// METHODS
	
	 /**
     * Constructor initializes the settings menu panel by creating effect and music buttons.
     */
	public SettingsMenuPanel() {
		// 1. Initialize fields
		this.backB = new BackButton();
		this.effectsB  = new EffectsButton();
		this.musicB = new MusicButton();
		
	}

	/**
	 * Configure the UI of this panel instance, setting the layout manager, adding
	 * components, eventually action listeners and technical details.
	 */
	protected void setupUI() {
		// 1. Placing component where I need
		setLayout(null);


		add(this.backB);
		this.backB.setBounds(100, 220, 100,100); // x, y, width, height
		
		
		add(this.effectsB);
		this.effectsB.setBounds(230, 50, 100, 100); // x, y, width, height
		
		
		add(this.musicB);
		this.musicB.setBounds(230, 200, 100, 100); // x, y, width, height
		
		// Add details
		addDetails();
	}
	
	/**
	 * Adds technical details for this panel instance
	 */
	private void addDetails() {
		setOpaque(false);
	}


	/**
	 * Returns the EffectsButton instance.
	 */
	public BackButton getBackButton() {
		return this.backB;
	}
	/**
	 * Returns the EffectsButton instance.
	 */
	public EffectsButton getEffectsButton() {
		return this.effectsB;
	}
	/**
	 * Returns the EffectsButton instance.
	 */
	public MusicButton getMusicButton() {
		return this.musicB;
	}

}
