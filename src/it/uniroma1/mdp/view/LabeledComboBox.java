package it.uniroma1.mdp.view;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class represents the design a Combo Box with a label for this
 * application. It instances are used especially when the user has to settings
 * for a new Game.
 */
public class LabeledComboBox<E> extends JPanel {
	// Constants
	public static final long serialVersionUID = 6857527673114059730L;

	// Fields
	private JLabel label;
	private JComboBox<E> comboBox;

	// Methods
	/**
	 * Constructor
	 */
	public LabeledComboBox(String stringLabel, E[] items) {
		if (stringLabel != null && items != null) {
			this.label = new JLabel(formatString(stringLabel));
			this.comboBox = new JComboBox<E>(items);
		}
	}

	/**
	 * This is a private method that is used to set the style of the label.
	 * 
	 * @param string
	 * @return
	 */
	private String formatString(String string) {
		return "<html><b style='font-size: 18px; font-family: Nunito;'>" + string + "</b></html>";
	}

	/**
	 * Configure the UI of this panel instance, setting the layout manager, adding
	 * components, eventually action listeners and technical details.
	 */
	protected void setupUI() {
		setLayout(new BorderLayout()); // Easy to place the label at the top and the combo box at the bottom

		// 2. Adding components
		add(this.label, BorderLayout.PAGE_START);
		add(this.comboBox, BorderLayout.PAGE_END);

		// Add details
		addDetails();
	}

	/**
	 * Adds 'technical details' for this panel instance.
	 */
	private void addDetails() {
		setOpaque(false);
		setAlignmentX(CENTER_ALIGNMENT);
	}

	/**
	 * Get the JLabel of the panel.
	 */
	public JLabel getLabel() {
		return this.label;
	}

	/**
	 * Get the JComboBox of the panel.
	 */
	public JComboBox<E> getComboBox() {
		return this.comboBox;
	}
}
