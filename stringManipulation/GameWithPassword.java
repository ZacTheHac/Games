package stringManipulation;

//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
//* Class name      : GameWithPassword			                        *
//*                                                                     *
//* Written by      : Zachary Muerle (C) 2014, All rights reserved      *
//*                                                                     *
//* Purpose         : ask for a username and password before playing    *									*
//*                                                                     *
//* Inputs          : username, password, and the passwords file	    *
//*                   						                            *
//*                                                                     *
//* Outputs         : positive or negative response to login details    *
//* 									                                *
//*                                                                     *
//* Methods      	: main(), checkUser()                               *
//*                                                                     *
//*---------------------------------------------------------------------*
//* Change Log:                                                         *
//*                         Revision                                    *
//*       Date    Changed  Rel Ver Mod Purpose                          *
//* 11/25/14      ZMuerle  000.000.000 Initial release of program       *
//* 12/02/14      ZMuerle  000.001.000 use err, and handle problems better*
//*                                                                     *
//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class GameWithPassword {

	public static void main(String[] args) {
		Path file = Paths.get("passwords.txt").toAbsolutePath();//comma delimited: "username, hashed_password"
		InputStream input = null;
		ArrayList<String> Usernames = new ArrayList<String>(); //will store all the usernames we load
		ArrayList<Integer> Passwords = new ArrayList<Integer>(); //stores all the password hashes, indexes will be the same
		try {
			input = Files.newInputStream(file); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(input)); 
			String line = "";
			String[] userPass = new String[2]; //will hold the split username and password from the latest line read
			while(true){
				line = reader.readLine();
				if(line!=null){
					try{
						userPass = line.split(", ");
						if(Usernames.contains(userPass[0])){
							System.err.println("ERROR: user "+userPass[0]+" has multiple entries!");
							//originally I just threw the extras away, but I'll add them, just in case
						}
						Usernames.add(userPass[0]);
						Passwords.add(Integer.parseInt(userPass[1]));
					}
					catch(Exception e){
						System.err.println("There was an error reading the line \""+line+"\"");
						//will get called if, say, there's a comma and space in the username, or someone just broke it
						//point is: not my fault, you messed it up.
					}
				}
				else
					break;
			}
			if(Usernames.isEmpty()){
				throw new FileSystemException("Phrases seems to be empty!");
			}
			input.close();
			reader.close();
		}
		catch(Exception e){
			System.err.println("Error while retrieving usernames and passwords.");
			System.err.println(e.getLocalizedMessage());
			System.exit(0);
		}
		Scanner keyInput = new Scanner(System.in);
		String tryUser;
		int tryPass;
		while(true){
			System.out.println("Enter your username:");
			tryUser = keyInput.nextLine();
			System.out.println("Enter your password:");
			tryPass = keyInput.nextLine().hashCode();
			System.out.println("Authenticating...");
			if(checkUser(tryUser, tryPass, Usernames, Passwords)){
				System.out.println("Welcome, "+tryUser+"!");
				SecretPhraseUsingFile.main(args);
				keyInput.close(); //if I close it before the other game, it crashes
				System.out.println("Thanks for playing, "+tryUser+"!");
				break;
			}
			else{
				System.out.println("I'm sorry, there seems to be a problem \nwith that username/password combination");
				continue;
			}


		}

	}
	public static Boolean checkUser(String Username, int PasswordHash, ArrayList<String> users, ArrayList<Integer> passwords){
		for(int i =0; i<users.size(); i++){//just goes through all the usernames. could get really slow really fast. definitely a bad idea for a large database.
			if(users.get(i).equals(Username)){
				if(passwords.get(i)==PasswordHash){//just looks at the same index in the password list
					return true;
				}
				else{
					return false;//their password was wrong
				}
			}
		}
		return false;//username wasn't found
		//funny rule of security that almost no sites follow: you don't tell the end user WHY
		//their username/password combo was wrong, otherwise it can be used to figure out
		//where people have accounts, and use other website's stolen passwords to 
		//break into those sites. however: most sites tell you either "something was wrong" 
		//or "your password is wrong", which they shouldn't.
	}

}
