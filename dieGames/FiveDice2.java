package dieGames;



public class FiveDice2 {//ignore the fact that we never did FiveDice - for CH8 GZ2a

	public static void main(String[] args) {
		Die[] plyDice = new Die[5]; 		//an array holding the player's dice
		Die[] compDice = new Die[5]; 		//an array holding the computer's dice
		Object[] nKindHolder = new Object[2];	//this holds the outcome of nKind Checking.
		int[] plyData = new int[2];
		int[] compData = new int[2];
		//both of these hold the data for the player or computer.
			//0 is their score
			//1 is their winning die
		
		for(int d = 0;d<5;d++){			//loop 5 times to give the player and computer 5 random dice
			plyDice[d]=new Die();		//brand new die for the player
			compDice[d]=new Die();		//and one for the computer
							//found out each die must be unique: by putting one object into the array, it's not a copy, it is a reference
		}
		for(int p = 0;p<5;p++){			//print out the player's dice
			System.out.println("Player Die #"+(p+1)+": "+plyDice[p].getValue());
		}
		for(int c = 0;c<5;c++){			//prints out the computer's dice
			System.out.println("Computer Die #"+(c+1)+": "+compDice[c].getValue());
		}
		
							//get the player's score
		for(int i = 5;i>=2;i--){		//goes from 5 to 2, searching for i of a kind
			nKindHolder = containsNOfAKind(plyDice,i);
			if((Boolean) nKindHolder[0]){
				plyData[0] = i;		//if they have that many, then that's their score
				plyData[1] = (Integer) nKindHolder[1];
				break;			//and we can stop searching, as we search for the highest score first
			}
		}
							//get the computer's score
		for(int i = 5;i>=2;i--){		//goes from 5 to 2, searching for i of a kind
			nKindHolder = containsNOfAKind(compDice,i);
			if((Boolean) nKindHolder[0]){
				compData[0] = i;	//if they have that many, then that's their score
				compData[1] = (Integer) nKindHolder[1];
				break;			//and we can stop searching, as we search for the highest score first
			}
		}
		if(plyData[0]>compData[0]){
			System.out.println("The player wins with a "+getScoreName(plyData[0])+"! beating the computer's "+getScoreName(compData[0])+"!");
		}
		else if(compData[0]>plyData[0]){
			System.out.println("The computer wins with a "+getScoreName(compData[0])+"! beating the player's "+getScoreName(plyData[0])+"!");
		}
		else{					//they have equal scores
			System.out.println("Both of you lose, having "+getScoreName(plyData[0])+"s!");
			if(compData[1]>plyData[1]){//the computer wins the tie
				System.out.println("But the Computer wins, as his winning die were "+compData[1]+"s, and the player's were "+plyData[1]+"s");
			}
			else if(plyData[1]>compData[1]){//the player wins the tie
				System.out.println("But the Player wins, as their winning die were "+plyData[1]+"s, and the computer's were "+compData[1]+"s");
			}
			else{				//they tied in the tie.
				System.out.println("Also: you both double-fail, as both of you won with "+plyData[1]+"s on the die. I have never been so dissapointed.");
			}
		}
	}
	public static String getScoreName(int score){	//returns a string based on their score (better than repeated if statements in the middle of the code)
		if(score > 2){//if their score is more than 2, it's just an n of a kind
			return score+" of a kind";
		}
		else if(score == 2){//if it's 2 it's called a pair
			return "pair";
		}
		else if(score < 2){//otherwise they got nothing (1 of a kind is nothing special, I don't even check for it initially. it's still score 0)
			return "NOTHING";
		}
		return "Your broke something! :D";	//I have to have a definite return or eclipse hates me, but if it gets here, something went wrong
	}
	public static Object[] containsNOfAKind(Die[] dieArray, int n){//check if an array has n of a kind, and return a true/false, and if true: the die value that made it true
		Object[] returnVal = new Object[2];
		returnVal[1]=0;				//make sure it's 0, to make it obvious  that it failed, if it passes, this value is set
							//this is my return object - 0 is a bool if it passed, and 1 is what value passed it
		int maxValue = dieArray[0].getHighest();
		int minValue = dieArray[0].getLowest();	//get the min and max value of the dice.
		int arrayLength = dieArray.length; 	//get the length of the array
		if(n>arrayLength){			//if they're asking for a higher n than the number of dice provided, it's not possible.
			returnVal[0]=false;
			return returnVal;
		}
							//I assume all dice have the same bounds. if not: then whoever messed with my code can fix it
		int count = 0;//holds the number of dice with equal value
		for(int i = maxValue;i>=minValue;--i){	//go through every possible value for the dice
							//I made it go backwards so it finds the highest value that satisfy it.
							//previously: it would find the lowest value, which is bad if you want to 
							//determine the higher value, and one person has 2 2's, but also 2 6's
			count = 0;
			for(int d = 0;d<arrayLength;d++){//go through every die in the array
				if(dieArray[d].getValue()==i){//if the value of this die is equal to the value we're looking for
					count++;	//increment the counter
				}
				if(count >= n){		//if we found n or more dice of this value, then this array contained an n of a kind
					returnVal[1] = i;
					returnVal[0] = true;
					return returnVal;
				}
			}
			
		}
		
							//if it got outside the loops, then not enough dice were found to make an n of a kind.
		returnVal[0]=false;
		return returnVal;
	}

}
