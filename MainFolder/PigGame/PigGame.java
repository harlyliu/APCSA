/**
 *	The game of Pig.
 *	Pig is a dice game first described by John Scarne in 1945. The rules are simple. Two players try to reach 100 points. In
 *  each turn, the player either rolls the die or holds and scores the sum of the turn. During a turn, the player has two choices:
 *  roll - If the player rolls a
 *  2 through 6, the number is added to the turn’s total
 *  1, the player loses their turn and the opponent gets to roll
 *  hold - The player adds the turn’s total to their score and the opponent gets to roll
 *  The user will play against the computer. The computer will always hold when reaching at least 20 points. 
 *
 *	@author Harly LIu
 *	@since	9/13/2024
 */
public class PigGame {
	
	/**	Print the introduction to the game */
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
	
	public char customizedGetChar(String str){
		char inp;
		do{
			inp = Prompt.getChar(str);
		}while(inp != 'h' && inp != 'r');
		return inp;
	}
	
	public void run(){
		printIntroduction();
		int playerpoints = 0;
		int computerpoints = 0;
		int currpoints = 0;
		Dice dice = new Dice(6);
		while (playerpoints < 100 && computerpoints < 100){
			currpoints = 0;
			while(true){
				System.out.println("Your turn");
				char inp = customizedGetChar("(r)oll or (h)old ");
				if (inp == 'h'){
					playerpoints += currpoints;
					break;
				}
				else{
					int val = dice.roll();
					dice.printDice();
					if (val == 1){
						currpoints = 0;
						System.out.println("You rolled a 1. Don't gamble");
						break;
					}
					currpoints += val;
					System.out.println("Your turn score: " + currpoints);
					System.out.println("Your total score: " + playerpoints);
				}
			}
			currpoints = 0;
			System.out.println("Your total score: " + playerpoints);
			System.out.println("**** COMPUTER'S Turn *** ");
			while (true){
				Prompt.getString("Press enter for computer's turn");
				if (currpoints >= 20){
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
	}
	
	public static void main(String args[]){
		PigGame pg = new PigGame();
		pg.run();
	}
	
}
