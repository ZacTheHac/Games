package stringManipulation;
import java.util.Scanner;
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//* Class name      : Eliza			                        *
//*                                                                     *
//* Written by      : Zachary Muerle (C) 2014, All rights reserved      *
//*                                                                     *
//* Purpose         : serve as a very poor therapist			*
//*                   							*
//*                                                                     *
//* Inputs          : any string					*
//*                   						  	*
//*                                                                     *
//* Outputs         : an apropriate (?) response			*
//* 								        *
//*                                                                     *
//* Methods         : main(String[] args)				*
//*                                                                     *
//*---------------------------------------------------------------------*
//* Change Log:                                                         *
//*                         Revision                                    *
//*       Date    Changed  Rel Ver Mod Purpose                          *
//* 09/18/14      ZMuerle  000.000.000 Initial release of program       *
//* 09/23/14      ZMuerle  000.001.000 added more responses		*
//*                                                                     *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *


public class Eliza {//for ch7 GZ4 - not very much a "game", but oh well?

	public static void main(String[] args) {
		System.out.println("Hello. I am Eliza. Say \"Goodbye\" at any time to leave.");
		Scanner inputDevice = new Scanner(System.in);
		
		while(true){
			String input = inputDevice.nextLine();
			if(input.indexOf("love") != -1 || input.indexOf("hate") != -1){
				System.out.println("You seem to have strong feelings about that.");
			}
			if(input.equalsIgnoreCase("goodbye")||input.indexOf("goodbye") != -1||input.indexOf("Goodbye") != -1){
				System.out.println("Goodbye!");
				inputDevice.close();
				break;
			}
			else if(input.indexOf("my ") != -1){
				int nounIndex = input.indexOf("my ") + 3;
				int nounLength = input.substring(nounIndex).indexOf(' ');//find the next space
				String noun;
				if(nounLength == -1){
					noun = input.substring(nounIndex);
				}
				else{
					noun = input.substring(nounIndex, nounIndex+nounLength);
				}
				System.out.println("Tell me more about your "+noun+".");
			}
			else if(input.indexOf("dislike ") != -1||input.indexOf("hate ") != -1){
				int adjectiveIndex = input.indexOf("dislike ");
				if(adjectiveIndex == -1){
					adjectiveIndex = input.indexOf("hate ")+5;
				}
				else{
					adjectiveIndex += 8;
				}
				
				int myIndex = input.substring(adjectiveIndex).indexOf("my ");
				if(myIndex == -1){
					myIndex = adjectiveIndex;
				}
				else{
					myIndex += 3;
				}
				int nounLength = input.substring(myIndex).indexOf(' ');//find the next space
				String noun;
				if(nounLength == -1){
					noun = input.substring(myIndex);
				}
				else{
					noun = input.substring(myIndex, myIndex+nounLength);
				}
				System.out.println("Tell me more about your discontent toward "+noun+".");
			}
			else if(input.indexOf("love ") != -1||input.indexOf("adore ") != -1){
				int adjectiveIndex = input.indexOf("adore ");
				if(adjectiveIndex == -1){
					adjectiveIndex = input.indexOf("love ")+5;
				}
				else{
					adjectiveIndex += 6;
				}
				
				int myIndex = input.substring(adjectiveIndex).indexOf("my ");
				if(myIndex == -1){
					myIndex = adjectiveIndex;
				}
				else{
					myIndex += 3;
				}
				int nounLength = input.substring(myIndex).indexOf(' ');//find the next space
				String noun;
				if(nounLength == -1){
					noun = input.substring(myIndex);
				}
				else{
					noun = input.substring(myIndex, myIndex+nounLength);
				}
				System.out.println("Tell me more about your positive feelings toward "+noun+".");
			}
			else{
				String[] dummyResponse = new String[3];
				dummyResponse[0]="Please go on.";
				dummyResponse[1]="Tell me more.";
				dummyResponse[2]="Continue.";
				short rand = (short) (Math.random() * 3);
				System.out.println(dummyResponse[rand]);
			}
		}

	}

}
