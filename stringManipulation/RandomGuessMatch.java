package stringManipulation;
import javax.swing.JOptionPane;

public class RandomGuessMatch {//ch2 - GZ2

	public static void main(String[] args) {
		// mostly just copied from GZ#1
		short chosenNumber = 0; // no reason to use full ints
		short randNumber = 0;
		boolean Correct = false;

		while (!Correct) {// it'll keep asking until it works
			String input = JOptionPane
					.showInputDialog("Think of a number between 1 and 5"); // get
																			// string
																			// input
			try {
				chosenNumber = Short.parseShort(input); // try to put the string
														// into int, goto catch
														// if it can't
				if (chosenNumber < 1 || chosenNumber > 5) { // if the value is
															// less than 1
															// or more than
															// 10
					JOptionPane.showMessageDialog(null,
							"I said a number between 1 and 5.");
				} else {
					Correct = true; // they got an input that works, so we can
									// drop out of the loop
				}
			} catch (Exception e) { // if the string can't be cast to a short
				JOptionPane.showMessageDialog(null,
						"That didn't seem to be a number between 1 and 5");
			}
		}
		randNumber = (short) (1 + (Math.random() * 5)); // generate a random
														// number between 1
														// and 10, and store
														// it
		// I'm really tempted to just check if this is equal to their guess, and
		// chose another one if it is.
		String Message = "The number I chose is " + randNumber;
		if (chosenNumber == randNumber) {// compare the 2 numbers to be equal
			Message = Message + ", which is what you chose!";
		} else {// otherwise, they're wrong
			short difference = (short) Math.abs(chosenNumber - randNumber);
			// take the absolute value of the difference between the values,
			// which will never exceed 5, so use a short.
			Message = Message + ", which is different from the " + chosenNumber
					+ " you chose" + " by " + difference + ". You lose"; // I'm
																			// nice
		}
		JOptionPane.showMessageDialog(null, Message);// display the message we
														// made
		JOptionPane.showMessageDialog(null, "Random number: "+randNumber+", equal = "+(randNumber==chosenNumber));
		//I only add this because the book told me to. it seems pointless to me.

	}

}
