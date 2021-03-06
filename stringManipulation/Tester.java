package stringManipulation;

import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		Scanner inp = new Scanner(System.in);
		String tryToType = "nape";
		while(true){
			System.out.println("try to type \""+tryToType+"\"");
			String input = inp.nextLine();
			int distance = FuzzyMatch.LevenshteinDistance(tryToType, input);
			System.out.println("That was "+distance+" away.");
			distance = FuzzyMatch.LevenshteinDistance("close", input);
			if(distance <= 2){
				System.out.println("but only "+distance+" away from \"close\"!");
				break;
			}
		}
		inp.close();
	}

}
