package cardGames;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//* Class name      : FiftyTwoCardPickup                       			*
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

public class FiftyTwoCardPickup extends CardGame {

	@Override
	public void dispDescription() {
		System.out.println("It's a fun game! here, I'll show you!");

	}

	@Override
	public void deal() {
		this.shuffle();
		for(Card card:deck){
			System.out.println(card.toString());
		}
		//since it's just throwing all the cards away, I can remove all the cards after.
		deck.clear();
		System.out.println("Now pick it all up!");

	}

}
