package cardGames;
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//* Class name      : War	                                            *
//*                                                                     *
//* Written by      : Zachary Muerle (C) 2014, All rights reserved      *
//*                                                                     *
//* Purpose         : plays a very basic form of the card game War		*
//*                   													*
//*                                                                     *
//* Inputs          : *none*										    *
//*                   						                            *
//*                                                                     *
//* Outputs          : a messagebox telling the user who won and why    *
//* 								                                    *
//*                                                                     *
//* Methods         : main(String[] args)							    *
//*                                                                     *
//*---------------------------------------------------------------------*
//* Change Log:                                                         *
//*                         Revision                                    *
//*       Date    Changed  Rel Ver Mod Purpose                          *
//* 09/09/14      ZMuerle  000.000.000 Initial release of program       *
//* 09/11/14      ZMuerle  000.000.001 added some notes					*
//* 09/11/14      ZMuerle  000.001.000 random cards now use Card()      *
//*                                                                     *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

import javax.swing.JOptionPane;

public class War {

	public static void main(String[] args) {
		//card picking shamelessly stolen from PickTwoCards:
		char[] suitList = new char[4];//create a list that can hold 4 chars
		suitList[0] = 's';
		suitList[1]	= 'd';
		suitList[2]	= 'h';
		suitList[3]	= 'c';//populate the list with the 4 chars that are applicable to the card class' suits
		Card cardComp = null;//the compiler won't let me continue if it's not definitely initialized, and this is what eclipse did to fix it.
		//Card cardOne = new Card();
		Card cardPly = null;
		
		for(int i = 1; i <= 2; i++){//loops twice to pick 2 cards
			if(i == 1){//first loop, so make the first card
				cardComp = new Card();//the default constructor spits out a random card
				continue; //just jump back instead of having to think about the rest.
			}
			else if(i == 2){//2nd loop, make the 2nd card
				cardPly = new Card();
				while(cardComp == cardPly){//if the cards are equal
					System.out.println("The computer's and player's cards were both "+ cardComp.toString());
					cardPly = new Card();
					//keep making a random one until they're not equal
				}
				break;//just picked the 2nd card, no reason to loop again
			}
			else{
				break;//somehow i isn't 1 or 2, so just break out
			}
		}
		//now that we have 2 cards: one for the computer, and the other for the player:
		//we compare the values to see who's is higher
		if(cardComp.getValue() > cardPly.getValue()){//the book said to just assume ace is the lowest
			//if the computer wins
			JOptionPane.showMessageDialog(null, "The computer's card: "+cardComp.toString()+"\nbeat the player's card: "+cardPly.toString());
		}
		else if(cardComp.getValue() < cardPly.getValue()){
			//if the player wins
			JOptionPane.showMessageDialog(null, "The player's card: "+cardPly.toString()+"\nbeat the computer's card: "+cardComp.toString());
		}
		else if(cardComp.getValue() == cardPly.getValue()){
			//if they have the same value of card (but not suit, b/c that means someone is cheating)
			JOptionPane.showMessageDialog(null, "Both the player and computer lose.\nThe player had "+cardPly.toString()+"\nand the computer had "+cardComp.toString());
		}

	}

}
