package ticTacToe;

public class TTTboard{
	private int gridsize; //this just holds the value. Modifying it here does nothing
	private String[][] board; //holds all possible plays
	private String winner = null;
	//this is only global so that if a board is won, we don't have to keep checking it.
	//Once there's a winner, they win
	//we don't do that "2 winners" BS
	
	TTTboard(){
		this(3);
	}
	TTTboard(int size){
		this.setGridSize(size);
		board = new String[this.gridsize][this.gridsize];
		this.resetBoard();
	}
	
	void resetBoard(){
		int indexLength = Integer.toString((int) Math.pow(this.gridsize, 2)).length();//holds how long the numbers can get (useful for formatting 5x5 for instance)
		int index = 1;										//holds a value to fill in the grid
		for(int r = 0; r<this.gridsize;++r){
			for(int c = 0; c<this.gridsize; ++c){
				board[r][c] = String.format("%0"+indexLength+"d", index);		//fill the grid with the current "index" value, padded with 0s if needed
				index++;
			}
		}
	}

	String[][] getBoard(){
		return this.board;
	}
	
	void setGridSize(int size){
		if(size > 1 && isOdd(size)){
			this.gridsize = size;
			board = new String[gridsize][gridsize];
		}
		else{
			throw new NumberFormatException("size must be greater than 1 and odd");
		}
	}
	
	int getGridSize(){
		return this.gridsize;
	}
	
	protected static boolean isOdd(int i){
		if(i%2!=0){
			return true;
		}
		return false;
	}
	
