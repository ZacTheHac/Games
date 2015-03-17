package cardGames;
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//* Class name      : Card			                                    *
//*                                                                     *
//* Written by      : Zachary Muerle (C) 2014, All rights reserved      *
//*                                                                     *
//* Purpose         : Holds values used to create a playing card        *
//*                   and send back corresponding values when requested *
//*                                                                     *
//* Inputs          : a char for the suit, and an int for the value     *
//*                   						                            *
//*                                                                     *
//* Outputs          : Strings, ints, or chars, depending on what's     *
//* 						requested                                   *
//*                                                                     *
//* Methods         : SetSuit(char suit), getSuit(), getSuitName(),     *
//*                   getValue(), getValueName(), setValue(int value),  *
//*						and toString()                                  *
//*                                                                     *
//*---------------------------------------------------------------------*
//* Change Log:                                                         *
//*                         Revision                                    *
//*       Date    Changed  Rel Ver Mod Purpose                          *
//* 09/04/14      ZMuerle  000.000.000 Initial release of program       *
//* 09/11/14      ZMuerle  000.000.001 edited the first note			*
//* 09/11/14      ZMuerle  000.001.000 modified the default constructor *
//*                                                                     *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *


public class Card {//for ch3 GZ1 and ch5 GZ3
	
	private char suit;
	private int value; //why use an int if it won't go beyond [1,13]? dunno, the book told me to. I would use a short.
	
	Card(char suit, int value){//makes a card with the stated values
		this.setSuit(suit);
		this.setValue(value);
	}
	
	Card(){//makes a random card
		char[] suitList = new char[4];//create a list that can hold 4 chars
		suitList[0] = 's';
		suitList[1]	= 'd';
		suitList[2]	= 'h';
		suitList[3]	= 'c';//populate the list with the 4 chars that are applicable to the card class' suits
		short randomSuit = (short) ((Math.random() * 4)); //random number 0-4, for the suit
		short randomValue = (short) (1+(Math.random() * 13));//random number 1-13, for the value.
		
		//these last 2 lines are similar to the original constructor, but using that constructor would make the code longer
		//there's no point in using this()
		this.setSuit(suitList[randomSuit]);
		this.setValue(randomValue);
		
	}
	
	public void setSuit(char s){
		if(s == 's' || s == 'c' || s == 'h' || s == 'd'){//suit can only be 1 of 4 chars
			this.suit = s;
		}
		else{//we're normalizing the 4 chars you can use. this failed
			throw new IndexOutOfBoundsException("Possible suit types are s,c,h, or d");
		}
	}

	public char getSuit(){//returns what we have stored for the char with no fiddling. if you want the full name, use the next method
		return this.suit;
	}
	
	public String getSuitName(){//returns the full names, if you just want the char, the previous method does that
		if(this.suit == 's'){
			return "Spades";
		}
		else if(this.suit == 'c'){
			return "Clubs";
		}
		else if(this.suit == 'h'){
			return "Hearts";
		}
		else if(this.suit == 'd'){
			return "Diamonds";
		}
		else{
			throw new IndexOutOfBoundsException("The card's suit wasn't s,c,h, or d. Somehow.");
			//if it gets down here, the card was corrupted.
		}
	}
	
	public int getValue(){//gets the raw value
		return this.value;
	}
	
	public String getValueName(){//translates the value into their names, if applicable
		if(this.value==1){
			return "Ace";
		}
		else if(this.value==11){
			return "Jack";
		}
		else if(this.value==12){
			return "Queen";
		}
		else if(this.value==13){
			return "King";
		}
		else{
			return Integer.toString(this.value);//send back the value as a string if it's not a special value
		}
	}
	
	public void setValue(int val){
		if(val>=1 && val <= 13){//check the value is between 1 and 13
			this.value = val;
		}
		else{ //that's not what I asked for. too small or large.
			throw new IndexOutOfBoundsException("Value must be between 1 and 13 (inclusive)");
		}
	}
	
	public String toString(){//rather simple method, all the hard work is done elsewhere.
		//System.out.println((char)7); //java doesn't send the command it seems, it just displays a missing character symbol
		//java.awt.Toolkit.getDefaultToolkit().beep(); //this just does this windows error sound
		return "The " + this.getValueName() + " of " + this.getSuitName();
	}

}
