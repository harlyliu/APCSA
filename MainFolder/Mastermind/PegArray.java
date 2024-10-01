/**
 *  This class creates and manages one array of pegs from the game MasterMind.
 *
 *  @author Harly Liu
 *  @since 9/27/2024
*/

public class PegArray {

	// array of pegs
	private Peg [] pegs;
	
	private final int PEGS_IN_CODE = 4;		// Number of pegs in code
	// the number of exact and partial matches for this array
	// as matched against the master.
	// Precondition: these values are valid after getExactMatches() and getPartialMatches()
	//				are called
	private int exactMatches, partialMatches;
		
	/**
	 *	Constructor
	 *	@param numPegs	number of pegs in the array
	 */
	public PegArray(int numPegs) {	
		pegs = new Peg[numPegs];
		for (int i = 0; i < PEGS_IN_CODE; i++){
			pegs[i] = new Peg();
		}
	}
	
	/**
	 *	Return the peg object
	 *	@param n	The peg index into the array
	 *	@return		the peg object
	 */
	public Peg getPeg(int n) { return pegs[n]; }
	
	/**
	 *  Finds exact matches between master (key) peg array and this peg array
	 *	Postcondition: field exactMatches contains the matches with the master
	 *  @param master	The master (code) peg array
	 *	@return			The number of exact matches
	 */
	public int getExactMatches(PegArray master) { 
		exactMatches = 0;
		for (int i = 0; i < PEGS_IN_CODE; i++){
			if (master.getPeg(i).getLetter() == pegs[i].getLetter()) 
				exactMatches++; 
		}
		return exactMatches; 
	}
	
	/**
	 *  Find partial matches between master (key) peg array and this peg array
	 *	Postcondition: field partialMatches contains the matches with the master
	 *  @param master	The master (code) peg array
	 *	@return			The number of partial matches
	 */
	public int getPartialMatches(PegArray master) {
		partialMatches = 0;
		exactMatches = 0;
		int[] used = new int[PEGS_IN_CODE];
		for (int i = 0; i < PEGS_IN_CODE; i++){
			if (master.getPeg(i).getLetter() == pegs[i].getLetter()){
				used[i] = 1;
				exactMatches++;
			}
		}
		for (int i = 0; i < PEGS_IN_CODE; i++){
			for (int j = 0; j < PEGS_IN_CODE; j++){
				if (used[j] == 0 && master.getPeg(j).getLetter()
					== pegs[i].getLetter()){
					partialMatches++;
					used[j] = 1;
					break;
				}
			}
		}
		return partialMatches; 
	}
	
	// Accessor methods
	// Precondition: getExactMatches() and getPartialMatches() must be called first
	public int getExact() { return exactMatches; }
	public int getPartial() { return partialMatches; }

}
