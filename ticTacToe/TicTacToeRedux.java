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
public class TicTacToeRedux {									 //ch9 GZ2 A and B
	
	public static void main(String[] args) {
		TTTboard board = new TTTboard();
		String turn = "X";									//holds whos turn it is: x or o, starts w/ x
		Scanner input = new Scanner(System.in);
		String plyInput = "";								//stores the player's input
		while(true){										//game loop
				board.dispBoard();									//display the board
				System.out.println("It is "+turn+"'s turn. enter where you want to play.");
				plyInput = input.nextLine();
				if(!board.tryPlay(plyInput, turn)){//trys to play that spot
					System.out.println("Ha ha, very funny. now play correctly.");
					continue;									//go back to the top of the loop
				}

				String winner = board.getWinner();
				if(winner != null){
					board.dispBoard();
					System.out.println(winner + " is the winner!");
					break;									//if we have a winner, break out of the game loop
				}
				if(turn.equals("X"))								//swap the turns
					turn = "O";
				else
					turn = "X";
		}//end of game loop
		input.close();
	}

}
