package ticTacToe;

public class TTTboard{
	private int gridsize = 3;
	private String[][] board;
	private String winner = null;
	
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

			//now check that there is still a valid place to play
			for(String[] row : board){
				for(String point : row){
					if(isInt(point)){
						this.winner = null;
						return this.winner;							//if there is an integer in any point, it's a valid place to play
					}
				}
			}
			this.winner = "Nobody";
			return this.winner;									//if it got here, nobody won, but there are no more valid places to play. nobody wins.
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
