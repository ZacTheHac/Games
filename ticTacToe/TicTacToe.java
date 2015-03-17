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
//* 									*
//*                                    					*
//*                                                                     *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
public class TicTacToe {									 //ch9 GZ2 A and B
	static boolean randomAI = false;								//use the random AI, or one that slightly thinks
	
	public static void main(String[] args) {
		int gridWidth = 3;									//how big the board is. default is 3. note that this must be odd and greater than 1, and you probably don't want more than 5
		String[][] board = new String[gridWidth][gridWidth];//2d array to hold the board
		String turn = "X";									//holds whos turn it is: x or o, starts w/ x
		int indexLength = Integer.toString((int) Math.pow(gridWidth, 2)).length();//holds how long the numbers can get (useful for formatting 5x5 for instance)
		int index = 1;										//holds a value to fill in the grid
		for(int r = 0; r<gridWidth;++r){
			for(int c = 0; c<gridWidth; ++c){
				board[r][c] = String.format("%0"+indexLength+"d", index);		//fill the grid with the current "index" value, padded with 0s if needed
				index++;
			}
		}
		Scanner input = new Scanner(System.in);
		String plyInput = "";								//stores the player's input
		int[] Coords = new int[2];							//will hold the Coords the player wishes to play at
		while(true){										//game loop
			Coords = null;									//wipe the Coords, just in case;
			
			if(turn.equals("O")){							// if it's the computer's turn
				Coords = getCoords(compPlay(board),board);
			}
			else{
				dispBoard(board);									//display the board
				System.out.println("It is "+turn+"'s turn. enter where you want to play.");
				plyInput = input.nextLine();
				if(isInt(plyInput))//check that the player isn't playing any funny games
					Coords = getCoords(Integer.parseInt(plyInput), board);
				else{											//they tried to play in an already played space
					System.out.println("Ha ha, very funny. now play correctly.");
					continue;									//go back to the top of the loop
				}
			}
			
			if(Coords == null){								//check we got a valid coord
				System.out.println("Sorry, that spot has been played, or never existed");
				continue;									//if not, jump back up to the top
			}
			else{
				board[Coords[0]][Coords[1]]=turn;
				String winner = checkWinner(board);
				if(winner != null){
					dispBoard(board);
					System.out.println(winner + " is the winner!");
					break;									//if we have a winner, break out of the game loop
				}
				if(turn.equals("X"))								//swap the turns
					turn = "O";
				else
					turn = "X";
			}
		}//end of game loop
		input.close();
		

	}
	public static void dispBoard(String[][] board){			//prints the board to the screen
		String line = "";
		int boardSize = board.length;				//the board should always be a square, so any single line will be the same length
		for(int r = 0;r<boardSize;++r){
			for(int c = 0;c<boardSize;++c){
				line+= board[r][c];
				if(c-1<boardSize)
					line += "|";
			}
			System.out.println(line);
			line = "";
			for(int i = 1; i<=boardSize ; ++i){
				line+="- ";
			}
			System.out.println(line);
			line="";
		}
		
	}
	
	public static int[] getCoords(int value, String[][] board){			//returns the coords of a string in the array (null if it doesn't exist)
		int boardSize = board.length;					//gets the length of the array
		for(int x = 0; x<boardSize; ++x){
			for(int y = 0; y<boardSize; ++y){
				if(isInt(board[x][y]) && Integer.parseInt(board[x][y]) == value){				//go through every value in the array, searching for the string
					int[] Coords = {x,y};					//if it's found: create an int[] that has that string's coords
					return Coords;							//and send it back
				}
			}
		}
		
		return null;										//if it gets here, the string doesn't exist in the array
	}

