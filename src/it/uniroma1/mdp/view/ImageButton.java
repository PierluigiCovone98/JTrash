package it.uniroma1.mdp.view;

import javax.swing.Icon;
import javax.swing.JButton;

import it.uniroma1.mdp.view.utilities.ImageUtility;

/**
 * An abstract class representing an image button with customizable icons and
 * dimensions. Subclasses must implement the setup method to configure button
 * settings. Provides methods to set icons and dimensions, and includes utility
 * methods for icon modification.
 */
public abstract class ImageButton extends JButton {
	// Constants
	public static final long serialVersionUID = -5949709058196809803L;

	// Fields
	private String iconPath;
	private String rolloverIconPath;
	private int width;
	private int height;

	/*
	 * Constructor has been declared protected to allow to use the Builder Pattern.
	 * Each instance of this class has too many arguments to specify to be created.
	 */
	public ImageButton(String iconPath, String rolloverIconPath, int width, int height) {
		super();

		// Initializing fields
		this.iconPath = iconPath;
		this.rolloverIconPath = rolloverIconPath;
		this.width = width;
		this.height = height;
	}

	/**
	 * Allows to set ups button settings. This method must be implemented by
	 * subclasses.
	 * 
	 */
	protected void setup() {
		if (this.width > 0 && this.height > 0) {
			if (this.iconPath != null)
				// 1. Adding icon
				setIcon(this.iconPath);
			if (this.rolloverIconPath != null)
				// 2. Adding rolloverIcon
				setRolloverIcon(this.rolloverIconPath);
		}
		// Adding details
		addDetails();
	}

	/**
	 * Adds technical details for instances of this class; it removes borders from
	 * buttons instances.
	 */
	private void addDetails() {
		setBorder(null);
	}

	/**
	 * Get the string representing the path of the icon.
	 */
	public String getIconPath() {
		return this.iconPath;
	}

	/**
	 * Get the string representing the path of the rollover icon.
	 */
	public String getRolloverIconPath() {
		return this.rolloverIconPath;
	}

	/**
	 * Get the width of the button.
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Get the height of the button.
	 */
	public int getHeigth() {
		return this.height;
	}

	/**
	 * Sets the main icon for the button using the provided image path. If the path
	 * is not null, it sets the icon with a modified version that is resized to the
	 * specified width and height.
	 */
	public void setIcon(String iconPath) {
		if (iconPath != null)
			super.setIcon(getModIcon(iconPath));
	}

	/**
	 * Sets the rollover icon for the button using the provided image path. If the
	 * path is not null, it sets the rollover icon with a modified version that is
	 * resized to the specified width and height.
	 */
	public void setRolloverIcon(String rolloverIconPath) {
		if (rolloverIconPath != null)
			super.setRolloverIcon(getModIcon(rolloverIconPath));
	}

	/**
	 * Sets the pressed icon for the button using the provided image path. If the
	 * path is not null, it sets the pressed icon with a modified version that is
	 * resized to the specified width and height.
	 */
	public void setPressedIcon(String pressedIconPath) {
		if (pressedIconPath != null)
			super.setPressedIcon(getModIcon(pressedIconPath));
	}

	/**
	 * Returns a modified icon resized to the specified width and height. This
	 * method is used internally to resize icons consistently.
	 */
	private Icon getModIcon(String path) {
		return ImageUtility.resizeIcon(path, this.width, this.height);
	}

	/**
	 * Sets the width of the button.
	 */
	protected void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Sets the height of the button.
	 */
	protected void setHeight(int height) {
		this.height = height;
	}
}
