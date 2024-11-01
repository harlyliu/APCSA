/**
 *  This program is the famous dice game yahtzee. Throw dice to and determine
 * what to keep. What you keep decides your score
 *
 *  @author Harly Liu
 *  @since 10/31/24
 */
 
public class Yahtzee{
	final int TOTAL_TURNS = 13; //total amount of turns
	YahtzeePlayer player1;//first player object
	YahtzeePlayer player2;//second player object
	DiceGroup diceGroup1;//dice group 1 object
	DiceGroup diceGroup2;//dice group 2 object
	
	public Yahtzee(){}
	
	public static void main(String[] args){
		Yahtzee yahtzee = new Yahtzee();
		yahtzee.playGame();
	}
	
	/**Runs the majority of the game. Facilitates deciding who goes first
	 * iterates through turns, and decides winner
	 * @param none
	 * @return none
	 */
	public void playGame(){
		printHeader();
		player1 = new YahtzeePlayer();
		player2 = new YahtzeePlayer();
		String player1Name = Prompt.getString("Player 1, please enter your first name ");
		String player2Name = Prompt.getString("Player 2, please enter your first name ");
		player1.setName(player1Name);
		player2.setName(player2Name);
		diceGroup1 = new DiceGroup();
		diceGroup2 = new DiceGroup();
		int first = determineOrder();
		if (first == 1){
			System.out.println(player1.getName() + ", since your sum was higher, you'll roll first.");
		}
		else{
			System.out.println(player2.getName() + ", since your sum was higher, you'll roll first.");
		}
		player1.getScoreCard().printCardHeader();
		player1.getScoreCard().printPlayerScore(player1);
		player2.getScoreCard().printPlayerScore(player2);
		for (int i = 0; i < TOTAL_TURNS; i++){
			System.out.println("Round " + (i+1) + " of 13");
			if (first == 1){
				runPlayerTurn(1);
				player1.getScoreCard().printCardHeader();
				player1.getScoreCard().printPlayerScore(player1);
				player2.getScoreCard().printPlayerScore(player2);
				runPlayerTurn(2);
			}
			else{
				runPlayerTurn(2);
				player1.getScoreCard().printCardHeader();
				player1.getScoreCard().printPlayerScore(player1);
				player2.getScoreCard().printPlayerScore(player2);
				runPlayerTurn(1);
			}
			player1.getScoreCard().printCardHeader();
			player1.getScoreCard().printPlayerScore(player1);
			player2.getScoreCard().printPlayerScore(player2);
		}
		System.out.printf("%10sscore total = " + player1.getScoreCard().getSum(), player1.getName());
		System.out.printf("%10sscore total = " + player2.getScoreCard().getSum(), player2.getName());
		if (player1.getScoreCard().getSum() > player2.getScoreCard().getSum()){
			System.out.println("Congratulations " + player1.getName() + ", you won");
		}
		else{
			System.out.println("Congratulations " + player2.getName() + ", you won");
		}
	}
	
	/**Runs each players turn. Asks them to roll, ask what to keep, 
	 * let them choose what category
	 * @param int player, 1 for player 1, 2 for player 2
	 * @return none
	 */
	public void runPlayerTurn(int player){
		String inp = "";
		if (player == 1){
			
			Prompt.getString(player1.getName() + " , it's your turn to play. Please hit enter to roll the dice");
			for (int i = 0; i < 3; i++){
				diceGroup1.rollDice(inp);
				diceGroup1.printDice();
				if (i != 2)
					inp = Prompt.getString("Which di(c)e would you like to keep? "
					+"Enter the values you'd like to 'hold' without"
					+ "spaces. For examples, if you'd like to 'hold' die 1, "
					+ "2, and 5, enter 125 (enter -1 if you'd like to end the turn)"); 
				if (inp.equals("-1"))i = 4;
			}
			player1.getScoreCard().printCardHeader();
			player1.getScoreCard().printPlayerScore(player1);
			player2.getScoreCard().printPlayerScore(player2);
			System.out.println("                  1    2    3    4    5 "
				+"  6    7    8    9   10   11   12   13");
			boolean works = false;
			do{
				int val = Prompt.getInt(player1.getName()+ ", now you need to "
					+"make a choice. Pick a valid integer from the list above (1 - 13)");
				works = player1.getScoreCard().changeScore(val, diceGroup1);
			}while(!works);
		}
		else{
			Prompt.getString(player2.getName() + " , it's your turn to play. Please hit enter to roll the dice");
			for (int i = 0; i < 3; i++){
				diceGroup2.rollDice(inp);
				diceGroup2.printDice();
				if (i != 2)
					inp = Prompt.getString("Which di(c)e would you like to keep? "
					+ "Enter the values you'd like to 'hold' without"
					+ "spaces. For examples, if you'd like to 'hold' die 1, "
					+ "2, and 5, enter 125 (enter -1 if you'd like to end the turn)"); 
				if (inp.equals("-1"))i = 4;
			}
			player1.getScoreCard().printCardHeader();
			player1.getScoreCard().printPlayerScore(player1);
			player2.getScoreCard().printPlayerScore(player2);
						System.out.println("                  1    2    3    4    5 "
				+"  6    7    8    9   10   11   12   13");
			boolean works = false;
			do{
				int val = Prompt.getInt(player2.getName()+ ", now you need to "
					+"make a choice. Pick a valid integer from the list above (1 - 13)");
				works = player2.getScoreCard().changeScore(val, diceGroup1);
			}while(!works);
		}
		
	}
	
	/**Determines who to go first by having them roll dice and tallying up score
	 * @param none
	 * @return int, 1 for player 1 first 2 for player 2 first
	 */
	public int determineOrder(){
		int val1 = 0;
		int val2 = 0;
		do{
			Prompt.getString("Let's see who will go first. " + player1.getName() + 
				", please hit enter to roll the dice");
			diceGroup1.rollDice();
			diceGroup1.printDice();
			val1 = diceGroup1.getTotal();
			Prompt.getString(player2.getName() + 
				", it's your turn. Please hit enter to roll the dice");
			diceGroup2.rollDice();
			diceGroup2.printDice();
			val2 = diceGroup2.getTotal();
		}while(val1 == val2);
		System.out.println(player1.getName() + ", you rolled a sum of " + val1 + 
			", and " + player2.getName() + ", you rolled a sum of " + val2 + "."); 
		if (val1 > val2) return 1;
		return 2;
	}
	
	/**Prints intro
	 * @param none
	 * @return none
	 */
	public void printHeader() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| WELCOME TO MONTA VISTA YAHTZEE!                                                    |");
		System.out.println("|                                                                                    |");
		System.out.println("| There are 13 rounds in a game of Yahtzee. In each turn, a player can roll his/her  |");
		System.out.println("| dice up to 3 times in order to get the desired combination. On the first roll, the |");
		System.out.println("| player rolls all five of the dice at once. On the second and third rolls, the      |");
		System.out.println("| player can roll any number of dice he/she wants to, including none or all of them, |");
		System.out.println("| trying to get a good combination.                                                  |");
		System.out.println("| The player can choose whether he/she wants to roll once, twice or three times in   |");
		System.out.println("| each turn. After the three rolls in a turn, the player must put his/her score down |");
		System.out.println("| on the scorecard, under any one of the thirteen categories. The score that the     |");
		System.out.println("| player finally gets for that turn depends on the category/box that he/she chooses  |");
		System.out.println("| and the combination that he/she got by rolling the dice. But once a box is chosen  |");
		System.out.println("| on the score card, it can't be chosen again.                                       |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME YAHTZEE!                                                           |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n\n");
	}
}
