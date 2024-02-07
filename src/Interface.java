
/**
 * @author Lukas Bozinov 
 * Description of Class: A class that takes user input commands and performs operations based on those commands
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Interface {

	public static void main(String[] args) throws DictionaryException, IOException {

		BSTDictionary dictionary = new BSTDictionary(); // declare a new dictionary
		String userCommand; // prepare a string variable for user input

		dictionary = startInserting(args[0]); // insert all nodes into the dictionary

		do {

			// accept user input
			StringReader keyboard = new StringReader();
			userCommand = keyboard.read("Enter a command: ");

			/*
			 * This next block of if statements is simply testing different commands and
			 * performing certain operations based on the commands. I will not comment all
			 * if statements as most of them are identical (save for a few small changes
			 * which I will denote)
			 */
			if (userCommand.toLowerCase().startsWith("define")) {
				String[] splitString = userCommand.split(" ", 2); // split the user command into two individual words
				String label = splitString[1]; // get the label from the user command

				Record returnedRec = dictionary.get(new Key(label.toLowerCase(), 1)); // use the get method on the
																						// dictionary and store it in a
																						// record

				if (returnedRec == null) { // if null, the record doesn't exist
					System.out.println("The word " + label + " is not in the ordered dictionary.");// tell the user it
																									// doesn't exist
				} else {// otherwise if it does exist
					String retString = returnedRec.getDataItem(); // get the data item returned record
					System.out.println(retString); // print the string
				}

			} else if (userCommand.toLowerCase().startsWith("translate")) {
				String[] splitString = userCommand.split(" ", 2);
				String label = splitString[1];

				Record returnedRec = dictionary.get(new Key(label.toLowerCase(), 2)); // same as "define" block, but
																						// with type 2 for translations

				if (returnedRec == null) {
					System.out.println("There is no definition for the word " + label + ".");
				} else {
					String retString = returnedRec.getDataItem().replace("/", ""); // replaces the / at the beginning of
																					// every data item for translations
																					// with nothing
					System.out.println(retString); // prints the string
				}

			} else if (userCommand.toLowerCase().startsWith("sound")) {
				String[] splitString = userCommand.split(" ", 2);
				String label = splitString[1];

				Record returnedRec = dictionary.get(new Key(label.toLowerCase(), 3));// changes to type 3 for sound

				if (returnedRec == null) {
					System.out.println("There is no sound file for " + label + ".");
				} else {
					String retString = returnedRec.getDataItem().replace("-", ""); // replaces the first character

					SoundPlayer player = new SoundPlayer(); // creates a new soundplayer object
					try {
						player.play(retString); // attempts to play the file
					} catch (MultimediaException e) {
						// if the play fails, return the error message below
						System.out.println(e.getMessage());
						System.out.println("There was an error playing your sound file.");
					}

				}

			} else if (userCommand.toLowerCase().startsWith("play")) {
				String[] splitString = userCommand.split(" ", 2);
				String label = splitString[1];

				Record returnedRec = dictionary.get(new Key(label.toLowerCase(), 4)); // set type to 4 for music files

				if (returnedRec == null) {
					System.out.println("There is no music file for " + label + ".");
				} else {
					String retString = returnedRec.getDataItem().replace("+", "");
					SoundPlayer player = new SoundPlayer();
					try {
						player.play(retString); // try to play file
					} catch (MultimediaException e) {
						System.out.println(e.getMessage());
						System.out.println("There was an error playing your music file.");
					}

				}

			} else if (userCommand.toLowerCase().startsWith("say")) {
				String[] splitString = userCommand.split(" ", 2);
				String label = splitString[1];

				Record returnedRec = dictionary.get(new Key(label.toLowerCase(), 5)); // set type to 5 for voice files

				if (returnedRec == null) {
					System.out.println("There is no voice file for " + label + ".");
				} else {
					String retString = returnedRec.getDataItem().replace("*", ""); // replace char
					SoundPlayer player = new SoundPlayer();
					try {
						player.play(retString); // play voice file
					} catch (MultimediaException e) {
						System.out.println(e.getMessage());
						System.out.println("There was an error playing your voice file.");
					}

				}

			} else if (userCommand.toLowerCase().startsWith("show")) {
				String[] splitString = userCommand.split(" ", 2);
				String label = splitString[1];

				Record returnedRec = dictionary.get(new Key(label.toLowerCase(), 6)); // change type to 6

				if (returnedRec == null) {
					System.out.println("There is no image file for " + label + ".");
				} else {
					String retString = returnedRec.getDataItem();
					PictureViewer viewer = new PictureViewer(); // create a new picture viewer object
					try {
						viewer.show(retString); // show the picture assigned to the string
					} catch (MultimediaException e) {
						System.out.println(e.getMessage()); // print error if needed
						System.out.println("There was an error showing your picture file.");
					}

				}

			} else if (userCommand.toLowerCase().startsWith("animate")) {
				String[] splitString = userCommand.split(" ", 2);
				String label = splitString[1];

				Record returnedRec = dictionary.get(new Key(label.toLowerCase(), 7));// change type to 7 for animation

				if (returnedRec == null) {
					System.out.println("There is no animated image file for " + label + ".");
				} else {
					String retString = returnedRec.getDataItem();
					PictureViewer viewer = new PictureViewer(); // same picture viewer initialization
					try {
						viewer.show(retString); // same try/catch statement
					} catch (MultimediaException e) {
						System.out.println(e.getMessage());
						System.out.println("There was an error showing your animated file.");
					}

				}

			} else if (userCommand.toLowerCase().startsWith("browse")) {
				String[] splitString = userCommand.split(" ", 2);
				String label = splitString[1];

				Record returnedRec = dictionary.get(new Key(label.toLowerCase(), 8)); // change to type 8 for browsing

				if (returnedRec == null) {
					System.out.println("There is no webpage called " + label + ".");
				} else {
					String retString = returnedRec.getDataItem();
					ShowHTML browser = new ShowHTML(); // create a new ShowHTML object
					browser.show(retString); // show the html in browser

				}

			} else if (userCommand.toLowerCase().startsWith("delete")) {
				String[] splitString = userCommand.split(" ", 3);
				String label = splitString[1];
				int key = Integer.parseInt(splitString[2]); // convert from string to int via parsing

				Record returnedRec = dictionary.get(new Key(label.toLowerCase(), key)); // looks for the key in the
																						// dictionary

				if (returnedRec == null) { // null = not found
					System.out.println(
							"No record in the ordered dictionary has key (" + label.toLowerCase() + ", " + key + ").");
				} else {// else = found

					dictionary.remove(returnedRec.getKey()); // deletes the node from the dictionary

				}

			} else if (userCommand.toLowerCase().startsWith("add")) {
				String[] splitString = userCommand.split(" ", 4);
				String label = splitString[1];
				int key = Integer.parseInt(splitString[2]); // convert string to int
				String data = splitString[3];

				Record returnedRec = new Record((new Key(label.toLowerCase(), key)), data);
				Record getRec = dictionary.get(new Key(label.toLowerCase(), key));

				if (getRec == null) { // null == not found (we should insert)
					dictionary.put(returnedRec);

				} else { // !null ==found (we shouldn't insert)

					System.out.println("A record with the given key (" + label.toLowerCase() + ", " + key
							+ ") is already in the ordered dictionary.");

				}

			} else if (userCommand.toLowerCase().startsWith("last")) {

				Record inDict = dictionary.largest(); // get largest from the dictionary and format string output
				System.out.println(
						inDict.getKey().getLabel() + "," + inDict.getKey().getType() + "," + inDict.getDataItem());

			} else if (userCommand.toLowerCase().startsWith("first")) {
				Record inDict = dictionary.smallest(); // get smallest from dictionary and format string output
				System.out.println(
						inDict.getKey().getLabel() + "," + inDict.getKey().getType() + "," + inDict.getDataItem());

			} else if (userCommand.toLowerCase().startsWith("list")) {
				String[] splitString = userCommand.split(" ", 2);
				String prefix = splitString[1];
				boolean match = false; // set match (if something is found) to false

				Key hasKey = new Key(prefix, 1); // get a new key with the given prefix and type 1 (default type)
				Record inDict = dictionary.get(hasKey); // get the record with the coresponding key

				if (inDict != null) { // if not null
					match = true; // a match has been found
					System.out.println(inDict.getKey().getLabel());// print the match
				}

				inDict = dictionary.successor(hasKey);// check if the successor (the next node in lexicographical order)
														// has anything that matches

				while (inDict != null && inDict.getKey().getLabel().toLowerCase().startsWith(prefix)) { // print more
																										// matches if
																										// found via the
																										// while loop
					match = true; // set the match to true if found
					System.out.println(inDict.getKey().getLabel()); // print the matched node
					inDict = dictionary.successor(inDict.getKey()); // get the successor again
				}

				if (!match) // if no matches were found, tell the user
					System.out.println("No label attributes in the ordered dictionary start with prefix " + prefix);
			} else {
				System.out.println("Invalid command."); // if an invalid command is entered, tell the user and re-prompt
			}

		} while (!userCommand.equalsIgnoreCase("exit")); // the program ends when the user types exit

		System.exit(0); // exits the program with exit code 0

	}

	private static BSTDictionary startInserting(String progArg) {
		BSTDictionary dictionary = new BSTDictionary(); // creates a new dictionary
		BufferedReader input = null; // sets input to null to initialize
		try {
			input = new BufferedReader(new FileReader(progArg)); // creates a new bufferedreader using program arguments
																	// as the file to read
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			// initialize strings and ints for storage of components of the record/key
			String keyLine, recData;
			int keyType;

			// as long as the file has more lines to read, keep inserting
			while ((keyLine = input.readLine()) != null) {

				recData = input.readLine();

				keyType = getType(recData.toLowerCase()); // get the type of the key via the helper method

				Record rec = new Record(new Key(keyLine, keyType), recData); // create a new record with all the given
																				// information

				dictionary.put(rec); // insert the aforementioned record

			}
		} catch (Exception e) { // catch any exceptions and print the reasons they were thrown
			e.printStackTrace();
		}

		return dictionary; // return the now-populated dictionary

	}

	private static int getType(String s) { // get the type of the string passed in based on the first character or last
											// few characters of the string

		if (s.startsWith("/")) {
			return 2;
		} else if (s.startsWith("-")) {// sound
			return 3;
		} else if (s.startsWith("+")) {// music
			return 4;
		} else if (s.startsWith("*")) {// voice
			return 5;
		} else if (s.endsWith(".gif")) {// gif
			return 7;
		} else if (s.endsWith(".jpg")) {// jpeg
			return 6;
		} else if (s.endsWith(".html")) {// html
			return 8;
		} else { // default case
			return 1;
		}

	}

}
