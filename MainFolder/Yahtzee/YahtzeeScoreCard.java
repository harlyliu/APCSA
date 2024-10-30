public class YahtzeeScoreCard{
	int[] scores = new int[13];
	public void setScore(int index, int val){
		scores[index-1] = val;
	}
	
	public int getScore(int index){
		return scores[index-1];
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
		for (int i = 1; i < 14; i++) {
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
		if (choice >= 1 && choice <= 6) numberScore(choice, dg);
		if (choice == 7) threeOfAKind(dg);
		if (choice == 8) fourOfAKind(dg);
		if (choice == 9) fullHouse(dg);
		if(choice == 10) smallStraight(dg);
		if (choice == 11) largeStraight(dg);
		if (choice == 12) change(dg);
		if (choice == 13) yahtzeeScore(dg);
	}
	
	/**
	 *  Change the scorecard for a number score 1 to 6
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, DiceGroup dg) {
		int val = 0;
		for (int i = 0; i < 6; i++){
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
		int[] amts = new int[6];
		for (int i = 0; i < 6; i++)amts[dg.getDice(i).getValue()-1]++;
		boolean found = false;
		for (int i = 0; i < 6; i++){
			if (amts[i] == 3) found = true;
		}
		int val = 0;
		if (found){
			for (int i = 0; i < 6; i++)val += dg.getDice(i).getValue();
		}
		scores[6] = val;
	}
	
	public void fourOfAKind(DiceGroup dg) {
		int[] amts = new int[6];
		for (int i = 0; i < 6; i++)amts[dg.getDice(i).getValue()-1]++;
		boolean found = false;
		for (int i = 0; i < 6; i++){
			if (amts[i] == 4) found = true;
		}
		int val = 0;
		if (found){
			for (int i = 0; i < 6; i++)val += dg.getDice(i).getValue();
		}
		scores[7] = val;
	}
	
	public void fullHouse(DiceGroup dg) {
		int[] amts = new int[6];
		for (int i = 0; i < 6; i++)amts[dg.getDice(i).getValue()-1]++;
		boolean found3 = false;
		boolean found2 = false;
		for (int i = 0; i < 6; i++){
			if (amts[i] == 3) found3 = true;
			if (amts[i] == 2) found2 = true;
		}
		int val = 0;
		if (found3 && found2){
			val = 25;
		}
		scores[8] = val;
	}
	
	public void smallStraight(DiceGroup dg) {
		int[] amts = new int[6];
		for (int i = 0; i < 6; i++)amts[dg.getDice(i).getValue()-1]++;
		boolean found = false;
		if (amts[0] >= 1 && amts[1] >= 1 && amts[2] >= 1 && amts[3] >= 1) found = true;
		if (amts[1] >= 1 && amts[2] >= 1 && amts[3] >= 1 && amts[4] >= 1) found = true;
		int val = 0;
		if (found) val = 30;
		scores[9] = val;
	}
	
	public void largeStraight(DiceGroup dg) {
		int[] amts = new int[6];
		for (int i = 0; i < 6; i++)amts[dg.getDice(i).getValue()-1]++;
		boolean found = false;
		if (amts[0] >= 1 && amts[1] >= 1 && amts[2] >= 1 && amts[3] >= 1 && amts[4] >= 1) found = true;
		int val = 0;
		if (found) val = 40;
		scores[10] = val;
	}
	
	public void chance(DiceGroup dg) {
		int val = 0;
		for (int i = 0; i < 6; i++)val += dg.getDice(i).getValue();
		scores[11] = val;
	}
	
	public void yahtzeeScore(DiceGroup dg) {
		int[] amts = new int[6];
		for (int i = 0; i < 6; i++)amts[dg.getDice(i).getValue()-1]++;
		boolean found = false;
		for (int i = 0; i < 6; i++){
			if (amts[i] == 5) found = true;
		}
		int val = 0;
		if (found){
			val = 50;
		}
		scores[6] = val;
	}
}