	public static boolean isInt(String input){				//returns true if a string is an int, false otherwise
		try{
			Integer.parseInt(input);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	public static String checkWinner(String[][] board){		//returns a winner, if there is one. null otherwise.
									//go through the first row & colum, if any of them is an X or O, search for neighbors of the same
									//if there is one, follow that line until we find *boardlength* in a row, or it is no longer the X or O
									//make sure to check there are still valid places to play at the end. if there aren't, then it's a tie and everyone loses
		int boardLength = board.length;
		for(int i = 0; i<boardLength; ++i){
			//check board[0][i]
			if(!isInt(board[0][i])){			//the spot has been played (if it hasn't, it would be an int)
				String checkFor = board[0][i];		//who we're checking for
				if(i==0 && board[0][i+1].equals(checkFor)){//it can only be a row going right if I start at the far left. otherwise, there's no reason to check
					//check going right
					int counter = 0;
					for(int t = 0;t<boardLength;++t){//go through the entire row
						if(!board[0][t].equals(checkFor)){
							break;																	//if any of them isn't the person we're checking for, then this fails
						}
						else{
							counter++;							
						}
					}
					if(counter == boardLength){	//if all of them are, then we have a winner
						return checkFor;
					}
					
				}
				
				if(i==0 && board[1][i+1].equals(checkFor)){		//check down and to the right
					int counter = 0;
					for(int t = 0; t<boardLength; ++t){
						if(board[t][t].equals(checkFor)){
							counter++;
						}
						else{
							break;
						}
					}
					if(counter == boardLength){
						return checkFor;
					}
				}
				if(i==(boardLength-1)  && board[1][i-1].equals(checkFor)){//check down and to the left
					int counter = 0;
					for(int t = 0; t<boardLength; ++t){
						if(board[boardLength-1-t][t].equals(checkFor)){
							counter++;
						}
						else{
							break;
						}
					}
					if(counter==boardLength){
						return checkFor;
					}
					
				}
				if(board[1][i].equals(checkFor)){			//check down, as this just checks the top row
					int counter = 0;
					for(int t = 0; t<boardLength; ++t){
						if(board[t][i].equals(checkFor)){
							counter++;
						}
						else{
							break;
						}
					}
					if(counter == boardLength){
						return checkFor;
					}
					
				}
				
				
			}
		}
		//now we can check board[i][0] - all we have to do here is going right, because all other possibilities are checked already
		for(int i = 1;i<boardLength;++i){
			if(!isInt(board[i][0])){							//the spot has been played (if it hasn't, it would be an int)
				String checkFor = board[i][0];					//who we're checking for
				if(board[i][1].equals(checkFor)){
					int counter = 0;
					for(int t = 0; t<boardLength; ++t){
						if(board[i][t].equals(checkFor)){
							counter++;
						}
						else{
							break;
						}
						
					}
					if(counter == boardLength){
						return checkFor;
					}
				}
			}
		}
		
		//now check that there is still a valid place to play
		for(String[] row : board){
			for(String point : row){
				if(isInt(point)){
					return null;							//if there is an integer in any point, it's a valid place to play
				}
			}
		}
		return "Nobody";									//if it got here, nobody won, but there are no more valid places to play. nobody wins.
	}

	public static int compPlay(String[][] board){
		if(!randomAI){
			return compSmartPlay(board);
		}
		else{
			return compRandPlay(board);
		}
	}
	public static int compSmartPlay(String[][] board){
															//shamelessly stealing most of this from the checkwinner
		String checkFor = "O";								//just hardcode this, as O is the computer.
		int possiblePlay = -1;								//this will hold values that may be a valid play
		
		int boardLength = board.length;
		for(int i = 0; i<boardLength; ++i){
			//check board[0][i]
			if(!isInt(board[0][i])){							//the spot has been played (if it hasn't, it would be an int)
				if(i==0 && board[0][i+1].equals(checkFor)){		//it can only be a row going right if I start at the far left. otherwise, there's no reason to check
					//check going right
					int counter = 0;
					for(int t = 0;t<boardLength;++t){			//go through the entire row
						if(board[0][t].equals(checkFor)){
							counter++;							//count how many Os are in the row
						}
						else{
							if(isInt(board[0][t])){
								possiblePlay = t;				//if it's an open space, it may be a place we want to play
							}
							else{
								break;							//if it's not us, and not an open space, then we can't win here
							}
						}
					}
					if(counter == boardLength-1 && isValid(board,0,possiblePlay)){					//if all but 1 space is filled with O, and that space is open, take it
						return Integer.parseInt(board[0][possiblePlay]);
					}
					
				}
				
				if(i==0 && board[1][i+1].equals(checkFor)){		//check down and to the right
					int counter = 0;
					for(int t = 0; t<boardLength; ++t){
						if(board[t][t].equals(checkFor)){
							counter++;
						}
						else{
							if(isInt(board[t][t])){
								possiblePlay = t;				//if it's an open space, it may be a place we want to play
							}
							else{
								break;							//if it's not us, and not an open space, then we can't win here
							}
						}
					}
					if(counter == boardLength-1 && isValid(board,possiblePlay,possiblePlay)){					//if all but 1 space is filled with O, and that space is open, take it
						return Integer.parseInt(board[possiblePlay][possiblePlay]);
					}
				}
				if(i==(boardLength-1)  && board[1][i-1].equals(checkFor)){//check down and to the left
					int counter = 0;
					for(int t = 0; t<boardLength; ++t){
						if(board[boardLength-1-t][t].equals(checkFor)){
							counter++;
						}
						else{
							if(isInt(board[t][t])){
								possiblePlay = t;				//if it's an open space, it may be a place we want to play
							}
							else{
								break;							//if it's not us, and not an open space, then we can't win here
							}
						}
					}
					if(counter == boardLength-1 && isValid(board,boardLength-1-possiblePlay,possiblePlay)){					//if all but 1 space is filled with O, and that space is open, take it
						return Integer.parseInt(board[boardLength-1-possiblePlay][possiblePlay]);
					}
					
				}
				if(board[1][i].equals(checkFor)){			//check down, as this just checks the top row
					int counter = 0;
					for(int t = 0; t<boardLength; ++t){
						if(board[t][i].equals(checkFor)){
							counter++;
						}
						else{
							if(isInt(board[t][i])){
								possiblePlay = t;				//if it's an open space, it may be a place we want to play
							}
							else{
								break;							//if it's not us, and not an open space, then we can't win here
							}
						}
					}
					if(counter == boardLength-1 && isValid(board,possiblePlay,i)){					//if all but 1 space is filled with O, and that space is open, take it
						return Integer.parseInt(board[possiblePlay][i]);
					}
					
				}
				
				
			}
		}
		//now we can check board[i][0] - all we have to do here is going right, because all other possibilities are checked already
		for(int i = 1;i<boardLength;++i){
			if(!isInt(board[i][0])){							//the spot has been played (if it hasn't, it would be an int)
				if(board[i][1].equals(checkFor)){
					int counter = 0;
					for(int t = 0; t<boardLength; ++t){
						if(board[i][t].equals(checkFor)){
							counter++;
						}
						else{
							if(isInt(board[i][t])){
								possiblePlay = t;				//if it's an open space, it may be a place we want to play
							}
							else{
								break;							//if it's not us, and not an open space, then we can't win here
							}
						}
						
					}
					if(counter == boardLength-1 && isValid(board,i,possiblePlay)){					//if all but 1 space is filled with O, and that space is open, take it
						return Integer.parseInt(board[i][possiblePlay]);
					}
				}
			}
		}
		return compRandPlay(board);								//if it got here without returning anything, then there's no 2 in a row yet, so just play a random spot.
		
	}
	public static int compRandPlay(String[][] board){
		int[] availPlays = new int[(int) Math.pow(board.length, 2)];//will hold all the places the computer can play. made large enough to hold the entire board, if needed
		int index = 0;
		for(String[] line: board){
			for(String place:line){
				if(isInt(place)){
					availPlays[index]=Integer.parseInt(place);
					index++;
				}
			}
		}
		int play = availPlays[(int) (Math.random()*(index))];
		return play;
	}
	
	public static boolean isValid(String[][] board, int y, int x){
		try{
			@SuppressWarnings("unused")					//I know it's unused, it'll just be dumped once we leave anyways.
			String holder = board[y][x];
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}
}
