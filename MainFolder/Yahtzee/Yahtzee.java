public class Yahtzee{
	final int TOTAL_TURNS = 13;
	YahtzeePlayer player1;
	YahtzeePlayer player2;
	DiceGroup diceGroup1;
	DiceGroup diceGroup2;
	
	public Yahtzee(){}
	
	public static void main(String[] args){
		Yahtzee yahtzee = new Yahtzee();
		yahtzee.playGame();
	}
	
	public void playGame(){
		printHeader();
		player1 = new YahtzeePlayer();
		player2 = new YahtzeePlayer();
		String player1Name = Prompt.getString("Player 1, please enter your first name ");
		String player2Name = prompt.getString("Player 2, please enter your first name ");
		player1.setName(player1Name);
		player2.setName(player2Name);
		diceGroup1 = new DiceGroup();
		diceGroup2 = new DiceGroup();
		int first = determineOrder();
		for (int i = 0; i < TOTAL_TURNS; i++){
			if (first == 1){
				runPlayerTurn(player1);
				runPlayerTurn(player2);
			}
			else{
				runPlayerTurn(player2);
				runPlayerTurn(player1);
			}
			player1.getScoreCard().printCardHeader();
			player1.getScoreCard().printPlayerScore();
			player2.getScoreCard().printPlayerScore();
		}
	}
	
	public void runPlayerTurn(int player){
		String inp;
		if (player == 1){
			do{
				Prompt.getString(player1.getName + " , it's your turn to play. Please hit enter to roll the dice");
				DiceGroup1.rollDice();
				DiceGroup1.printDice();
				inp = Prompt.getString("Which di(c)e would you like to keep? "
				+"Enter the values you'd like to 'hold' without"
				+ "spaces. For examples, if you'd like to 'hold' die 1, "
				+ "2, and 5, enter 125 (enter -1 if you'd like to end the turn)"); 
			}while(!inp.equals("-1"));
			int val = Prompt.getInt(player1.getName+ ", now you need to "
				+"make a choice. Pick a valid integer from the list above (1 - 13)");
		}
		
	}
	
	public int determineOrder(){
		int val1 = 0;
		int val2 = 0;
		do{
			Prompt.getString("Let's see who will go first. " + player1.getName() + 
				", please hit enter to roll the dice");
			diceGroup1.rollDice();
			val1 = diceGroup1.getTotal();
			Prompt.getString(player2.getName() + 
				", it's your turn. Please hit enter to roll the dice");
			diceGroup2.rollDice();
			val2 = diceGroup2.getTotal();
		}while(val1 != val2);
		if (val1 > val2) return 1;
		return 2;
	}
	
	
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
