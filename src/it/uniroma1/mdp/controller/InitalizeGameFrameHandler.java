package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.uniroma1.mdp.view.game.GameFrame;

/**
 * Handles the action event triggered by initializing the game frame.
 */
public class InitalizeGameFrameHandler implements ActionListener {

	/**
	 * Hides the previous MainFrame instance and sets the new one as visible by
	 * creating and setting up the game frame instance.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// 0. Adding the sound
		AudioManagerController.getEffectAudioManagerInstance().playClick();
		// 1. Hiding the previous MainFrame instance & setting the new one as visible
		JTrash.getMainFrameInstance().setVisible(false); // Note that it is still present
		// 2. Creating the (unique) instance of the GameFrame class
		GameFrame gameFrameInstance = JTrash.getGameFrameInstance();
		// 3. Setting it ups
		gameFrameInstance.setupUI();
		// 4. Making it visible
		gameFrameInstance.setVisible(true);
	}

}