	protected static boolean isInt(String input){				//returns true if a string is an int, false otherwise
		try{
			Integer.parseInt(input);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}
	
	boolean WinPossible(){
		return this.WinPossible(false);
	}
	boolean WinPossible(boolean printResults){//Checks if a board is even possible to win on (even if there are still open spaces)
		boolean ImpossibleWin = false;//will hold if the current check resulted in no possible win
		char currWinner = 'N';
		int[] PossibleWins = new int[3]; //x,o,neither
		//go down each collumn
		for(int coll = 0; coll<gridsize;coll++){
			currWinner = 'N';
			for(int row = 0; row < gridsize; row++){
				if(!this.isPlayable(row, coll)){
					//place is played
					if(currWinner == 'N'){
						currWinner = this.getSpotInfo(row, coll).charAt(0);//get the first character since it should be the player
					}//if there has been no player until now, store it
					else if(currWinner == this.getSpotInfo(row, coll).charAt(0)){
						//They're already winning, so continue
						continue;
					}
					else{//it's not the current winner, so we know that there are 2 players in this collumn, and is thus
						PossibleWins[2]++;
						ImpossibleWin = true;
						break;
					}
				}
			}
			if (!ImpossibleWin) {
				if (currWinner == 'X')
					PossibleWins[0]++;
				else if (currWinner == 'O')
					PossibleWins[1]++;
				else {
					PossibleWins[0]++;
					PossibleWins[1]++;
				} // if neither are winning a row, but it's not impossible,
					// either can win;
			} else {
				ImpossibleWin = false;// make sure to reset it after
			}
		}
		
		//go across each row
		currWinner = 'N';//honestly I'm only putting this so I can debug before the loop
		for(int row = 0; row<gridsize;row++){
			currWinner = 'N';
			for(int coll = 0; coll < gridsize; coll++){
				if(!this.isPlayable(row, coll)){
					//place is played
					if(currWinner == 'N'){
						currWinner = this.getSpotInfo(row, coll).charAt(0);//get the first character since it should be the player
					}//if there has been no player until now, store it
					else if(currWinner == this.getSpotInfo(row, coll).charAt(0)){
						//They're already winning, so continue
						continue;
					}
					else{//it's not the current winner, so we know that there are 2 players in this collumn, and is thus
						PossibleWins[2]++;
						ImpossibleWin=true;
						break;
					}
				}
			}
			if (!ImpossibleWin) {
				if (currWinner == 'X')
					PossibleWins[0]++;
				else if (currWinner == 'O')
					PossibleWins[1]++;
				else {
					PossibleWins[0]++;
					PossibleWins[1]++;
				} // if neither are winning a row, but it's not impossible,
					// either can win;
			} else {
				ImpossibleWin = false;// make sure to reset it after
			}
		}
		//check diagonals
		//left-to-right
		currWinner = 'N';
		for(int i = 0;i<gridsize;i++){
			if(!this.isPlayable(i, i)){
				//place is played
				if(currWinner == 'N'){
					currWinner = this.getSpotInfo(i, i).charAt(0);//get the first character since it should be the player
				}//if there has been no player until now, store it
				else if(currWinner == this.getSpotInfo(i, i).charAt(0)){
					//They're already winning, so continue
					continue;
				}
				else{//it's not the current winner, so we know that there are 2 players in this collumn, and is thus
					PossibleWins[2]++;
					ImpossibleWin=true;
					break;
				}
			}
		}
		if (!ImpossibleWin) {
			if (currWinner == 'X')
				PossibleWins[0]++;
			else if (currWinner == 'O')
				PossibleWins[1]++;
			else {
				PossibleWins[0]++;
				PossibleWins[1]++;
			} // if neither are winning a row, but it's not impossible,
				// either can win;
		} else {
			ImpossibleWin = false;// make sure to reset it after
		}
		//right-to-left
		currWinner = 'N';
		for(int i = 0;i<gridsize;i++){
			int coll = (gridsize - 1)-i;
			if(!this.isPlayable(i, coll)){
				//place is played
				if(currWinner == 'N'){
					currWinner = this.getSpotInfo(i, coll).charAt(0);//get the first character since it should be the player
				}//if there has been no player until now, store it
				else if(currWinner == this.getSpotInfo(i, coll).charAt(0)){
					//They're already winning, so continue
					continue;
				}
				else{//it's not the current winner, so we know that there are 2 players in this collumn, and is thus
					PossibleWins[2]++;
					ImpossibleWin=true;
					break;
				}
			}
		}
		if (!ImpossibleWin) {
			if (currWinner == 'X')
				PossibleWins[0]++;
			else if (currWinner == 'O')
				PossibleWins[1]++;
			else {
				PossibleWins[0]++;
				PossibleWins[1]++;
			} // if neither are winning a row, but it's not impossible,
				// either can win;
		} else {
			ImpossibleWin = false;// make sure to reset it after
		}
		
		if(printResults){
			System.out.println("X can win in "+PossibleWins[0]+" ways.");
			System.out.println("O can win in "+PossibleWins[1]+" ways.");
			System.out.println(PossibleWins[2]+" ways are impossible.");
		}
		
		if(PossibleWins[2] == (2+gridsize*2))//if all possible ways have no possible wins, it's impossible
			return false;
		return true;//otherwise it's still possible to win.
		
	}
	
	String getWinner(){//returns a winner, if there is one. null otherwise.
		if(winner != null){
			return winner;
		}
			//go through the first row & colum, if any of them is an X or O, search for neighbors of the same
			//if there is one, follow that line until we find *boardlength* in a row, or it is no longer the X or O
			//make sure to check there are still valid places to play at the end. if there aren't, then it's a tie and everyone loses
			int boardLength = gridsize;
			for(int i = 0; i<boardLength; ++i){
				//check board[0][i]
				if(!isInt(board[0][i])){			//the spot has been played (if it hasn't, it would be an int)
					String checkFor = board[0][i];		//who we're checking for
					if(i==0 && board[0][i+1].equals(checkFor)){//it can only be a row going right if I start at the far left. otherwise, there's no reason to check
						//check going right
						int counter = 0;
						for(int t = 0;t<boardLength;++t){//go through the entire row
							if(!board[0][t].equals(checkFor)){
								break;																	//if any of them isn't the person we're checking for, then this fails
							}
							else{
								counter++;							
							}
						}
						if(counter == boardLength){	//if all of them are, then we have a winner
							this.winner = checkFor;
							return this.winner;
						}

					}

					if(i==0 && board[1][i+1].equals(checkFor)){		//check down and to the right
						int counter = 0;
						for(int t = 0; t<boardLength; ++t){
							if(board[t][t].equals(checkFor)){
								counter++;
							}
							else{
								break;
							}
						}
						if(counter == boardLength){
							this.winner = checkFor;
							return this.winner;
						}
					}
					if(i==(boardLength-1)  && board[1][i-1].equals(checkFor)){//check down and to the left
						int counter = 0;
						for(int t = 0; t<boardLength; ++t){
							if(board[boardLength-1-t][t].equals(checkFor)){
								counter++;
							}
							else{
								break;
							}
						}
						if(counter==boardLength){
							this.winner = checkFor;
							return this.winner;
						}

					}
					if(board[1][i].equals(checkFor)){			//check down, as this just checks the top row
						int counter = 0;
						for(int t = 0; t<boardLength; ++t){
							if(board[t][i].equals(checkFor)){
								counter++;
							}
							else{
								break;
							}
						}
						if(counter == boardLength){
							this.winner = checkFor;
							return this.winner;
						}

					}


				}
			}
			//now we can check board[i][0] - all we have to do here is going right, because all other possibilities are checked already
			for(int i = 1;i<boardLength;++i){
				if(!isInt(board[i][0])){							//the spot has been played (if it hasn't, it would be an int)
					String checkFor = board[i][0];					//who we're checking for
					if(board[i][1].equals(checkFor)){
						int counter = 0;
						for(int t = 0; t<boardLength; ++t){
							if(board[i][t].equals(checkFor)){
								counter++;
							}
							else{
								break;
							}

						}
						if(counter == boardLength){
							this.winner = checkFor;
							return this.winner;
						}
					}
				}
			}

			/*//now check that there is still a valid place to play
			for(String[] row : board){
				for(String point : row){
					if(isInt(point)){
						this.winner = null;
						return this.winner;
						//if there is an integer in any point, it's a valid place to play
					}
				}
			}*/
			if(this.WinPossible()){
				this.winner = null;
				return this.winner;
			}//nobody has won, but it's still possible to win
			else{
				this.winner = "Nobody";
				return this.winner;
			}
			//if it got here, nobody won, but there are no more winning plays. nobody wins.
		}

	boolean isPlayable(int y, int x){
		int maxIndex = this.gridsize-1;
		if(x>maxIndex||x<0||y>maxIndex||y<0){
			throw new IndexOutOfBoundsException("The values "+x+" or "+y+"were outside the bounds of 0-"+maxIndex);
		}
		else{
			return isInt(board[y][x]);
		}
	}
	
	String getSpotInfo(int y, int x){
		int maxIndex = this.gridsize-1;
		if(x>maxIndex||x<0||y>maxIndex||y<0){
			throw new IndexOutOfBoundsException("The values "+x+" or "+y+"were outside the bounds of 0-"+maxIndex);
		}
		else{
			return board[y][x];
		}
	}
	
	int getSpotNumber(int y, int x){
		if(this.isPlayable(y,x)){
			return Integer.parseInt(board[y][x]);
		}
		return 0;
	}
	
	boolean isValidSquareNumber(int number){
		if(number > Math.pow(gridsize, 2)||number<1){
			return false;
		}
		return true;
	}
	boolean isValidSquareNumber(String number){
		if(isInt(number)){
			return isValidSquareNumber(Integer.parseInt(number));
		}
		return false;
	}

	int[] getCoords(int value){			//returns the coords of a string in the array (null if it doesn't exist)
		for(int y = 0; y<this.gridsize; ++y){
			for(int x = 0; x<this.gridsize; ++x){
				if(isInt(board[y][x]) && Integer.parseInt(board[y][x]) == value){				//go through every value in the array, searching for the string
					int[] Coords = {y,x};					//if it's found: create an int[] that has that string's coords
					return Coords;							//and send it back
				}
			}
		}
		
		return null;										//if it gets here, the string doesn't exist in the array
	}

	void play(int y, int x, String player){
		board[y][x] = player;
	}//if they send something wrong, it'll just break everything. it's their own fault for not checking. I have a method that does checking on its own

	boolean tryPlay(String spotNumber, String player){
		if(isInt(spotNumber)){
			return tryPlay(Integer.parseInt(spotNumber),player);
		}
		return false;
	}
	boolean tryPlay(int spotNumber, String player){
		if(isValidSquareNumber(spotNumber)){
			int[] coords = getCoords(spotNumber);
			if(coords == null){
				return false;
			}
			else{
				play(coords[0],coords[1],player);
				return true;
			}
		}
		else{
			return false;
		}
	}

	void dispBoard(){			//prints the board to the screen
		int size = this.getGridSize();
		int indexLength = Integer.toString((int) Math.pow(size, 2)).length();//holds how long the numbers can get (useful for formatting 5x5 for instance)
		int lineWidth = (indexLength*size)+(size-1); //that's characters + the board's separators
		String line = "";
		for(int r = 0;r<size;++r){
			for(int c = 0;c<size;++c){
				line+= board[r][c];
				if(c+1<size)
					line += "|";
			}
			System.out.println(line);
			if(r+1<size){
				line = "";
				for(int i = 1; i<=lineWidth ; ++i){
						line+="-";
				}
				System.out.println(line);
			}
			line="";
		}
		
	}
	
}
