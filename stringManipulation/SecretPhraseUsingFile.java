package stringManipulation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//* Class name      : SecretPhraseUsingFile			                    *
//*                                                                     *
//* Written by      : Zachary Muerle (C) 2014, All rights reserved      *
//*                                                                     *
//* Purpose         : read a file that is a list of words, and play     *
//*                   hangman with it									*
//*                                                                     *
//* Inputs          : a char for the guess, and the phrases file	    *
//*                   						                            *
//*                                                                     *
//* Outputs          : how much has been guessed, and instructions	    *
//* 									                                *
//*                                                                     *
//* Methods      : main, getGoodGuesses(), getBadGuesses(), countGuesses()*
//*                   isWinner(), getStringDisplay(), getRandomPhrase(),*
//*                   getUniqueLetterCount(), checkWinner(), 	        *
//*                   getGoodBadGuesses()                               *
//*                                                                     *
//*---------------------------------------------------------------------*
//* Change Log:                                                         *
//*                         Revision                                    *
//*       Date    Changed  Rel Ver Mod Purpose                          *
//* 11/18/14      ZMuerle  000.000.000 Initial release of program       *
//* 12/04/14      ZMuerle  000.001.000 modified the balance of the game *
//*                                                                     *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

public class SecretPhraseUsingFile {

	public static void main(String[] args) {
		String toGuess = getRandomPhrase();
		ArrayList<Character> guessed = new ArrayList<Character>();//holds the letters we have guessed
		Scanner input = new Scanner(System.in);
		String guess =null;//the current guess by the user
		char charGuess = '0';//if the user only puts a single character, it's put in here

		while(true){
			System.out.println(getStringDisplay(toGuess, guessed));
			System.out.println("Guess a letter.");
			guess = input.nextLine().toLowerCase();
			if(guess.length()!=1){
				if(guess.equals(toGuess)){
					System.out.println("Congrats, you guessed the word, "+toGuess+"!");
					break;
				}
				else{
					guessed.addAll(newGuesses(guess, guessed));
					if(checkWinner(toGuess, guessed)){
						break;
					}
				}
			}
			else{
				charGuess = guess.charAt(0);
				if(guessed.contains(charGuess)){
					System.out.println("You already guessed "+charGuess);
					continue;
				}
				guessed.add(charGuess);
				if(checkWinner(toGuess, guessed)){
					break;
				}
			}

		}
		input.close();

	}

	public static boolean checkWinner(String word, ArrayList<Character> guesses){
		int maxErrors = (int) Math.round((27-getUniqueLetterCount(word))*0.3);
		if(maxErrors < 5)
			maxErrors = 5;//make sure they have SOME wiggle room
		//System.out.println("You get "+maxErrors+" wrong guesses");
		int[] guessCounts = new int[2];//holds how many good and bad guesses the person has made
		guessCounts = countGuesses(word, guesses);
		System.out.println(getGoodGuesses(word, guesses));
		System.out.println(getBadGuesses(word, guesses));
		if(guessCounts[1] > maxErrors){//only allowed a certain amount of bad guesses
			System.out.println("I'm sorry, but you guessed incorrectly too many times. "
					+ "\n The word was \""+word+"\"");
			return true;
		}
		if(isWinner(word, guesses)){
			System.out.println("The word was \""+word+"\"!");
			return true;
		}
		return false;
	}

	public static int getUniqueLetterCount(String word){
		ArrayList<Character> letters = new ArrayList<Character>();
		for(int i = 0; i<word.length(); i++){
			if(!letters.contains(word.charAt(i))){
				letters.add(word.charAt(i));
			}
		}
		return letters.size();
	}
	
	public static ArrayList<Character> newGuesses(String failedAttempt, ArrayList<Character> guesses){
		ArrayList<Character> newGuesses = new ArrayList<Character>();
		char[] failedList = failedAttempt.toCharArray();
		for(Character lett:failedList){
			if(!guesses.contains(lett) && !newGuesses.contains(lett)){//I had to add the 2nd check in case they enter the same letter twice
				newGuesses.add(lett);
			}
		}
		return newGuesses;
	}
	@SuppressWarnings("unchecked")//shh.. I know what I'm doing. this will always return character arrays
	public static ArrayList<Character>[] getGoodBadGuesses(String word, ArrayList<Character> letters){
		ArrayList<Character> good = new ArrayList<Character>();
		ArrayList<Character> bad = new ArrayList<Character>();
		for(Character letter: letters){
			if(word.contains(letter.toString())){
				good.add(letter);
			}
			else{
				bad.add(letter);
			}
		}
		return new ArrayList[] {good, bad};
	}
	public static String getGoodGuesses(String word, ArrayList<Character> letters){
		String ret ="Correct Guesses: ";
		for(Character let:letters){
			if(word.contains(let.toString())){
				ret += let.toString() + ", ";
			}
			else{
				continue;
			}
		}
		if(ret.equals("Correct Guesses: ")){
			return "You have not yet made a correct guess.";
		}
		else{
			return ret.substring(0, ret.length()-2);
		}
	}

	public static String getBadGuesses(String word, ArrayList<Character> letters){
		String ret ="Incorrect Guesses: ";
		for(Character let:letters){
			if(word.contains(let.toString())){
				continue;
			}
			else{
				ret += let + ", ";
			}
		}
		if(ret.equals("Incorrect Guesses: ")){
			return "You have not yet made an incorrect guess, good job!";
		}
		else{
			return ret.substring(0, ret.length()-2);
		}
	}

	public static int[] countGuesses(String word, ArrayList<Character> letters){
		int goodGuesses = 0;
		int badGuesses = 0;
		for(Character let: letters){
			if(word.contains(let.toString())){
				goodGuesses++;
			}
			else{
				badGuesses++;
			}
		}
		return new int[] {goodGuesses, badGuesses};
	}

	public static Boolean isWinner(String word, ArrayList<Character> letters){
		for(int i = 0; i<word.length(); i++){
			if(!letters.contains(word.charAt(i))){
				return false;
			}
		}
		return true;
	}
	public static String getStringDisplay(String string, ArrayList<Character> letters){
		String ret = "";
		for(int i = 0; i<string.length(); i++){
			if(letters.contains(string.charAt(i))){
				ret += string.charAt(i);
			}
			else{
				ret += "_";
			}
		}
		return ret;
	}
	public static String getRandomPhrase(){
		Path file = Paths.get("phrases.txt").toAbsolutePath();
		InputStream input = null;
		ArrayList<String> lines = new ArrayList<String>();
		try {
			input = Files.newInputStream(file); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(input)); 
			String line = "";
			while(true){
				line = reader.readLine();
				if(line!=null)
					lines.add(line);
				else
					break;
			}
			if(lines.isEmpty()){
				throw new FileSystemException("Phrases seems to be empty!");
			}
			else{
				int length = lines.size();
				int randomLine = (int) Math.floor(Math.random()*(length));
				return lines.get(randomLine).toLowerCase();
			}
		}
		catch(Exception ex){
			System.err.println("An exception occured while trying to read the phrases file:");
			System.err.println(ex.getLocalizedMessage());
			ex.printStackTrace();
			return "HIPPOCAMPUS".toLowerCase(); //to end the program right here
		}
		//return "HIPPOCAMPUS";//it didn't want me to possibly not return anything.


	}

}
