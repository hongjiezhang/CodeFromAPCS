public class Minefield {
	public static void main(String[] args) {
		
	}
	
	public Square[][] grid;
	public boolean win;
	
	public Minefield(String d) {
		grid = makeGrid(d);
		win = false;
	}
	
	
	

	/**
	 * 
	 * When called, it makes a 2D grid of squares 
	 * the grids size depends on it difficulty
	 * the grid is populated with mines (using popMines())
	 * and populated with numbers (using popNums())
	 * 
	 * 
	 * @param difficulty 
	 * @return Square[][]
	 */
	public Square[][] makeGrid(String difficulty) {
		Square[][] a;
		if (difficulty.equals("beginner")) { 			//sets grid size to 8x8 if its an "easy" game
			Square[][] b = new Square[8][8];
			//grid = tmp;
			for ( int r = 0 ; r < b.length ; r++ ) {
				for ( int c = 0 ; c < b[r].length ; c++ ) {
					b[r][c] = new Square();
				}
			}
			a = popMines(b, "beginner");
			a = popNums(b);
			
		} 
		else if (difficulty.equals("intermediate")) {		//sets grid size to 12x12 if its a "medium" game
			Square[][] b = new Square[12][12];
			
			for ( int r = 0 ; r < b.length ; r++ ) {
				for ( int c = 0 ; c < b[r].length ; c++ ) {
					b[r][c] = new Square();
				}
				
			}
			
			a = popMines(b, "intermediate");
			a = popNums(b);
			
			
		} else {							//sets grid size to 15x15 if its a "hard" game
			Square[][] b = new Square[15][15];
			
			for ( int r = 0 ; r < b.length ; r++ ) {
				for ( int c = 0 ; c < b[r].length ; c++ ) {
					b[r][c] = new Square();
				}
			}
			
			a = popMines(b, "advanced");
			a = popNums(b);
			
		}
		return a;
	}




	/**
	 * 
	 * Takes in a 2D array of squares and randomly makes ~16% of
	 * the squares in the grid true for bomb (a mine)
	 * 
	 * @param z : a 2D grid of squares
	 * @param difficulty : a string representing the difficulty of the game
	 * @return The grid, with ~16% of the squares set true for mines
	 */
	public Square[][] popMines(Square z[][], String difficulty) {		//populates the grid with mines


		if (difficulty.equals("beginner")) {					//if the game is easy, sets 10 random squares to be true for mine/bomb
			for (int a = 0 ; a < 10 ; a++) {
				int b = (int)(Math.random()*8);
				//System.out.println(b);
				int c = (int)(Math.random()*8);
				//System.out.println(c);
					if (z[b][c].getBomb() == true) {
						a--;
					} else {
						z[b][c].setBomb();
					}
			}
			
		}
		else if (difficulty.equals("intermediate")) {			//if the game is medium, sets 23 random squares to be true for mine/bomb
			for (int a = 0 ; a < 23 ; a++) {
				int b = (int)(Math.random()*12);
				int c = (int)(Math.random()*12);
				if (z[b][c].getBomb() == true) {
					a--;
				} else {
					z[b][c].setBomb();
				}
			}
			

		} else {					//if the game is hard, sets 36 random squares to be true for mine/bomb
			for (int a = 0 ; a < 36 ; a++) {
				int b = (int)(Math.random()*15);
				int c = (int)(Math.random()*15);
					if (z[b][c].getBomb() == true) {
						a--;
					} else {
						z[b][c].setBomb();
					}
			}
			
		}
		return z;
	}
	
	/**
	 * 
	 * Takes in a 2D grid of squares, and places in each square 
	 * (that is not true for bomb) the total number of bombs in 
	 * the eight squares bordering it.
	 * 
	 * @param a : a 2D array of squares
	 * @return the 2D array of squares populated with ints accordingly
	 */
	public Square[][] popNums(Square[][] a) {
		System.out.println(a);
		for (int r = 0 ; r < a.length ; r++) {
			for (int c = 0 ; c < a[r].length ; c++) {
				
					if (a[r][c].getBomb() == true) {
					} else {
						int count = 0;
						for ( int i = r - 1; i <= r + 1; i++ ) {
							if (i < a.length && i >= 0) {
							for ( int p = c - 1; p <= c + 1; p++ ) {
								if (p < a.length && p >= 0) {
									if (a[i][p].getBomb() == true) {
										count ++;
									}
							}
						}
					}
				}
						a[r][c].setNum(count);
			}
		}
	}
		return a;
	}
	
	
	
	// SEPARATOR***************************
	
		
	/**
	 * 
	 * @param cmd
	 * @return
	 */
	public boolean isValid(String cmd) {
		
		String letter = "abcdefghijklmno";
		String icmds = "ufi"; //these are acceptable comands, c - check, f - flag, etc.
		String num = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15"; 
		
		boolean valid = false;
		String tmp = "";
		
		for (int a = 0 ; a < cmd.length() ; a++ ) {
			if (cmd.charAt(a) == ' ' || cmd.charAt(a) == '	') {
				tmp = cmd.substring(a,cmd.length()).trim();
				
			} 
		}
			
			if (tmp.length() <= 3 && tmp.length() > 1 && icmds.indexOf(cmd.charAt(0)) 
					>= 0 && num.indexOf(tmp.substring(1)) >= 0 && letter.indexOf(tmp.charAt(0)) >= 0) {
				int r = letter.indexOf(tmp.charAt(0));
				int c = Integer.parseInt(tmp.substring(1));
				
					if (r <= grid.length && c <= grid[0].length) {
						valid = true;
					}		
		}
		return valid;
	}
	
	/**
	 * Takes in a valid String index and translates it into two numbers that will 
	 * be stored into an Array of ints.
	 * @param coord is the valid input String coordinate from controller
	 * @return an Array of two ints
	 */
	public int[] decode(String coord){
		String tmp = "";
		for (int a = 0 ; a < coord.length() ; a++ ) {
			if (coord.charAt(a) == ' ' || coord.charAt(a) == '	') {
				tmp = coord.substring(a,coord.length()).trim();
			} 
		}
		String letter = "abcdefghijklmno";
			int c = tranCol(tmp);//letter.indexOf(tmp.charAt(0));
			int r = tranRow(tmp);//Integer.parseInt(tmp.substring(1));
		/*
		coord.charAt(0);
		String tmp = "";
		for (int a = 1 ; a < coord.length() ; a++ ) {
			if (coord.charAt(a) == ' ' || coord.charAt(a) == '	') {
				tmp = coord.substring(a,coord.length()).trim();
			} 
		}
				*/
		int[] cod = new int[2];
		//int row = tranRow(tmp);
		//int col = tranCol(tmp);
		cod[0] = c;
		cod[1] = r;
		return cod;
	}
	
	//turns input coordinate letter into a row from coordinate String
	public int tranCol(String bleh){
		String alphabet = "abcdefghijklmno";
		int x = alphabet.indexOf(bleh.charAt(0));
		return x;
	}
	
	//turns input coordinate number into a row from coordinate String
	public int tranRow(String bleh){
		int z = 0;
		String[] nums = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
		for(int i = 0; i < nums.length ; i++) {
			if( nums[i].equals(bleh.substring(1))){
				z = i;
				break;
			}
		}
		return z;
	}
	
	/**
	 * Takes in index coordinates of grid and inspects the given Square on the grid. 
	 * If there is a bomb at that Square, then a lose state is triggered 
	 * revealing all numbers and bombs and returns false.
	 * Otherwise it clears spaces or reveals the number of the Square and checks if 
	 * the game has been won. If the game has been won it returns true; otherwise returns false.
	 * 
	 * @param row is the row index of the Square on grid to Squares to inspect
	 * @param col is the row index of the Square on grid to inspect
	 * @return false if a win or loss is reached; otherwise returns true;
	 */
	public boolean inspect(int row , int col){
		if(!grid[row][col].getFlag()){
			if(grid[row][col].getBomb() == true){
				unflagAll();
				revealAll();
				//lose
				win = false;
				return false;
				
			} else if(grid[row][col].getNum() == 0 && !grid[row][col].getBomb()){
				clearSpace(row,col);
				if(!bombWinState()){
					win = true;
				}
				return bombWinState();
			} else {
				grid[row][col].setReveal(true);
				if(!bombWinState()){
					win = true;
				}
				return bombWinState();
			}
		}
		return true;
	}
	//UPDATE***********	
	/**
	 * Takes in an index of a grid 
	 * and flags it as well as reveals it on grid.
	 * 
	 * @param a is the row index of a Square on grid to flag
	 * @param b is the column index of a Square on grid to flag
	 */
	public boolean flag(int a, int b){
		if(!grid[a][b].getFlag()){
		grid[a][b].setFlag(true);
		grid[a][b].setReveal(true);
		}
		return true;
	}
	
	//UPDATE**********
	/**
	 * Takes in an index grid
	 * and unflags it as well as hides it on the 2d array of Square objects
	 * 
	 * @param a is the row index of a Square on grid to unflag
	 * @param b is the column index of a Square on grid to unflag
	 */
	public boolean unflag(int a, int b){
		grid[a][b].setFlag(false);
		grid[a][b].setReveal(false);
		return true;
	}
		
		
		
	
	
	
	/**
	 * Returns true if all the Squares other than bombs have reveal set to true;
	 * otherwise returns false.
	 * 
	 * @return true if all non bomb Squares on an array list of Squares are revealed; otherwise false.
	 */
	public boolean nonBombRevealed(){
		for(int i = 0; i < grid.length;  i++){
			for(int j = 0; j < grid[0].length; j++){
				if(!grid[i][j].getBomb() && !grid[i][j].getReveal()){
					return false;
				}
			}
		}
		return true;
	}
	//checks if flags and bombs all match to win
	
	/**
	 *If the a game of Minesweeper is won (by all the non bomb spaces being revealed),
	 *then all of the flags not on bombs are removed and reveals all the Squares of a 2d Array of Square objects.
	 * 
	 */
	public boolean bombWinState(){
		if(nonBombRevealed() ){
			badFlagPurge();
			revealAll();
			return false;
		}
		return true;
	}
	
	
	/*
	public boolean flagBombMatched(){
		int b = 0;
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(!grid[i][j].getBomb() && grid[i][j].getFlag() 
						||grid[i][j].getBomb() && !grid[i][j].getFlag()){
					b++;
				}
			}
		}
		if(b != 0 ){
			return true;
		}
		return false;
	}*/
	
	/**
	 * Reveals all Square objects in grid.
	 * 
	 */
	
	public void revealAll(){
		for(int i = 0; i < grid.length;  i++){
			for(int j = 0; j < grid[0].length; j++){
				grid[i][j].setReveal(true);
			}
		}
	}
	
	/**
	 * All Squares with flags that aren't on bombs are removed in grid.
	 * 
	 */
	
	public void badFlagPurge(){
		for(int i = 0; i < grid.length;  i++){
			for(int j = 0; j < grid[0].length; j++){
				if(grid[i][j].getFlag() && !grid[i][j].getBomb()){
					grid[i][j].setFlag(false);
				}
			}
		}
	}
	
	/**
	 *Sets all flags of Square objects in grid to false.
	 * 
	 */
	
	public void unflagAll(){
		for(int i = 0; i < grid.length;  i++){
			for(int j = 0; j < grid[0].length; j++){
				grid[i][j].setFlag(false);
			}
		}
	}
	
	/**
	 * Takes in a coordinate of a 2d Array of Square objects that has a num value of 0
	 * and clears all Squares with num values of 0 around it as long as they
	 * aren't already revealed, aren't bombs, aren't out side of the given Array, 
	 * and aren't flags and recurs that method. If it hits a square with a num value greater 
	 * than 0, then it sets reveal to true but does not recur.
	 * 
	 * @param a is the row index of the space to be cleared on grid
	 * @param b is the column index of the space to be cleared on grid
	 */
	public void clearSpace(int a, int b){
			for( int c = (a - 1) ; c <= a + 1 ; c++){
				if(c < grid.length && c >= 0){
					for(int d = (b - 1); d <= b + 1 ; d++) {
						if(d < grid[0].length && d >= 0){
							if(ifOut(c,d) == false && !grid[c][d].getFlag() 
									&& !grid[c][d].getBomb() && grid[c][d].getNum() == 0 && !grid[c][d].getReveal()) {
								grid[c][d].setReveal(true);
								clearSpace(c,d);
							} else if (grid[c][d].getNum() > 0){
								grid[c][d].setReveal(true);
							}
						}
					}
				}
			}
		
	}
	
	/**
	 * Checks if the given row value of a and column value of b are outside of grid's possible indexes.
	 * 
	 * @param a is the row index to be checked if in bounds or not
	 * @param b is the column index to be checked if in bounds or not
	 * @return true if both coordinate values are within grid; otherwise returns false.
	 */
	public boolean ifOut(int a, int b){
		if(a < 0 || a >= grid.length || b < 0 || b >= grid[0].length){
			return true;
		}
		return false;
	}
	
	
	/**
	 * Takes in the gameState and a 2D Array of Square objects and prints out all of the revealed Squares.
	 * 
	 * @param gameState is false if the player has not yet reached a win or loss in one game; otherwise true
	 * @param grid is the 2d Array of Square objects
	 */
	
	
	
	public void printGrid(boolean gameState) {
        for (int a = 0; a < grid.length; a++) {
            String alphabet = "abcdefghijklmno";
            System.out.print("   ");
            System.out.print(alphabet.charAt(a));
        }
        System.out.println();
        // first row separator
        System.out.print("  ");
        for (int b = 0; b < grid.length - 1; b++) {
            System.out.print("+---");
        }
        System.out.println("+---+");
        // row # | and | following each value
        // prints revealed flag if available else just prints number
        // then prints out a row separator
        for (int c = 0; c < grid.length; c++) {
            if(c<=8) {
                System.out.print(c + 1 + " |");
            } else {
                System.out.print(c + 1 + "|");
            }
            for (int j = 0; j < grid[0].length; j++) {
            	if(grid[j][c].getReveal()){
					if(grid[j][c].getFlag()){
						System.out.print(" F |");
					} else if(!grid[j][c].getBomb()){
						System.out.print(" " + grid[j][c].getNum() + " |");
					} else if(gameState == false && grid[j][c].getBomb()) {
						System.out.print(" * |");
					}
				} else {
                    if (grid[j][c].getBomb() == true) {
                        System.out.print(" * |");
                    } else {
                        System.out.print("   |");
                    }
                }
                // }
            }
            System.out.println("");
            System.out.print("  ");
            for (int b = 0; b < grid.length - 1; b++) {
                System.out.print("+---");
            }
            System.out.println("+---+");
        }
        
        

        // win or lose picture?
    }
	
	
	
	public boolean getWin(){
		return win;
	}
	/*public void printGrid(boolean gameState) {
		for(int a = 0; a < grid.length ; a++){
			String alphabet = "abcdefghijklmno";
			System.out.print("    ");
			System.out.print("  " + alphabet.charAt(a) + "  ");
		}
		System.out.println();
		for(int b = 0; b < grid.length - 1; b++){
			System.out.print("+---");
		}
		System.out.println("+---+");
		for ( int c = 0 ; c < grid.length ; c++ ) {
	
			System.out.print(c + 1 + "| ");
			for ( int j = 0 ; j < grid[0].length ; j++ ) {
				if(grid[c][j].getReveal()){
					if(grid[c][j].getFlag()){
						System.out.println("F");
					} else if(!grid[c][j].getBomb()){
						System.out.print(grid[c][j].getNum() + " |");
					} else if(gameState == false && grid[c][j].getBomb()) {
						System.out.println("*");
					}
				}
				System.out.print(grid[c][j].getNum() + " |");
				
			}
			System.out.println("|");
			for(int b = 0; b < grid.length - 1; b++){
				System.out.print("+---");
			}
			System.out.println("+---+");
		}
	}*/

}
