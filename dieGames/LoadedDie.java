package dieGames;


//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//* Class name      : LoadedDie			                                *
//*                                                                     *
//* Written by      : Zachary Muerle (C) 2014, All rights reserved      *
//*                                                                     *
//* Purpose         : create a cheating die						        *
//*                   													*
//*                                                                     *
//* Inputs          : none.  											*
//*                                                                     *
//* Outputs          : ints: either the bounds or  the value of that die*
//* 								                                    *
//*                                                                     *
//* Methods         : all inherited, so none.							*
//*                                                                     *
//*---------------------------------------------------------------------*
//* Change Log:                                                         *
//*                         Revision                                    *
//*       Date    Changed  Rel Ver Mod Purpose                          *
//* 10/22/14      ZMuerle  000.000.000 Initial release of class         *
//*                                                                     *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
public class LoadedDie extends Die {
	public LoadedDie(){
		//this.value = genRandomValue();
																		//this is really nice, everything is already done for me, I just have to reroll if it's a 1
		while(this.value == 1){
			this.reRoll();												//reroll the die until it gets a value not equal to 1
		}
	}
	
	public void reRoll(){
		do{
			super.reRoll();
		}while(this.value == 1);
	}

}
