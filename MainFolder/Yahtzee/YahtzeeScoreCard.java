/**
 *  Runs the score card class. Keeps track of all of the players scores
 * and modifies them accordingly
 *
 *  @author Harly Liu
 *  @since 10/31/24
 */

public class YahtzeeScoreCard{
	private final int NUM_DICE = 5;	// number of dice
	private final int NUM_SIDES = 6;	// number of sides
	private final int NUM_SLOTS = 13;	// number of dice

	
	int[] scores = new int[NUM_SLOTS];//main array of all the current scores
	
	/**Constructor, initialize all scores to -1
	 */
	public YahtzeeScoreCard(){
		for (int i = 0; i < NUM_SLOTS; i++) scores[i] = -1;
	}
	
	/**Sets the value of inidivdual scores
	 * @param int index of slot, val what to change it to
	 * @return none
	 */
	public void setScore(int index, int val){
		scores[index-1] = val;
	}
	
	/**Returns the score of a specific slot
	 * @param index of slot
	 * @return value
	 */
	public int getScore(int index){
		return scores[index-1];
	}
	
	/**Gets total sum of all scores
	 * @param none
	 * @return int, sum of all values in array
	 */
	public int getSum(){
		int ans = 0;
		for (int i = 0; i < NUM_SLOTS; i++)ans += scores[i];
		return ans;
	}
	
	/**
	 *  Print the scorecard header
	 */
	public void printCardHeader() {
		System.out.println("\n");
		System.out.printf("\t\t\t\t\t       3of  4of  Fll Smll Lrg\n");
		System.out.printf("  NAME\t\t  1    2    3    4    5    6   Knd  Knd  Hse " +
						"Strt Strt Chnc Ytz!\n");
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}
	
	/**
	 *  Prints the player's score
	 */
	public void printPlayerScore(YahtzeePlayer player) {
		System.out.printf("| %-12s |", player.getName());
		for (int i = 1; i < NUM_SLOTS+1; i++) {
			if (getScore(i) > -1)
				System.out.printf(" %2d |", getScore(i));
			else System.out.printf("    |");
		}
		System.out.println();
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}

	/**
	 *  Change the scorecard based on the category choice 1-13.
	 *
	 *  @param choice The choice of the player 1 to 13
	 *  @param dg  The DiceGroup to score
	 *  @return  true if change succeeded. Returns false if choice already taken.
	 */
	public boolean changeScore(int choice, DiceGroup dg) {
		if (choice < 1 || choice > 13) return false;
		if (scores[choice-1] != -1) return false;
		if (choice >= 1 && choice <= 6) numberScore(choice, dg);
		if (choice == 7) threeOfAKind(dg);
		if (choice == 8) fourOfAKind(dg);
		if (choice == 9) fullHouse(dg);
		if(choice == 10) smallStraight(dg);
		if (choice == 11) largeStraight(dg);
		if (choice == 12) chance(dg);
		if (choice == 13) yahtzeeScore(dg);
		return true;
	}
	
	/**
	 *  Change the scorecard for a number score 1 to 6
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, DiceGroup dg) {
		int val = 0;
		for (int i = 0; i < NUM_DICE; i++){
			if (dg.getDice(i).getValue() == choice) val += choice;
		}
		scores[choice-1] = val;
	}
	
	/**
	 *	Updates the scorecard for Three Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(DiceGroup dg) {
		int[] amts = new int[NUM_SIDES];
		for (int i = 0; i < NUM_DICE; i++)amts[dg.getDice(i).getValue()-1]++;
		boolean found = false;
		for (int i = 0; i < NUM_SIDES; i++){
			if (amts[i] == 3) found = true;
		}
		int val = 0;
		if (found){
			for (int i = 0; i < NUM_DICE; i++)val += dg.getDice(i).getValue();
		}
		scores[6] = val;
	}
	
		/**
	 *	Updates the scorecard for four Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void fourOfAKind(DiceGroup dg) {
		int[] amts = new int[NUM_SIDES];
		for (int i = 0; i < NUM_DICE; i++)amts[dg.getDice(i).getValue()-1]++;
		boolean found = false;
		for (int i = 0; i < NUM_SIDES; i++){
			if (amts[i] == 4) found = true;
		}
		int val = 0;
		if (found){
			for (int i = 0; i < NUM_DICE; i++)val += dg.getDice(i).getValue();
		}
		scores[7] = val;
	}
	
		/**
	 *	Updates the scorecard for full house choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void fullHouse(DiceGroup dg) {
		int[] amts = new int[NUM_SIDES];
		for (int i = 0; i < NUM_DICE; i++)amts[dg.getDice(i).getValue()-1]++;
		boolean found3 = false;
		boolean found2 = false;
		for (int i = 0; i < NUM_SIDES; i++){
			if (amts[i] == 3) found3 = true;
			if (amts[i] == 2) found2 = true;
		}
		int val = 0;
		if (found3 && found2){
			val = 25;
		}
		scores[8] = val;
	}
	
		/**
	 *	Updates the scorecard for small straight choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	
	public void smallStraight(DiceGroup dg) {
		int[] amts = new int[NUM_SIDES];
		for (int i = 0; i < NUM_DICE; i++)amts[dg.getDice(i).getValue()-1]++;
		boolean found = false;
		if (amts[0] >= 1 && amts[1] >= 1 && amts[2] >= 1 && amts[3] >= 1) found = true;
		if (amts[1] >= 1 && amts[2] >= 1 && amts[3] >= 1 && amts[4] >= 1) found = true;
		if (amts[2] >= 1 && amts[3] >= 1 && amts[4] >= 1 && amts[5] >= 1) found = true;
		int val = 0;
		if (found) val = 30;
		scores[9] = val;
	}
		/**
	 *	Updates the scorecard for large straight choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void largeStraight(DiceGroup dg) {
		int[] amts = new int[NUM_SIDES];
		for (int i = 0; i < NUM_DICE; i++)amts[dg.getDice(i).getValue()-1]++;
		boolean found = false;
		if (amts[0] >= 1 && amts[1] >= 1 && amts[2] >= 1 && amts[3] >= 1 && amts[4] >= 1) found = true;
		if (amts[1] >= 1 && amts[2] >= 1 && amts[3] >= 1 && amts[4] >= 1 && amts[5] >= 1) found = true;
		int val = 0;
		if (found) val = 40;
		scores[10] = val;
	}
		/**
	 *	Updates the scorecard for chance choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void chance(DiceGroup dg) {
		int val = 0;
		for (int i = 0; i < NUM_DICE; i++)val += dg.getDice(i).getValue();
		scores[11] = val;
	}
	
	/**
	 *	Updates the scorecard for yahtzee choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void yahtzeeScore(DiceGroup dg) {
		int[] amts = new int[NUM_SIDES];
		for (int i = 0; i < NUM_DICE; i++)amts[dg.getDice(i).getValue()-1]++;
		boolean found = false;
		for (int i = 0; i < NUM_SIDES; i++){
			if (amts[i] == 5) found = true;
		}
		int val = 0;
		if (found){
			val = 50;
		}
		scores[12] = val;
	}
}
