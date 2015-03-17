package dieGames;

import javax.swing.JOptionPane;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//* Class name      : PigDiceGame                                       *
//*                                                                     *
//* Written by      : Zachary Muerle (C) 2014, All rights reserved      *
//*                                                                     *
//* Purpose         : a version of the dice game Pig					*
//*                   The object of the game is to be the first			*
//*						to score 100 points.						    *
//*                                                                     *
//* Inputs          : yes or no, if they want to continue rolling		*
//*                   						                            *
//*                                                                     *
//* Outputs         : strings detailing the events of the game			*
//* 								                                    *
//*                                                                     *
//* Methods         : main(String[] args)							    *
//*                                                                     *
//*---------------------------------------------------------------------*
//* Change Log:                                                         *
//*                         Revision                                    *
//*       Date    Changed  Rel Ver Mod Purpose                          *
//* 09/11/14      ZMuerle  000.000.000 Initial release of program       *
//* 																	*
//* 																    *
//*                                                                     *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *


public class PigDiceGame {//ch6 GZ4

	public static void main(String[] args) {
		short playerScore = 0;//hold the player's score
		short compScore = 0;//the computer's score
		Die dieOne = new Die();
		Die dieTwo = new Die();//2 dice to play with
		boolean finished = false;//is the game finished?
		boolean plyTurn = true;//who's turn is it? true = ply, false = comp
		
		while(!finished){
			dieOne.reRoll();
			dieTwo.reRoll();//randomize the 2 die
			if(dieOne.getValue() != 1 && dieTwo.getValue() != 1){//if neither of them are 1
				if(plyTurn){
					playerScore += dieOne.getValue() + dieTwo.getValue();//adds the 2 die to the player's score
					System.out.println("Player earns " + (dieOne.getValue() + dieTwo.getValue()) + " points!");
					System.out.println("Player's new score: "+playerScore);
					//check if their score can end the game before asking to roll again:
					if(playerScore >= 100 || compScore >= 100){
						//someone has a score of 100+, end the game
						finished = true;
						break;//immediately jump out, so it skips the rest
					}
					int choice = JOptionPane.showConfirmDialog(null, "Would you like to keep rolling?", "Keep rolling?", JOptionPane.YES_NO_OPTION);
					//create an option pane w/ yes and no, store the result as "choice"
					if(choice == JOptionPane.YES_OPTION){//if they chose to keep rolling
						System.out.println("Player chose to roll again.");
					}
					else if(choice == JOptionPane.NO_OPTION){//if they chose to pass
						System.out.println("Player chose to pass their turn.");
						plyTurn = false;
					}
					else{//result wasn't yes or no, so I'll take that as a no. - they prob. just closed the message
						System.out.println("You closed the option box. Taking that as a \"no\".");
						plyTurn = false;
					}
				}
				else{
					compScore += dieOne.getValue() + dieTwo.getValue();
					System.out.println("Computer earns " + (dieOne.getValue() + dieTwo.getValue()) + " points!");
					System.out.println("Computer's new score: "+compScore);
					//check if either won before asking to re-roll
					if(playerScore >= 100 || compScore >= 100){
						//someone has a score of 100+, end the game
						finished = true;
						break;//immediately jump out, so it skips the rest
					}
					double rand = Math.random();
					if(rand >= 0.5){
						//keep rolling
						System.out.println("Computer chose to keep rolling.");
					}
					else{
						//pass turn
						System.out.println("Computer chose to pass their turn.");
						plyTurn = true;
					}
				}
			}
			else if(dieOne.getValue() == 1 && dieTwo.getValue() == 1){//both die are 1
				//reset that player's score to 0 & pass their turn
				if(plyTurn){
					playerScore = 0;
					plyTurn = false;
					System.out.println("Turn passed to the Computer, as the player rolled both 1s, resetting their score");
				}
				else{
					compScore = 0;
					plyTurn = true;
					System.out.println("Turn passed to the Player, as the computer rolled both 1s, resetting their score");
				}
				
			}
			else{//one of the die showed a 1, pass the turn to the other player
				if(plyTurn){//if it is the player's turn
					plyTurn = false;//make it not
					System.out.println("Turn passed to the Computer, as the player rolled a 1");
				}
				else{//if it's the computer's turn
					plyTurn = true;//pass it to the player
					System.out.println("Turn passed to the Player, as the computer rolled a 1");
				}
				
			}
			if(playerScore >= 100 || compScore >= 100){
				//someone has a score of 100+, end the game
				finished = true;
			}
			
		}//end of while loop
		if(playerScore >= 100){
			System.out.println("The player wins with " + playerScore + " points! " + (playerScore-compScore) + " points more than the computer!");
		}
		else if(compScore >= 100){
			System.out.println("The computer wins with " + compScore + " points! " + (compScore-playerScore) + " points more than the player!");
		}
		else{
			System.out.println("Please restart the program. something broke.");
		}

	}

}
