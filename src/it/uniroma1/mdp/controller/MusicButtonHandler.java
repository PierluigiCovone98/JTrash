package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.uniroma1.mdp.view.EffectAudioManager;
import it.uniroma1.mdp.view.MusicAudioManager;
import it.uniroma1.mdp.view.MusicButton;


/**
 * The MusicButtonHandler class implements the ActionListener interface to handle events
 * triggered by the MusicButton.
 */
public class MusicButtonHandler implements ActionListener{

	/**
	 * Handles the actionPerformed event triggered by the MusicButton. It manages
	 * the audio settings for music, toggling between mute and unmute states.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Managing audio
		EffectAudioManager effectAudio = AudioManagerController.getEffectAudioManagerInstance();
		MusicAudioManager musicAudio = AudioManagerController.getMusicAudioManagerInstance();
		
		// 1. Play the click sound
		effectAudio.playClick();
		
		// 2. Saving the reference of the EffectsButton instance
		MusicButton musicB = JTrash.getMainFrameInstance().getMenuManager().getSettingsMenu().getMusicButton();
		
		// 3. Setting false the bTurnedOn value of the EffectAudioManager
		if (musicAudio.isTurnedOn()) { 	// Effects are activated
			// 1.1. Making effects disabled
			musicAudio.setTurnedOn(false);
			
			// 1.2. Changing the view of the button
			musicB.setIcon(MusicButton.PRESSED_ICON_PATH);
			musicB.setRolloverIcon(MusicButton.ICON_PATH);
			
		} else {	// effects are disabled
			// 1.1. Making effects disabled
			musicAudio.setTurnedOn(true);
			// 1.2. Changing the view of the button
			musicB.setIcon(MusicButton.ICON_PATH);
			musicB.setRolloverIcon(MusicButton.PRESSED_ICON_PATH);
			
			
		}
		musicB.setBorder(null);
	}

}
