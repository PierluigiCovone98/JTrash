package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.uniroma1.mdp.view.EffectAudioManager;
import it.uniroma1.mdp.view.EffectsButton;

/**
 * The EffectsButtonHandler class implements ActionListener interface to handle
 * actions performed on the EffectsButton. It manages the audio effects and
 * updates the button's visual state accordingly.
 */
public class EffectsButtonHandler implements ActionListener {

	/**
	 * Handles the action performed when the EffectsButton is clicked. It toggles
	 * the state of the audio effects and updates the button's visual state.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Managing audio
		EffectAudioManager effectAudio = AudioManagerController.getEffectAudioManagerInstance();

		// 1. Play the click sound
		effectAudio.playClick();

		// 2. Saving the reference of the EffectsButton instance
		EffectsButton effectsB = JTrash.getMainFrameInstance().getMenuManager().getSettingsMenu().getEffectsButton();

		// 3. Setting false the bTurnedOn value of the EffectAudioManager
		if (effectAudio.isTurnedOn()) { // Effects are activated
			// 1.1. Making effects disabled
			effectAudio.setTurnedOn(false);

			// 1.2. Changing the view of the button
			effectsB.setIcon(EffectsButton.PRESSED_ICON_PATH);
			effectsB.setRolloverIcon(EffectsButton.ICON_PATH);

		} else { // effects are disabled
			// 1.1. Making effects disabled
			effectAudio.setTurnedOn(true);
			// 1.2. Changing the view of the button
			effectsB.setIcon(EffectsButton.ICON_PATH);
			effectsB.setRolloverIcon(EffectsButton.PRESSED_ICON_PATH);

		}
		effectsB.setBorder(null);

	}

}
