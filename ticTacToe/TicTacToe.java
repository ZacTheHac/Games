package ticTacToe;

import java.util.Scanner;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//* Class name      : TicTacToe			                        *
//*                                                                     *
//* Written by      : Zachary Muerle (C) 2014, All rights reserved      *
//*                                                                     *
//* Purpose         : plays TicTacToe via number input			*
//*                   							*
//*                                                                     *
//* Inputs          : a number of where the player would like to play	*
//*                   							*
//*                                                                     *
//* Outputs          : a TicTacToe grid with the plays & a winner	*
//* 								        *
//*                                                                     *
//* Methods         : main(String[] args)				*
//*                                                                     *
//*---------------------------------------------------------------------*
//* Change Log:                                                         *
//*                         Revision                                    *
//*       Date    Changed  Rel Ver Mod Purpose                          *
//* 10/02/14      ZMuerle  000.000.000 Initial release of program       *
//* 10/22/14      ZMuerle  000.001.000 made the computer play and fixed *
//*						some major bugs		*
//* 5/2/17			ZMuerle 001.000.000 refactored code to use the class I made (finally) and broke a lot*
//* 									*
//*                                    					*
//*                                                                     *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
public class TicTacToe { // ch9 GZ2 A and B
	static boolean randomAI = true; 
	// use the random AI, or one that slightly thinks
	static boolean debug = true;
	//print out debug statements or not.

	public static void main(String[] args) {
		int gridWidth = 3; // how big the board is. default is 3. note that this
							// must be odd and greater than 1, and you probably
							// don't want more than 5
		TTTboard board = new TTTboard(gridWidth);
		String turn = "X"; // holds whos turn it is: x or o, starts w/ x
		Scanner input = new Scanner(System.in);
		String plyInput = ""; // stores the player's input
		while (true) { // game loop
			if (turn.equals("O")) { // if it's the computer's turn
				int comPlay = compPlay(board);
				if(!board.tryPlay(comPlay, turn))
					continue;//if it isn't a valid play, then just run it again
				
			} else {
				board.dispBoard();
				board.WinPossible(debug);
				System.out.println("It is " + turn + "'s turn. enter where you want to play.");
				plyInput = input.nextLine();
				if (board.tryPlay(plyInput, turn) == false) { // they tried to
																// play in an
																// already
																// played space
					System.out.println("Sorry, that spot has been played, or never existed");
					continue; // if not, jump back up to the top
				}
			}
			String winner = board.getWinner();
			if (winner != null) {
				board.dispBoard();
				System.out.println(winner + " is the winner!");
				break; // if we have a winner, break out of the game loop
			}
			if(!board.WinPossible()){
				board.dispBoard();
				System.out.println("Cat's game! Nobody wins!");
				break;
			}
			if (turn.equals("X")) // swap the turns
				turn = "O";
			else
				turn = "X";
		} // end of game loop
		input.close();

	}

	public static int compPlay(TTTboard board) {
		if (!randomAI) {
			return compSmartPlay(board);
		} else {
			return compRandPlay(board);
		}
	}

