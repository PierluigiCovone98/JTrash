package it.uniroma1.mdp.view.utilities;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Utility class for handling image-related operations, providing static
 * methods.
 */
public class ImageUtility {

	/**
	 * Creates and returns an Icon instance (runtime) of the specified dimension.
	 * 
	 * @param path   The String path of the image file.
	 * @param width  The desired width of the resized image.
	 * @param height The desired height of the resized image.
	 * @return An Icon instance of the resized image, or null if any parameter is
	 *         invalid.
	 */
	public static Icon resizeIcon(String path, int width, int height) {
		if (path != null && width > 0 && height > 0) {
			Image originalImage = new ImageIcon(path).getImage();
			Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			return new ImageIcon(scaledImage);
		} else
			return null;
	}

	/**
	 * Returns a resized Image instance of the specified dimension.
	 * 
	 * @param path   The String path of the image file.
	 * @param width  The desired width of the resized image.
	 * @param height The desired height of the resized image.
	 * @return A resized Image instance, or null if any parameter is invalid.
	 */
	public static Image getResizedImage(String path, int width, int height) {
		if (path != null && width > 0 && height > 0) {
			Image originalImage = new ImageIcon(path).getImage();
			return originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		} else
			return null;
	}

}
