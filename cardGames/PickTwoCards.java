package cardGames;
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//* Class name      : PickTwoCards	                                    *
//*                                                                     *
//* Written by      : Zachary Muerle (C) 2014, All rights reserved      *
//*                                                                     *
//* Purpose         : randomly generates 2 playing cards and displays   *
//*                   their values as a string							*
//*                                                                     *
//* Inputs          : *none*										    *
//*                   						                            *
//*                                                                     *
//* Outputs          : a string inside a dialog box					    *
//* 								                                    *
//*                                                                     *
//* Methods         : main(String[] args)							    *
//*                                                                     *
//*---------------------------------------------------------------------*
//* Change Log:                                                         *
//*                         Revision                                    *
//*       Date    Changed  Rel Ver Mod Purpose                          *
//* 09/04/14      ZMuerle  000.000.000 Initial release of program       *
//* 09/09/14      ZMuerle  000.000.001 added flowerbox and a comment    *
//* 																	*
//*                                    					                *
//*                                                                     *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

import javax.swing.JOptionPane;


public class PickTwoCards {//main class for Ch3 GZ1. and CH5 - GZ3 - with no changes

	public static void main(String[] args) {
		char[] suitList = new char[4];//create a list that can hold 4 chars
		suitList[0] = 's';
		suitList[1]	= 'd';
		suitList[2]	= 'h';
		suitList[3]	= 'c';//populate the list with the 4 chars that are applicable to the card class' suits
		Card cardOne = null;//the compiler won't let me continue if it's not definitely initialized, and this is what eclipse did to fix it.
		//Card cardOne = new Card();
		Card cardTwo = null;
		
		for(int i = 1; i < 3; i++){//loops twice to pick 2 cards
			short randomSuit = (short) ((Math.random() * 4)); //random number 0-4, for the suit
			short randomValue = (short) (1+(Math.random() * 13));//random number 1-13, for the value. it gave me an equation, but I like mine more.
			if(i == 1){//first loop, so make the first card
				cardOne = new Card(suitList[randomSuit], randomValue);
				continue; //just jump back instead of having to think about the rest.
			}
			else if(i == 2){//2nd loop, make the 2nd card
				cardTwo = new Card(suitList[randomSuit], randomValue);
				continue;
			}
			else{
				break;//somehow i isn't 1 or 2, so just break out
			}
		}
		JOptionPane.showMessageDialog(null, "I picked 2 cards: \n" + cardOne.toString() + "\nand \n" + cardTwo.toString());//heh, nand. 
		//displays the 2 cards, using Card's toString method
		
		

	}

}
