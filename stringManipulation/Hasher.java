package stringManipulation;

import java.util.Scanner;

public class Hasher {

	public static void main(String[] args) {
		String word ="";
		Scanner input = new Scanner(System.in);
		while(true){
			word= input.nextLine();
			System.out.println(word.hashCode());
		}
	}

}
