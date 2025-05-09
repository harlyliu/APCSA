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
			board[height][i] = '-';
		}
		for (int i = 0; i < height+2; i++){
			board[i][0] = '|';
			board[width+1][0] = '|';
		}
		board[0][0] = '+';
		board[height+1][0] = '+';
		board[0][widith+1] = '+';
		board[height+1][width+1] = '+';
	}
	
	/**
	 *	Print the board to the screen.
	 */
	public void printBoard(Snake snake, Coordinate target) {
		for (char[] row: board){
			for (char c: row){
				System.out.print(c);
			}
		}
	}
	
	/* Helper methods go here	*/
	
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
		Snake snake = new Snake(3, 3);
		// Place the target
		Coordinate target = new Coordinate(1, 7);
		// Print the board
		sb.printBoard(snake, target);
	}
}
