package cardGames;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//* Class name      : CardGame			                       			*
//*                                                                     *
//* Written by      : Zachary Muerle (C) 2014, All rights reserved      *
//*                                                                     *
//* Purpose         : display knowledge of abstract classes             *
//*                                                                     *
//* Inputs          : cards per player								    *
//*                   						                            *
//* Outputs         : none									            *
//*                                                                     *
//* Methods         : CardGame(), dispDescription(), deal(),            *
//*                    setCardsPerPlayer(), getCardsPerPlayer(),        *
//*                    buildDeck(), shuffle(), printDeck()              *
//*                                                                     *
//*---------------------------------------------------------------------*
//* Change Log:                                                         *
//*                         Revision                                    *
//*       Date    Changed  Rel Ver Mod Purpose                          *
//* 11/06/14      ZMuerle  000.000.000 Initial release					*
//*                                                                     *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

import java.util.ArrayList;
import java.util.Collections;

public abstract class CardGame {
	ArrayList<Card> deck = new ArrayList<Card>();
	int cardsPerPlayer;
	
	CardGame(){
		this(7);
	}
	CardGame(int cardsPerPlayer){
		this.setCardsPerPlayer(cardsPerPlayer);
		this.buildDeck();
	}
	
	
	public abstract void dispDescription();
	public abstract void deal();

	public void setCardsPerPlayer(int cards){
		this.cardsPerPlayer = cards;
	}
	public int getCardsPerPlayer(){
		return this.cardsPerPlayer;
	}

	public void buildDeck(){
		char[] suits = {'s','d','h','c'};//possible suits
		for(char suit:suits){//build the deck
			for(int v = 1;v<=13;++v){
				deck.add(new Card(suit,v));
			}
		}
	}

	public void shuffle(){
		Collections.shuffle(deck); //I MAY have rewritten the entire deck just to use this method. I really didn't feel like writing a fair shuffler
	}
	
	public void printDeck(){
		for(Card currCard:deck){//print the cards
			System.out.println(currCard.toString());
		}
	}
}
