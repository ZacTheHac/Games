package ticTacToe;

import java.util.Scanner;

public class UltimateTTT {

	public static void main(String[] args) {
		UltimateTTTboard sBoard = new UltimateTTTboard(3);
		String turn = "X";
		int[] lastPlayed = null;
		Scanner input = new Scanner(System.in);
		String plyInput = "          ";
		while(true){
			//inside of here, check if the player input is "board" or something, and just display the whole board when they do
			if(lastPlayed != null && sBoard.isPlayable(lastPlayed[0], lastPlayed[1])){//if there was a play last round that can bump them to a specific tile
				sBoard.dispSubBoard(lastPlayed[0], lastPlayed[1]);
				System.out.println("It is "+turn+"'s turn. Pick where to play.");
				plyInput = input.nextLine();
				if(plyInput.equalsIgnoreCase("board")||plyInput.indexOf("board")!=-1){
					sBoard.dispFullBoard();
					System.out.println("");
					sBoard.dispBoard();
					System.out.println("Currently in board "+sBoard.getBoard()[lastPlayed[0]][lastPlayed[1]]);
					System.out.println("\n");
					continue;
				}
				int[] playHolder = sBoard.getCoords(lastPlayed[0], lastPlayed[1], plyInput);
				if(playHolder == null){
					System.out.println("That's not a valid place to play. try again.");
					continue;
				}
				else{//technically I don't need the else, because the continue means the rest of the code is skipped, but it's better for logic breakdown.
					if(!sBoard.tryPlay(lastPlayed[0], lastPlayed[1], playHolder[0], playHolder[1], turn)){
						System.out.println("That's not a valid place to play. try again.");
						continue;
					}
					else{//they played successfully in that spot.
						lastPlayed = playHolder; //move their play into lastPlayed
					}
				}
			}
			else{//they're not bumped to any specific tile, so let them choose a board
				sBoard.dispFullBoard(); //show them every single play, so they can choose a valid tile
				System.out.println("It is "+turn+"'s turn. Pick the board to play on");
				sBoard.dispBoard(); //and a breakdown of who's won where, for ease of tile picking.
				plyInput = input.nextLine();
				lastPlayed = sBoard.getCoords(plyInput);
				if(lastPlayed == null){
					System.out.println("Very funny. Let's try that again, shall we?");
				}//just a warning. it's going to come back here anyways
				continue;
			}
			String winner = sBoard.getWinner();
			if(winner != null){
				sBoard.dispFullBoard();
				System.out.println("");
				sBoard.dispBoard();
				System.out.println(winner + " is the winner!");
				break;									//if we have a winner, break out of the game loop
			}
			if(turn.equals("X"))								//swap the turns
				turn = "O";
			else
				turn = "X";
		}//game loop end
		input.close();
	}
}