	//this is woefully broken right now, and I honestly can't be bothered to figure out how I broke it.
	//it also crashes the game a lot, so I'm disabling it by default. it didn't play well anyways.
	public static int compSmartPlay(TTTboard inpboard) {
		String[][] board = inpboard.getBoard();
		// shamelessly stealing most of this from the checkwinner
		String checkFor = "O"; // just hardcode this, as O is the computer.
		int possiblePlay = -1; // this will hold values that may be a valid play

		int boardLength = board.length;
		for (int i = 0; i < boardLength; ++i) {
			// check board[0][i]
			if (inpboard.isPlayable(0, i)) { // the spot has been played (if it
										// hasn't, it would be an int)
				if (i == 0 && board[0][i + 1].equals(checkFor)) { // it can only
																	// be a row
																	// going
																	// right if
																	// I start
																	// at the
																	// far left.
																	// otherwise,
																	// there's
																	// no reason
																	// to check
					// check going right
					int counter = 0;
					for (int t = 0; t < boardLength; ++t) { // go through the
															// entire row
						if (board[0][t].equals(checkFor)) {
							counter++; // count how many Os are in the row
						} else {
							if (inpboard.isPlayable(0, t)) {
								possiblePlay = t; // if it's an open space, it
													// may be a place we want to
													// play
							} else {
								break; // if it's not us, and not an open space,
										// then we can't win here
							}
						}
					}
					if (counter == boardLength - 1 && inpboard.isPlayable(0, possiblePlay)) { // if
																							// all
																							// but
																							// 1
																							// space
																							// is
																							// filled
																							// with
																							// O,
																							// and
																							// that
																							// space
																							// is
																							// open,
																							// take
																							// it
						return Integer.parseInt(board[0][possiblePlay]);
					}

				}

				if (i == 0 && board[1][i + 1].equals(checkFor)) { // check down
																	// and to
																	// the right
					int counter = 0;
					for (int t = 0; t < boardLength; ++t) {
						if (board[t][t].equals(checkFor)) {
							counter++;
						} else {
							if (inpboard.isPlayable(t,t)) {
								possiblePlay = t; // if it's an open space, it
													// may be a place we want to
													// play
							} else {
								break; // if it's not us, and not an open space,
										// then we can't win here
							}
						}
					}
					if (counter == boardLength - 1 && inpboard.isPlayable(possiblePlay, possiblePlay)) { // if
																									// all
																									// but
																									// 1
																									// space
																									// is
																									// filled
																									// with
																									// O,
																									// and
																									// that
																									// space
																									// is
																									// open,
																									// take
																									// it
						return Integer.parseInt(board[possiblePlay][possiblePlay]);
					}
				}
				if (i == (boardLength - 1) && board[1][i - 1].equals(checkFor)) {// check
																					// down
																					// and
																					// to
																					// the
																					// left
					int counter = 0;
					for (int t = 0; t < boardLength; ++t) {
						if (board[boardLength - 1 - t][t].equals(checkFor)) {
							counter++;
						} else {
							if (inpboard.isPlayable(t,t)) {
								possiblePlay = t; // if it's an open space, it
													// may be a place we want to
													// play
							} else {
								break; // if it's not us, and not an open space,
										// then we can't win here
							}
						}
					}
					if (counter == boardLength - 1 && inpboard.isPlayable(boardLength - 1 - possiblePlay, possiblePlay)) {
						//if all but 1 space is filled with O, and that space is open, take it
						return Integer.parseInt(board[boardLength - 1 - possiblePlay][possiblePlay]);
					}

				}
				if (board[1][i].equals(checkFor)) { // check down, as this just
													// checks the top row
					int counter = 0;
					for (int t = 0; t < boardLength; ++t) {
						if (board[t][i].equals(checkFor)) {
							counter++;
						} else {
							if (inpboard.isPlayable(t,i)) {
								possiblePlay = t; // if it's an open space, it
													// may be a place we want to
													// play
							} else {
								break; // if it's not us, and not an open space,
										// then we can't win here
							}
						}
					}
					if (counter == boardLength - 1 && inpboard.isPlayable(possiblePlay, i)) { // if
																							// all
																							// but
																							// 1
																							// space
																							// is
																							// filled
																							// with
																							// O,
																							// and
																							// that
																							// space
																							// is
																							// open,
																							// take
																							// it
						return Integer.parseInt(board[possiblePlay][i]);
					}

				}

			}
		}
		// now we can check board[i][0] - all we have to do here is going right,
		// because all other possibilities are checked already
		for (int i = 1; i < boardLength; ++i) {
			if (!inpboard.isPlayable(i,0)) { // the spot has been played (if it
										// hasn't, it would be an int)
				if (board[i][1].equals(checkFor)) {
					int counter = 0;
					for (int t = 0; t < boardLength; ++t) {
						if (board[i][t].equals(checkFor)) {
							counter++;
						} else {
							if (inpboard.isPlayable(i,t)) {
								possiblePlay = t; // if it's an open space, it
													// may be a place we want to
													// play
							} else {
								break; // if it's not us, and not an open space,
										// then we can't win here
							}
						}

					}
					if (counter == boardLength - 1 && inpboard.isPlayable(i, possiblePlay)) { // if
																							// all
																							// but
																							// 1
																							// space
																							// is
																							// filled
																							// with
																							// O,
																							// and
																							// that
																							// space
																							// is
																							// open,
																							// take
																							// it
						return Integer.parseInt(board[i][possiblePlay]);
					}
				}
			}
		}
		return compRandPlay(inpboard); // if it got here without returning
										// anything, then there's no 2 in a row
										// yet, so just play a random spot.

	}

	public static int compRandPlay(TTTboard board) {
		int boardSize = board.getGridSize();
		int[] availPlays = new int[(int) Math.pow(boardSize, 2)];// will hold
																	// all the
																	// places
																	// the
																	// computer
																	// can play.
																	// made
																	// large
																	// enough to
																	// hold the
																	// entire
																	// board, if
																	// needed
		int index = 0;
		for (int row = 0; row < boardSize; row++) {
			for (int coll = 0; coll < boardSize; coll++) {
				if (board.isPlayable(row, coll)) {
					availPlays[index] = board.getSpotNumber(row, coll);
					index++;
				}
			}
		}
		/*
		 * for(String[] line: board){ for(String place:line){ if(isInt(place)){
		 * availPlays[index]=Integer.parseInt(place); index++; } } }
		 */
		int play = availPlays[(int) (Math.random() * (index))];
		return play;
	}
}
