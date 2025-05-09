/**
 *	Snake Game - <Description goes here>
 *	
 *	@author	
 *	@since	
 */
public class SnakeGame {
	
	private Snake snake;		// the snake in the game
	private SnakeBoard board;	// the game board
	private Coordinate target;	// the target for the snake
	private int score;			// the score of the game
	

	/*	Constructor	*/
	public SnakeGame() {
		int xCoord = (int)(Math.random()*16)+1;
		int yCoord = (int)(Math.random()*21)+1;
		snake = new Snake(new Coordinate(xCoord,yCoord));
		board = new SnakeBoard(20,25);
		getTarget();
	}
	
	public void getTarget(){
		int xCoord = (int)(Math.random()*20)+1;
		int yCoord = (int)(Math.random()*25)+1;
		target = new Coordinate(xCoord, yCoord);
		while(snake.contains(target)){
			xCoord = (int)(Math.random()*20)+1;
			yCoord = (int)(Math.random()*25)+1;
			target = new Coordinate(xCoord, yCoord);
		}
		
	}
	/*	Main method	*/
	public static void main(String[] args) {
		SnakeGame sg = new SnakeGame();
		sg.runGame();
	}
	
	public void runGame(){
		printIntroduction();
		board = new SnakeBoard(20,25);
		helpMenu();
		String inp = Prompt.getString("");
		while(!inp.equals("q")){
			Coordinate currHead = snake.get(0);
			if (inp.equals("w")){
				snake.add(0,new Coordinate(currHead.getRow()-1, currHead.getCol()));
				snake.remove(snake.size()-1);
			}
			else if (inp.equals("a")){
				snake.add(0,new Coordinate(currHead.getRow(), currHead.getCol()-1));
				snake.remove(snake.size()-1);
			}
			else if (inp.equals("s")){
				snake.add(0,new Coordinate(currHead.getRow()+1, currHead.getCol()));
				snake.remove(snake.size()-1);
			}
			else if (inp.equals("d")){
				snake.add(0,new Coordinate(currHead.getRow(), currHead.getCol()+1));
				snake.remove(snake.size()-1);
			}
		}
	}
	
	/**	Print the game introduction	*/
	public void printIntroduction() {
		System.out.println("  _________              __            ________");
		System.out.println(" /   _____/ ____ _____  |  | __ ____  /  _____/_____    _____   ____");
		System.out.println(" \\_____  \\ /    \\\\__  \\ |  |/ // __ \\/   \\  ___\\__  \\  /     \\_/ __ \\");
		System.out.println(" /        \\   |  \\/ __ \\|    <\\  ___/\\    \\_\\  \\/ __ \\|  Y Y  \\  ___/");
		System.out.println("/_______  /___|  (____  /__|_ \\\\___  >\\______  (____  /__|_|  /\\___  >");
		System.out.println("        \\/     \\/     \\/     \\/    \\/        \\/     \\/      \\/     \\/");
		System.out.println("\nWelcome to SnakeGame!");
		System.out.println("\nA snake @****** moves around a board " +
							"eating targets \"+\".");
		System.out.println("Each time the snake eats the target it grows " +
							"another * longer.");
		System.out.println("The objective is to grow the longest it can " +
							"without moving into");
		System.out.println("itself or the wall.");
		System.out.println("\n");
	}
	
	/**	Print help menu	*/
	public void helpMenu() {
		System.out.println("\nCommands:\n" +
							"  w - move north\n" +
							"  s - move south\n" +
							"  d - move east\n" +
							"  a - move west\n" +
							"  h - help\n" +
							"  f - save game to file\n" +
							"  r - restore game from file\n" +
							"  q - quit");
		Prompt.getString("Press enter to continue");
	}
	
}
