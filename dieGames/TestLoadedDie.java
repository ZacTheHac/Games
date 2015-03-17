package dieGames;


//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//* Class name      : TestLoadedDie			                            *
//*                                                                     *
//* Written by      : Zachary Muerle (C) 2014, All rights reserved      *
//*                                                                     *
//* Purpose         : test the loaded die						        *
//*                   													*
//*                                                                     *
//* Inputs          : none.  											*
//*                                                                     *
//* Outputs         :how many times one die won over the other			*
//* 								                                    *
//*                                                                     *
//* Methods         : main()											*
//*                                                                     *
//*---------------------------------------------------------------------*
//* Change Log:                                                         *
//*                         Revision                                    *
//*       Date    Changed  Rel Ver Mod Purpose                          *
//* 10/22/14      ZMuerle  000.000.000 Initial release of program       *
//*                                                                     *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
public class TestLoadedDie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Die die1 = new Die();
		Die die2 = new Die();
		LoadedDie cheaterDie = new LoadedDie();
		int counter = 0;
		System.out.println("Rolling dice...");
		for(int i = 1; i<=1000; ++i){
			die1.reRoll();
			die2.reRoll();
			if(die1.getValue()>die2.getValue()){
				counter++;
			}
		}
		System.out.println("The first die was higher than the 2nd "+counter+" times out of 1000.");
		counter = 0;
		System.out.println("Rolling loaded die...");
		for(int i = 1; i<=1000; ++i){
			die1.reRoll();
			cheaterDie.reRoll();
			if(die1.getValue()>cheaterDie.getValue()){
				counter++;
			}
		}
		System.out.println("The legit die was higher than the loaded "+counter+" times out of 1000.");

	}

}
