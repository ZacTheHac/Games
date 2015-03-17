package stringManipulation;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//* Class name      : RandomGuess	                                    *
//*                                                                     *
//* Written by      : Zachary Muerle (C) 2014, All rights reserved      *
//*                                                                     *
//* Purpose         : compare a player's input to a random value, with	*
//*                      error checking                                 *
//*                                                                     *
//* Inputs          : a short for the user's input					    *
//*                   						                            *
//*                                                                     *
//* Outputs          : what to input and who wins or loses              *
//*                                                                     *
//* Methods         : Main()			                                *
//*                                                                     *
//*---------------------------------------------------------------------*
//* Change Log:                                                         *
//*                         Revision                                    *
//*       Date    Changed  Rel Ver Mod Purpose                          *
//* 11/06/14      ZMuerle  000.000.000 Initial release with flowerbox   *
//*                                                                     *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

import java.util.Scanner;

public class RandomGuess {// for ch1 - GZ 1 and GH11 GZ1 - just did some
							// modifications - removed swing, and added the
							// specific exception
	static short chosenNumber = 0; // no reason to use full ints - holds the
									// user's choice
	static short randNumber = 0;// will hold a random value the computer makes
								// up

	public static void main(String[] args) {
		Scanner inputDev = new Scanner(System.in);
		boolean Correct = false;// if their input was valid

		while (!Correct) {// it'll keep asking until it works
			System.out.println("Think of a number between 1 and 10");
			String input = inputDev.nextLine();// get their input
			try {
				chosenNumber = Short.parseShort(input); // try to put the string
														// into int, goto catch
														// if it can't
				if (chosenNumber < 1 || chosenNumber > 10) { // if the value is
																// less than 1
																// or more than
																// 10
					System.out.println("I said a number between 1 and 10.");
				} else {
					Correct = true; // they got an input that works, so we can
									// drop out of the loop
				}
			} catch (NumberFormatException e) { // if the string can't be cast
												// to a short
				System.out.println(e.getMessage()
						+ " isn't convertable to a number between 1 and 10");
			}
		}
		inputDev.close();
		randNumber = (short) (1 + (Math.random() * 10)); // generate a random
															// number between 1
															// and 10, and store
															// it
		// I'm really tempted to just check if this is equal to their guess, and
		// chose another one if it is.
		String Message = "The number I chose is " + randNumber;
		if (chosenNumber == randNumber) { // this is for Game Zone #1, but I
											// added the checking, simply
											// because it was easy enough to do,
											// and seemed like it needed it.
			Message += ", which is what you chose!";
		} else {// otherwise, they're wrong
			Message += ", which is different from the " + chosenNumber
					+ " you chose. You lose"; // I'm nice
		}
		System.out.println(Message);// display the message we
									// made

	}

}
