package cardGames;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//* Class name      : Poker	                                   			*
//*                                                                     *
//* Written by      : Zachary Muerle (C) 2014, All rights reserved      *
//*                                                                     *
//* Purpose         : display knowledge of abstract classes             *
//*                                                                     *
//* Inputs          : none											    *
//*                   						                            *
//* Outputs         : delt cards and how to play			            *
//*                                                                     *
//* Methods         : Main(), dispDescription(), deal()                 *
//*                                                                     *
//*---------------------------------------------------------------------*
//* Change Log:                                                         *
//*                         Revision                                    *
//*       Date    Changed  Rel Ver Mod Purpose                          *
//* 11/06/14      ZMuerle  000.000.000 Initial release with flowerbox   *
//*                                                                     *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

public class Poker extends CardGame {

	Poker(){
		super(5);
	}
	@Override
	public void dispDescription() {
		System.out.println("You're dealt 5 cards, and you try to make the best combination or something. Yay gambling!");

	}

	@Override
	public void deal() {
		this.shuffle();
		for(int i=1; i<=this.getCardsPerPlayer(); i++){
			Card delt = deck.get(0);//just grabs the first off the stack, just like a real dealer
			deck.remove(delt);
			System.out.println(delt.toString());
		}

	}

}
