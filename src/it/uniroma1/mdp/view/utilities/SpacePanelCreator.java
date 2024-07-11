package it.uniroma1.mdp.view.utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JPanel;


/**
 * Utility class for creating customized space panels with specified dimensions and colors.
 * This class provides methods to generate JPanel instances representing empty space.
 */

public class SpacePanelCreator {
	/**
	 * Allows to create white customized space panel, in terms of dimensions (width and height).
	 * @param width
	 * @param height
	 * @return
	 */
	public static JPanel createSpacePanel(int width, int height) {
		return createSpacePanel(width, height, 255, 255, 255);
	}
	
	/**
	 * Allows to create customized space panel, in terms of dimensions (width and height) and
	 * color. If no values for R,G,B arguments are given, the default color is white.
	 * Look at the overload of this method.
	 * @param width
	 * @param height
	 * @param R
	 * @param G
	 * @param B
	 * @return
	 */
	public static JPanel createSpacePanel(int width, int height, int R, int G, int B) {
		// Create the root panel
		JPanel rootPanel = new JPanel();
		// Adding the white space component
		rootPanel.add( createSpaceComponent(width, height) );
		// Setting the white background
		rootPanel.setBackground( new Color(R, G, B) );
		return rootPanel;
	}
	
	/**
	 * Returns a Rigid area Component with specifies width and height dimensions.
	 * @param width
	 * @param height
	 * @return
	 */
	private static Component createSpaceComponent(int width, int height) {
		return Box.createRigidArea( new Dimension(width, height) );
	}
}
