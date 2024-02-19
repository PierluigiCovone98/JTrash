package it.uniroma1.mdp.view.utilities;

/**
 * The StringFormatter class provides a utility method for formatting strings.
 * This class is specifically designed to format strings in a way that they can
 * be used effectively in GUI components.
 */
public class StringFormatter {

	/**
	 * Formats a string with HTML styling, making it suitable for enhanced GUI text
	 * display. Applies bold formatting, sets font size, family, and color.
	 */
	public static String formatString(String string) {
		return formatString(string, "black");
	}
	
	
	/**
	 * Formats a string with HTML styling, making it suitable for enhanced GUI text
	 * display. Applies bold formatting, sets font size, family, and color.
	 * Can specify the color too.
	 */
	public static String formatString(String string, String color) {
		return "<html><b style='font-size: 18px; font-family: Nunito; color: " + color + "; '>" + string + "</b></html>";
	}
}
