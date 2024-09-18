/**
 *	The game of Pig.
 *	Pig is a dice game first described by John Scarne in 1945. The rules are simple. Two players try to reach 100 points. In
 *  each turn, the player either rolls the die or holds and scores the sum of the turn. During a turn, the player has two choices:
 *  roll - If the player rolls a
 *  2 through 6, the number is added to the turnâ€™s total
 *  1, the player loses their turn and the opponent gets to roll
 *  hold - The player adds the turnâ€™s total to their score and the opponent gets to roll
 *  The user will play against the computer. The computer will always hold when reaching at least 20 points. 
 *
 *	@author Harly LIu
 *	@since	9/13/2024
 */
public class PigGame {
	
	/**	Print the introduction to the game 
	 * @param None
	 * @return None
	 * */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("______ _         _____");
		System.out.println("| ___ (_)       |  __ \\");
		System.out.println("| |_/ /_  __ _  | |  \\/ __ _ _ __ ___   ___");
		System.out.println("|  __/| |/ _` | | | __ / _` | '_ ` _ \\ / _ \\");
		System.out.println("| |   | | (_| | | |_\\ \\ (_| | | | | | |  __/");
		System.out.println("\\_|   |_|\\__, |  \\____/\\__,_|_| |_| |_|\\___|");
		System.out.println("          __/ |");
		System.out.println("         |___/");
		System.out.println("\nThe Pig Game is human vs computer. Each takes a"
							+ " turn rolling a die and the first to score");
		System.out.println("100 points wins. A player can either ROLL or "
							+ "HOLD. A turn works this way:");
		System.out.println("\n\tROLL:\t2 through 6: add points to turn total, "
							+ "player's turn continues");
		System.out.println("\t\t1: player loses turn");
		System.out.println("\tHOLD:\tturn total is added to player's score, "
							+ "turn goes to other player");
		System.out.println("\n");

	}
	
	/**	Takes in user input for either roll or hold. User keeps on 
	 * entering until h or r is entered. 
	 * @param The prompt string
	 * @return return the char, either h or r
	 * */
	public char getRollOrHold(String str){
		char inp;
		do{
			inp = Prompt.getChar(str);
		}while(inp != 'h' && inp != 'r');
		return inp;
	}
	
	/**	Takes in user input for either roll or hold. User keeps on 
	 * entering until p or s is entered. 
	 * @param The prompt string
	 * @return return the char, either p or s
	 * */
	public char getPlayOrStatistics(String str){
		char inp;
		do{
			inp = Prompt.getChar(str);
		}while(inp != 'p' && inp != 's');
		return inp;
	}
	
	/**	Takes in input from user for amount of times to run it.
	 * Run the computer's turn that amount of times. for each time,
	 * record the final score and then print. 
	 * @param None
	 * @return None
	 * */
	public void runStats(){
		int amt = 1;
		do{
			amt = Prompt.getInt("Number of turns (1000 - 1000000) ");
		}while(amt < 1000 || amt > 1000000);
		int amtOf0 = 0;
		int amtOf20 = 0;
		int amtOf21 = 0;
		int amtOf22 = 0;
		int amtOf23 = 0;
		int amtOf24 = 0;
		int amtOf25 = 0;
		Dice dice = new Dice(6);
		for (int i = 0; i < amt; i++){
			int currpoints = 0;
			while (true){
				if (currpoints >= 20){
					break;
				}
				int val = dice.roll();
				if (val == 1){
					currpoints = 0;
					break;
				}
				currpoints += val;
			}
			if (currpoints == 0){
				amtOf0++;
			}
			else if (currpoints == 20){
				amtOf20++;
			}
			else if (currpoints == 21){
				amtOf21++;
			}
			else if (currpoints == 22){
				amtOf22++;
			}
			else if (currpoints == 23){
				amtOf23++;
			}
			else if (currpoints == 24){
				amtOf24++;
			}
			else{
				amtOf25++;
			}
		}
		System.out.println("Estimated");
		System.out.println("Score  Probability");
		System.out.println("0      " + (double)amtOf0/amt);
		System.out.println("20     " + (double)amtOf20/amt);
		System.out.println("21     " + (double)amtOf21/amt);
		System.out.println("22     " + (double)amtOf22/amt);
		System.out.println("23     " + (double)amtOf23/amt);
		System.out.println("24     " + (double)amtOf24/amt);
		System.out.println("25     " + (double)amtOf25/amt);
	}
	
	/**	THis runs the main game. It uses a while loop to run through 
	 * player and user turns until one sides gets 100 points. For each
	 * time, take input from the user and if it isn't 1, add the value
	 * to the total score. For the computer, it just uses random and stops
	 * at 20. 
	 * @param none
	 * @return none
	 * */
	public void runGame(){
		System.out.println("----------------------");
		int playerpoints = 0;
		int computerpoints = 0;
		int currpoints = 0;
		Dice dice = new Dice(6);
		while (playerpoints < 100 && computerpoints < 100){
			currpoints = 0;
            System.out.println("**** USER Turn ***");
			System.out.println("Your turn score: " + currpoints);
			System.out.println("Your total score: " + playerpoints);
			while(true){
				char inp = getRollOrHold("(r)oll or (h)old ");
				if (inp == 'h'){
					playerpoints += currpoints;
					break;
				}
				else{
					System.out.println("You ROLL");
					int val = dice.roll();
					dice.printDice();
					if (val == 1){
						currpoints = 0;
						break;
					}
					currpoints += val;
					System.out.println("Your turn score: " + currpoints);
					System.out.println("Your total score: " + playerpoints);
				}
			}
			if (playerpoints >= 100) break;
			currpoints = 0;
			System.out.println("Your total score: " + playerpoints);
			System.out.println("**** COMPUTER'S Turn *** ");
			while (true){
				Prompt.getString("Press enter for computer's turn");
				if (currpoints >= 20 || currpoints + computerpoints >= 100){
					System.out.println("Computer will hold");
					computerpoints += currpoints;
					break;
				}
				System.out.println("Computer will roll");
				int val = dice.roll();
				dice.printDice();
				if (val == 1){
					currpoints = 0;
					break;
				}
				currpoints += val;
				System.out.println("Computer turn score: " + currpoints);
				System.out.println("Computer total score: " + computerpoints);
				if (computerpoints + currpoints >= 100){
					computerpoints += currpoints;
					break;
				}
			}
			System.out.println("Computer total score: " + computerpoints);
		}
		System.out.println("Your score:" + playerpoints);
		System.out.println("Computer score: " + computerpoints);
		if (playerpoints >= 100){
			System.out.println("Congratulations!!! YOU WON!!!! ");
		}
		else{
			System.out.println("The computer won");
		}
		System.out.println("Thanks for playing the Pig Game!!!");
	}
	
	/**	Main method. Prints introduction and asks if the user wants to
	 * play or see statistics
	 * @param none
	 * @return none
	 * */
	public static void main(String args[]){
		PigGame pg = new PigGame();
		pg.printIntroduction();
		char playOrStats = pg.getPlayOrStatistics("Play game or Statistics (p or s) ");
		if (playOrStats == 'p'){
			pg.runGame();
		}
		else{
			pg.runStats();
		}
	}
	
}
