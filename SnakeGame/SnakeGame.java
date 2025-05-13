/**
 *	Snake Game - <Description goes here>
 *	
 *	@author	
 *	@since	
 */
 
import java.util.Scanner;
import java.io.PrintWriter;
 
public class SnakeGame {
	
	private Snake snake;		// the snake in the game
	private SnakeBoard board;	// the game board
	private Coordinate target;	// the target for the snake
	private int score;			// the score of the game
	

	/*	Constructor	*/
	public SnakeGame() {
		int rowCoord = (int)(Math.random()*16)+1;
		int colCoord = (int)(Math.random()*21)+1;
		snake = new Snake(new Coordinate(rowCoord,colCoord));
		board = new SnakeBoard(20,25);
		score = 0;
		getTarget();
	}
	
	/**
	 *	generates a new target that isn't on top of a current snake
	 *  sets field variable to new target
	 */
	public void getTarget(){
		int rowCoord = (int)(Math.random()*20)+1;
		int colCoord = (int)(Math.random()*25)+1;
		target = new Coordinate(rowCoord, colCoord);
		while(snake.contains(target)){
			rowCoord = (int)(Math.random()*20)+1;
			colCoord = (int)(Math.random()*25)+1;
			target = new Coordinate(rowCoord, colCoord);
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
		String inp;
		do{
			board.printBoard(snake, target);
			System.out.print("Score: " + score + 
					" (w - North, s - South, d - East, a - West, h - Help)");
			inp = Prompt.getString("");
			Coordinate currHead = snake.get(0).getValue();
			if ("wasd".indexOf(inp) >= 0 && inp.length() == 1){
				Coordinate next;
				if (inp.equals("w")){
					next = new Coordinate(currHead.getRow()-1, currHead.getCol());
				}
				else if (inp.equals("a")){
					next = new Coordinate(currHead.getRow(), currHead.getCol()-1);
				}
				else if (inp.equals("s")){
					next = new Coordinate(currHead.getRow()+1, currHead.getCol());
				}
				else{
					next = new Coordinate(currHead.getRow(), currHead.getCol()+1);
				}
				if (next.onGrid(20,25)&& !snake.contains(next)){
					snake.add(0, next);
					if (snake.get(0).getValue().equals(target)){
						getTarget();
						score++;
					
					}
					else{
						snake.remove(snake.size()-1);
					}
				}
				else{
					System.out.println("Sorry, you crashed.");
					inp = "q";
				}
			}
			else if (inp.equals("h")){
				helpMenu();
			}
			else if (inp.equals("f")){
				System.out.println("Game saved to savedGame.txt");
				saveToFile();
			}
			else if (inp.equals("r")){
				restoreGame();
			}
			else if (inp.equals("q")){
				inp = Prompt.getString("Do you really want to quit? (y or n)");
				if (inp.equals("y")) inp = "q";
			}
		}while(!inp.equals("q"));
		System.out.println("Thank you for playing");
	}
	
	/**
	 *	opens savedGame.txt to read the file and set field variables 
	 * 	accordingly
	 */
	public void restoreGame(){
		Scanner fileIn = FileUtils.openToRead("savedGame.txt");
		fileIn.next();
		score = fileIn.nextInt();
		System.out.println("score is read as : " + score);
		fileIn.next();
		int r = fileIn.nextInt();
		int c = fileIn.nextInt();
		target = new Coordinate(r, c);
		fileIn.next();
		int len = fileIn.nextInt();
		snake.clear();
		for (int i = 0; i < len; i++){
			r = fileIn.nextInt();
			c = fileIn.nextInt();
			snake.add(new Coordinate(r, c));
		}
		
	}
	
	/**
	 *	uses printwriter to write data of current game into savedGame.txt
	 */
	public void saveToFile(){
		PrintWriter fileWrite = FileUtils.openToWrite("savedGame.txt");
		fileWrite.print("Score ");
		fileWrite.println(score);
		fileWrite.println("Target");
		fileWrite.println(target.getRow() + " " + target.getCol());
		fileWrite.print("Snake ");
		fileWrite.println(snake.size());
		for (int i = 0; i < snake.size(); i++){
			fileWrite.println(snake.get(i).getValue().getRow() + " " + 
				snake.get(i).getValue().getCol());
		}	
		fileWrite.close();
		
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
