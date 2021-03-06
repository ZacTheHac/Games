package ticTacToe;

public class UltimateTTTboard extends TTTboard {
	private TTTboard[][] uBoard;
	private int uBoardSize = 3;
	private TTTboard displayBoard; //will be used to select sector and display winners
	
	UltimateTTTboard(){
		this(3);
	}
	UltimateTTTboard(int size){
		this.setGridSize(size);
		uBoard = new TTTboard[this.getGridSize()][this.getGridSize()];
		//TTTboard tempBoard = new TTTboard(this.getGridSize());
		for(int r = 0; r<size; ++r){
			for(int c = 0; c<size; ++c){
				uBoard[r][c] = new TTTboard(this.getGridSize());
				//should be able to clone 1 to every single one, but cloning is proving to be difficult
			}
		}
		displayBoard = new TTTboard(this.getGridSize());
	}
	
	void setGridSize(int size){
		if(isOdd(size) && size > 1){
			this.uBoardSize=size;
		}
		else{
			throw new IndexOutOfBoundsException(size+" is not >1 and odd");
		}
	}
	
	int getGridSize(){
		return this.uBoardSize;
	}
	
	String[][] getBoard(){
		return this.displayBoard.getBoard();
	}
	TTTboard getSubBoard(int y, int x){
		int maxGridIndex = this.getGridSize()-1;
		if(x>maxGridIndex||x<0||y>maxGridIndex||y<0){
			throw new IndexOutOfBoundsException("The values "+x+" or "+y+"were outside the bounds of 0-"+maxGridIndex);
		}
		else{
			return uBoard[y][x];
		}
	}
	TTTboard getSubBoard(String number){
		if(isInt(number)){
			int num = Integer.parseInt(number);
			this.getWinner();
			for(int x = 0; x<this.getGridSize(); ++x){
				for(int y = 0; y<this.getGridSize(); ++y){
					if(Integer.parseInt(displayBoard.getBoard()[y][x]) == num){				//go through every value in the array, searching for the string
						return this.getSubBoard(y,x);
					}
				}
			}
		}
		return null;
	}
	
	boolean isPlayable(int boardy, int boardx, int y, int x){
		this.getWinner();
		TTTboard subBoard = this.getSubBoard(boardy,boardx);
		return subBoard.isPlayable(y, x);
		//all the error checking is already in these methods
	}
	boolean isPlayable(int y, int x){
		this.getWinner();
		return displayBoard.isPlayable(y, x);
	}
	
	void play(int boardy, int boardx, int y, int x, String player){
		this.getSubBoard(boardy, boardx).play(y, x, player);
	}
	boolean tryPlay(int boardy, int boardx, int x, int y, String player){
		if(this.isPlayable(boardy, boardx, x, y)){
			play(boardy, boardx, x, y, player);
			return true;
		}
		else{
			return false;
		}
	}
	boolean tryPlay(int boardy, int boardx, int spotNumber, String player){
		int[] coords = this.getCoords(boardy, boardx, spotNumber);
		if(coords!= null){
			return tryPlay(boardy, boardx, coords[0], coords[1], player);
		}
		else{
			return false;
		}
	}
	
	int[] getCoords(int place){
		return this.displayBoard.getCoords(place);
	}
	int[] getCoords(String place){
		if(isInt(place)){
			return this.getCoords(Integer.parseInt(place));
		}
		else{
			return null;
		}
	}
	int[] getCoords(int y, int x, String place){
		if(isInt(place)){
			return this.getSubBoard(y, x).getCoords(Integer.parseInt(place));
		}
		else{
			return null;
		}
	}
	int[] getCoords(int y, int x, int place){
		return this.getSubBoard(y, x).getCoords(place);
	}
	
	void dispBoard(){
		this.getWinner();
		displayBoard.dispBoard();
	}
	void dispFullBoard(){
		int size = this.getGridSize();
		int totalWidth = (int) Math.pow(size, 2);
		int indexLength = Integer.toString(totalWidth).length();//holds how long the numbers can get (useful for formatting 5x5 for instance)
		int lineWidth = (indexLength*totalWidth)+(2*(size-1))+((size-1)*(size)); //that's characters + the board separators + the board's separators*number of boards
		String line = "";

		for(int h = 0;h<size;++h){//which row of boards we're in
			for(int i = 0;i<size;++i){//which row in those boards
				for(int j = 0;j<size;++j){//which board in that row
					for(int k = 0; k<size;++k){//the collum of the board we're in
						line+= this.uBoard[h][j].getBoard()[i][k];
						if(k+1<size){
							line += "|";
						}
						else if(k+1==size && j+1<size){
							line += "<>";
						}
					}//k end
				}//j end
				System.out.println(line);
				if(i<size-1){
					line = "";
					for(int q = 1; q<=lineWidth ; ++q){
						line+="-";
					}
					System.out.println(line);
				}
				line="";
			}//i end
			if(h+1<size){
				for(int q = 1; q<=lineWidth ; ++q){
					line+="=";
				}
				System.out.println(line);
				line="";
			}

		}//h end
	}
	
	void dispSubBoard(int y, int x){
		this.getSubBoard(y, x).dispBoard();
	}
	
	String getWinner(){
		for(int y = 0; y < this.getGridSize(); ++y){
			for(int x = 0; x < this.getGridSize(); ++x){
				String win = this.getSubBoard(y, x).getWinner();
				if(win!=null){
					if(win.equalsIgnoreCase("Nobody")){
						displayBoard.play(y, x, "N");
					}
					else{
						displayBoard.play(y, x, win);
					}
				}
			}
		}
		String winner = displayBoard.getWinner();
		if(winner != null){
			return winner;
		}
		else{
			return null;
		}
	}

}
