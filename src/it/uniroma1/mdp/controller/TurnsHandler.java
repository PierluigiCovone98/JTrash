package it.uniroma1.mdp.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.uniroma1.mdp.model.GameStatusManager;
import it.uniroma1.mdp.view.game.TurnLabel;
import it.uniroma1.mdp.view.utilities.StringFormatter;


/**
 * Handles the turn switching between players during the game.
 * This listener updates the turn label to indicate whose turn it is and controls player interactions based on whether the current player is human or computer.
 */
public class TurnsHandler implements ActionListener {

	// FIELDS
	
	// --- model
	private GameStatusManager gameStatusManager;
	
	
	// --- view
	private TurnLabel turnLabel;
	
	/**
	 * Constructs a TurnsHandler instance. Initializes the model and view fields.
	 */
	public TurnsHandler() {
		// --- model
		this.gameStatusManager = JTrash.getGameInstance().getGameStatusManager();
		
		// --- view
		this.turnLabel = JTrash.getGameFrameInstance().getHuDisplay().getTurnLabel();
	}	
	
	
	/**
     * Performs the necessary actions when a turn event occurs.
     * Updates the turn label to indicate whose turn it is and controls player interactions accordingly.
     * If the current player is a human player, it enables interaction with the game frame.
     * If the current player is a computer player, it disables interaction with the game frame and triggers the computer player's turn.
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Saving the nickname of the current player
		String currentPlayerNickname = this.gameStatusManager.getCurrentPlayer().getNickname();
		System.out.println("Is the turn of: " + currentPlayerNickname);
		

		// If the current player is not a human player
		if (! this.gameStatusManager.isCurrentPlayerHuman()) {
			// Displaying the bot's label
			this.turnLabel.setText(currentPlayerNickname);
			
			// Making the frame not enabled for the player
			JTrash.getGameFrameInstance().setEnabled(false);
			// Fires up the handler for a computer player, for a specific computer player
			ComputerPlayerListener computerPlayerListener = new ComputerPlayerListener(this.gameStatusManager.getCurrentPlayer());
		    ActionEvent computerTurn = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Bot Turn");
		    computerPlayerListener.actionPerformed(computerTurn);
		} else {
			// Displaying the user's label
			currentPlayerNickname = StringFormatter.formatString(currentPlayerNickname, "green");
			this.turnLabel.setText(currentPlayerNickname);
			
			// Making it enable again for the human to interacts with the frame
			JTrash.getGameFrameInstance().setEnabled(true);
			//JTrash.getGameInstance().startTurn();
		}	

	}

}
