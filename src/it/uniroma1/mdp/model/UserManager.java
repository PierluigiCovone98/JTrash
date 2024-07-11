package it.uniroma1.mdp.model;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.uniroma1.mdp.model.players.HumanPlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class UserManager {

	// FIELDS
	public static HumanPlayer humanPlayer;

	// METHODS
	/**
	 * This method is responsible for creating a new or uploading an existing
	 * instance of a HumanPlayer class.
	 */
	public static void manageUser(String nickname) {
		// 0. Converting the nickname in lower case
		nickname = nickname.toLowerCase();
		System.out.println("Inserted nickname: " + nickname);

		// 1. Searching for the nickname in the existing list of nicknames
		boolean bRegistered = isTheNicknameRegistered(nickname);
		System.out.println("Is " + nickname + " present? " + bRegistered);

		// 2. If the nickname is not registered, register it & creates a file with
		// information about the player player
		if (!bRegistered) {
			registerNickname(nickname);
			createHumanUser(nickname);
			registerHumanUser(nickname);
		} else {
			uploadHumanUser(nickname);
		}
		System.out.println(humanPlayer.getNickname() + " " + humanPlayer.getWon() + " " + humanPlayer.getLost());

	}

	/**
	 * Checks if the provided nickname is already registered.
	 */
	public static boolean isTheNicknameRegistered(String nickname) {
		
		
		// 1. Creating a new instance of the file
		File file = new File("users/nicknames.txt");

		// 2. If the does not exist
		if (!file.exists()) {
			createFile(file);
			// 2.1. If the file does not exist, the nickname can't be present
			return false;
		}

		// 3. Otherwise, the file exists
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			// 3.1 Checks if the nickname is already present
			return reader.lines().anyMatch(line -> line.equals(nickname));

		} catch (IOException e) {
			e.printStackTrace();
		}

		// The nickname is not present
		return false;

	} // end of the isTheNicknameRegistered method.

	/**
	 * Creates an empty text file if it is not present-
	 * 
	 * @param file
	 */
	private static void createFile(File file) {
		try (FileWriter writer = new FileWriter(file)) {
			// Creates a new empty text file
			writer.write("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // end of createFile() method

	/**
	 * Registers a new nickname by adding it to the file.
	 */
	public static void registerNickname(String nickname) {
		// 1. Creating an instance of the file
		File file = new File("users/nicknames.txt");

		try (FileWriter writer = new FileWriter(file, true)) {
			// Add a new line in the file
			writer.write(nickname + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // end of the registerNickname() method

	
	/**
	 * Delete the given nickname from the nicknames.txt file.
	 */
	public static void deleteNickname(String nickname) {
		// 1. Creating an instance of the file
		File file = new File("users/nicknames.txt");

		// 2. Reading the content of the file into a List of Lines
		List<String> lines = new ArrayList<>();
		// 3. Reading nicknames form the file nicknames.txt
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			  lines = reader.lines().filter(line -> !line.contains(nickname)).collect(Collectors.toList());
			  reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 4. Writing the updated lines back to the file
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			for (String line : lines) {
						writer.write(line);
						writer.newLine();
					}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // end of the deleteNickname() method
	
	
	/**
	 * Creates an instance of the HumanPlayer class
	 * 
	 * @param nickname
	 */
	public static void createHumanUser(String nickname) {
		// 1. Creating the instance of the human player
		humanPlayer = new HumanPlayer(nickname, 10);	// 10 is the first number of cards in hand
		// NOTE: Values for WON and LOST are both 0;
	}

	/**
	 * Registers a new human user by adding their nickname to a file along with
	 * initial values for games won and lost.
	 */
	public static void registerHumanUser(String username) {
		// 1. Saving the file in which i'm storing player informations
		File file = new File("users/" + username + ".csv");

		try (FileWriter writer = new FileWriter(file, true)) {
			// 2. Saving informations in Comma-Separated Values, CSV.
			String playerInfo = String.format("%s,%d,%d\n", humanPlayer.getNickname(), humanPlayer.getWon(),
					humanPlayer.getLost());
			writer.write(playerInfo);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // end of the registerHumanUser() method

	/**
	 * Loads user information from a CSV file.
	 * 
	 * @param username The username for which to load information.
	 * @return An instance of HumanPlayer with loaded information, or null if not
	 *         found.
	 */
	public static void uploadHumanUser(String nickname) {

		nickname = nickname.toLowerCase();

		// 0. Creating a new instance of the HumanPlayer
		humanPlayer = new HumanPlayer(nickname, 10);

		// 1. Saving the file
		File file = new File("users/" + nickname + ".csv");

		// 2. If the file dose not exists
		if (!file.exists()) {
			System.out.println("The .csv related to this user does not exist");
			return;
		}

		// 3. Loading informations
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			// 3.1 Read the first line (assuming there is only one line for the given user)
			String line = reader.readLine();
			if (line != null) {
				// 3.2 Parse the CSV line and create a HumanPlayer instance
				String[] informations = line.split(",");
				// 3.3. Checking that informations a
				humanPlayer.setNickname(informations[0]);
				System.out.println(informations[0]);
				humanPlayer.setWon(Integer.parseInt(informations[1]));
				System.out.println(informations[1]);
				humanPlayer.setLost(Integer.parseInt(informations[2]));
				System.out.println(informations[2]);
				reader.close();
			} else
				System.out.println("Invalid line in the .csv file");
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
		}
	} // end of the uploadHumanUser method

	/**
	 * Updates user information in the CSV file.
	 */
	public static void updateUserInformations() {
		File file = new File("users/" + humanPlayer.getNickname() + ".csv");

		// Check if the file exists
		if (!file.exists()) {
			System.out.println("User file not found for " + humanPlayer.getNickname());
			return;
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(file));
				BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			// 1. Reading informations from the existing line
			String firstLine = reader.readLine();
			// String[] informations = firstLine.split(",");
			// 2. Updating it with current values
			String newLine = String.format("%s,%d,%d", humanPlayer.getNickname(), humanPlayer.getWon(),
					humanPlayer.getLost());
			// 3. Writing the updated line
			writer.write(newLine + "\n");

			reader.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Switches occurrences of the oldNickname with the newNickname in the
	 * "users/nicknames.txt" file.
	 * 
	 */
	public static void switchNicknamesInTheList(String oldNickname, String newNickname) {
		// 1. Creating an instance of the file
		File file = new File("users/nicknames.txt");

		// 2. Reading the content of the file into a List of Lines
		List<String> lines = new ArrayList<>();
		// 3. Reading nicknames form the file nicknames.txt
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			reader.lines().forEach(lines::add);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 4. Replacing oldNickname with newNickname in the List
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).equals(oldNickname)) {
				lines.set(i, newNickname);
				break; // Stop iterating once replaced
			}
		}

		// 4. Writing the updated lines back to the file
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			for (String line : lines) {
				writer.write(line);
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * Removes a user by deleting the specific .csv file.
	 */
	public static void removeHumanUser(String nickname) {
	    // 1. Creating an instance of the file to be removed
	    File fileToRemove = new File("users/" + nickname + ".csv");

	    // 2. Check if the file exists
	    if (fileToRemove.exists()) {
	        // 2.1 Attempt to delete the file
	        if (fileToRemove.delete()) {
	            System.out.println("User file deleted successfully: " + nickname);
	        } else {
	            System.out.println("Failed to delete user file: " + nickname);
	        }
	    } else {
	        System.out.println("User file not found: " + nickname);
	    }
	}
	
	
	/**
	 * Returns the list of available players.
	 */
	public static List<String> getNicknames() {
		// 1. Creating a new instance of the file
		File file = new File("users/nicknames.txt");


		// 2. Returning the list of nicknames
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			// 3.1 Checks if the nickname is already present
			return reader.lines().toList();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// The nickname is not present
		return null;
	}
	
	
	
	
}
