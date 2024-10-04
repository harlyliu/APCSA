/**
 *	Plays the game of MasterMind.
 *	The game involves two players, a board, and pegs of six different colors.
 *  One person is designated the codemaker and the other the codebreaker. 
 *  The code-maker secretly chooses a pattern of four pegs in which
 *  some or all can be duplicate colors, then places the pegs into an ordered
 *  row that is hidden from view. The code-breaker has up to ten guesses to
 *  determine the code and win; otherwise, the code-maker wins. 
 *	@author Harly Liu
 *	@since 10/4/2024
 */

public class MasterMind {

	private boolean reveal;			// true = reveal the master combination
	private PegArray[] guesses;		// the array of guessed peg arrays
	private PegArray master;		// the master (key) peg array
	
	// Constants
	private final int PEGS_IN_CODE = 4;		// Number of pegs in code
	private final int MAX_GUESSES = 10;		// Max number of guesses
	private final int PEG_LETTERS = 6;		// Number of different letters on pegs
											// 6 = A through F
	
	/**Just the main method
	 * @param String[] args
	 * @return none
	 */
	public static void main(String[] args){
		MasterMind mm = new MasterMind();
		mm.run();
	}
	
	/**Runs the game and calls all other methods. 
	 * @param none
	 * @return none
	 */
	public void run(){
		printIntroduction();
		generateArray();
		Prompt.getString("Hit the Enter key to start the game ");
		guesses = new PegArray[MAX_GUESSES];
		PegArray blankRow = new PegArray(PEGS_IN_CODE);
		for (int i = 0; i < PEGS_IN_CODE; i++) blankRow.getPeg(i).setLetter(' ');
		for (int i = 0; i < MAX_GUESSES; i++){
			guesses[i] = blankRow;
		}
		boolean found = false;
		for (int i = 0; i < MAX_GUESSES; i++){
			System.out.println("Guess " + (i+1));
			PegArray currGuess = getInput();
			guesses[i] = currGuess;
			if (currGuess.getExactMatches(master) == PEGS_IN_CODE){
				reveal = true;
				printBoard();
				System.out.println("Nice work! You found the master code in "
					+ (i+1) + " guesses. ");
				i = MAX_GUESSES;
			}
			else if (i == MAX_GUESSES-1){
				reveal = true;
				printBoard();
				System.out.println("Oops. You were unable to find the"
					+" solution in 10 guesses. ");
			}
			else{
				printBoard();
			}
		}
		
	}
	
	/**Creates the master key code using random
	 * @param none
	 * @return none
	 */
	public void generateArray(){
		master = new PegArray(PEGS_IN_CODE);
		for (int i = 0; i < PEGS_IN_CODE; i++){
			char nextPeg = (char)((int)(Math.random()*6) + 65);
 			master.getPeg(i).setLetter(nextPeg);
		}
	}
	
	/**Gets the input of correct size 
	 * @param none
	 * @return PegArray the user enters. 
	 */
	public PegArray getInput(){
		String str = "";
		do{
			str = Prompt.getString("Enter the code using (A,B,C,D,E,F)."
				+" For example, ABCD or abcd from left-to-right");
		}while(!isValid(str));
		str = str.toUpperCase();
		PegArray newArray = new PegArray(PEGS_IN_CODE);
		for (int i = 0; i < PEGS_IN_CODE; i++){
			newArray.getPeg(i).setLetter(str.charAt(i));
		}
		return newArray;
	}
	
	/**Checks if the user input is valid
	 * @param String that the user entered
	 * @return true if the string is valid, false if it isn't
	 */
	public boolean isValid(String str){
		if (str.length() != PEGS_IN_CODE){
			System.out.println("ERROR: Bad input, try again. ");
			return false;
		}
		for (int i = 0; i < PEGS_IN_CODE; i++){
			if (!(str.charAt(i) >= (int)'a' && str.charAt(i)<= (int)'f' || 
				str.charAt(i) >= (int)'A' && str.charAt(i) <= (int)'F')){
				System.out.println("ERROR: Bad input, try again. ");
				return false;
			}
		}
		return true;
	}
	
	/**
	 *	Print the introduction screen
	 * @param none
	 * @return none
	 */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| ___  ___             _              ___  ___ _             _                       |");
		System.out.println("| |  \\/  |            | |             |  \\/  |(_)           | |                      |");
		System.out.println("| | .  . |  __ _  ___ | |_  ___  _ __ | .  . | _  _ __    __| |                      |");
		System.out.println("| | |\\/| | / _` |/ __|| __|/ _ \\| '__|| |\\/| || || '_ \\  / _` |                      |");
		System.out.println("| | |  | || (_| |\\__ \\| |_|  __/| |   | |  | || || | | || (_| |                      |");
		System.out.println("| \\_|  |_/ \\__,_||___/ \\__|\\___||_|   \\_|  |_/|_||_| |_| \\__,_|                      |");
		System.out.println("|                                                                                    |");
		System.out.println("| WELCOME TO MONTA VISTA MASTERMIND!                                                 |");
		System.out.println("|                                                                                    |");
		System.out.println("| The game of MasterMind is played on a four-peg gameboard, and six peg letters can  |");
		System.out.println("| be used.  First, the computer will choose a random combination of four pegs, using |");
		System.out.println("| some of the six letters (A, B, C, D, E, and F).  Repeats are allowed, so there are |");
		System.out.println("| 6 * 6 * 6 * 6 = 1296 possible combinations.  This \"master code\" is then hidden     |");
		System.out.println("| from the player, and the player starts making guesses at the master code.  The     |");
		System.out.println("| player has 10 turns to guess the code.  Each time the player makes a guess for     |");
		System.out.println("| the 4-peg code, the number of exact matches and partial matches are then reported  |");
		System.out.println("| back to the user. If the player finds the exact code, the game ends with a win.    |");
		System.out.println("| If the player does not find the master code after 10 guesses, the game ends with   |");
		System.out.println("| a loss.                                                                            |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME MASTERMIND!                                                        |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n");
	}
	
	/**
	 *	Print the peg board to the screen
	 * @param none
	 * @return none
	 */
	public void printBoard() {
		// Print header
		System.out.print("+--------+");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------+");
		System.out.print("| MASTER |");
		for (int a = 0; a < PEGS_IN_CODE; a++)
			if (reveal)
				System.out.printf("   %c   |", master.getPeg(a).getLetter());
			else
				System.out.print("  ***  |");
		System.out.println(" Exact Partial |");
		System.out.print("|        +");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("               |");
		// Print Guesses
		System.out.print("| GUESS  +");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------|");
		for (int g = 0; g < MAX_GUESSES - 1; g++) {
			printGuess(g);
			System.out.println("|        +-------+-------+-------+-------+---------------|");
		}
		printGuess(MAX_GUESSES - 1);
		// print bottom
		System.out.print("+--------+");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------+");
	}
	
	/**
	 *	Print one guess line to screen
	 *	@param t	the guess turn
	 * @return none
	 */
	public void printGuess(int t) {
		System.out.printf("|   %2d   |", (t + 1));
		// If peg letter in the A to F range
		char c = guesses[t].getPeg(0).getLetter();
		if (c >= 'A' && c <= 'F')
			for (int p = 0; p < PEGS_IN_CODE; p++)
				System.out.print("   " + guesses[t].getPeg(p).getLetter() + "   |");
		// If peg letters are not A to F range
		else
			for (int p = 0; p < PEGS_IN_CODE; p++)
				System.out.print("       |");
				
		System.out.printf("   %d      %d    |\n",
							guesses[t].getExactMatches(master), 
							guesses[t].getPartialMatches(master));
	}

}
