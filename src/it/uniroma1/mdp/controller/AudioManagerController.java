package it.uniroma1.mdp.controller;

import it.uniroma1.mdp.view.EffectAudioManager;
import it.uniroma1.mdp.view.MusicAudioManager;

public class AudioManagerController {

	// Singleton instance of the EffectAudioManager
	private static EffectAudioManager effectAudioManagerInstance;
	// Singleton instance of the MusicAudioManager
	private static MusicAudioManager musicAudioManagerInstance;

	/**
	 * Retrieves the singleton instance of the EffectAudioManager. If the instance
	 * is not initialized, it creates a new one.
	 */
	public static EffectAudioManager getEffectAudioManagerInstance() {
		if (effectAudioManagerInstance == null)
			effectAudioManagerInstance = new EffectAudioManager();
		return effectAudioManagerInstance;
	}

	/**
	 * Retrieves the singleton instance of the MusicAudioManager. If the instance is
	 * not initialized, it creates a new one.
	 */
	public static MusicAudioManager getMusicAudioManagerInstance() {
		if (musicAudioManagerInstance == null)
			musicAudioManagerInstance = new MusicAudioManager();
		return musicAudioManagerInstance;
	}

}
