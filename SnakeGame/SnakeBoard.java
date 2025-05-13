/**
 *	<Describe the SnakeBoard here>
 *
 *	@author	
 *	@since	
 */
public class SnakeBoard {
	
	/*	fields	*/
	private char[][] board;			// The 2D array to hold the board
	
	/*	Constructor	*/
	public SnakeBoard(int height, int width) {
		board = new char[height+2][width+2];
		for (int r = 0; r < height + 2; r++){
			for (int c = 0; c < width + 2; c++){
				board[r][c] = ' ';
			}
		}
		for (int i = 0; i < width+2; i++){
			board[0][i] = '-';
			board[height+1][i] = '-';
		}
		for (int i = 0; i < height+2; i++){
			board[i][0] = '|';
			board[i][width+1] = '|';
		}
		board[0][0] = '+';
		board[height+1][0] = '+';
		board[0][width+1] = '+';
		board[height+1][width+1] = '+';
	}
	
	/**
	 *	Print the board to the screen.
	 */
	public void printBoard(Snake snake, Coordinate target) {
		for (int r = 1; r < board.length-1; r++){
			for (int c = 1; c < board[0].length-1; c++){
				board[r][c] = ' ';
			}
		}
		for (int i = 0; i < snake.size(); i++){
			Coordinate curr = snake.get(i).getValue();
			board[curr.getRow()][curr.getCol()] = '*';
		}
		board[snake.get(0).getValue().getRow()][snake.get(0).getValue().getCol()] = '@';
		board[target.getRow()][target.getCol()] = '+';
		for (char[] row: board){
			for (char c: row){
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	/* Helper methods go here	*/
	/**
	 *	Resets the board to all 0's so that when a new snake is loaded
	 * 	no squares from the old snake are left.
	 */
	public void clearBoard(){
		for (int r = 0; r < board.length; r++){
			for (int c = 0; c < board[0].length; c++){
				board[r][c] = ' ';
			}
		}
	}
	
	
	/*	Accessor methods	*/
	public int getHeight(){
		return board.length;
	}
	
	public int getWidth(){
		return board[0].length;
	}
	
	public char[][] getBoard(){
		return board;
	}
	
	/********************************************************/
	/********************* For Testing **********************/
	/********************************************************/
	
	public static void main(String[] args) {
		// Create the board
		int height = 10, width = 15;
		SnakeBoard sb = new SnakeBoard(height, width);
		// Place the snake
		Snake snake = new Snake(new Coordinate(3, 3));
		// Place the target
		Coordinate target = new Coordinate(1, 7);
		// Print the board
		System.out.println("hi");
		sb.printBoard(snake, target);
	}
}
