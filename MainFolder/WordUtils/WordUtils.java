
/**
 *	Provides utilities for word games:
 *	1. finds all words in the dictionary that match a list of letters
 *	2. prints an array of words to the screen in tabular format
 *	3. finds the word from an array of words with the highest score
 *	4. calculates the score of a word according to a table
 *
 *	Uses the FileUtils and Prompt classes.
 *	
 *	@author Harly Liu
 *	@since	10/19/2024
 */
 
import java.util.Scanner;
public class WordUtils
{
	private String[] allWords;		// the dictionary of words
	
	// File containing dictionary of almost 100,000 words.
	private final String WORD_FILE = "wordList.txt";
	
	private String [][] words;	// words that match letters, 1st dimension
								// is number of letters in the word
	private int [] numWords;	// the number of words found
	
	private int wordsFound = 0;
	private final int MAX_LETTERS = 15;	// maximum letters in word to store
	
	/* Constructor */
	public WordUtils() { 
		words = new String [MAX_LETTERS][100];
		numWords = new int [MAX_LETTERS];
	}
	
	/**	Load all of the dictionary from a file into words array. */
	private void loadWords () { 
			allWords = new String[100000];
			int i = 0;
			Scanner wordScanner = FileUtils.openToRead(WORD_FILE);
			while (wordScanner.hasNext()){
				allWords[i] = wordScanner.next();
				i++;
			}
			wordScanner.close();
	}
	
	/**	Find all words that can be formed by a list of letters.
	 *  @param letters	string containing list of letters
	 *  @return			array of strings with all words found.
	 */
	public String [] findAllWords (String letters)
	{		
		Scanner input = FileUtils.openToRead(WORD_FILE);
		while(input.hasNext()){
			String word = input.next();
			if (isWordMatch(word, letters)){
				words[word.length()][numWords[word.length()]] = word;
				numWords[word.length()]++;
			}
		}
		input.close();
		String[] ans = new String[1000];
		wordsFound = 0;
		for (int row = 0; row < numWords.length; row++) {
			for (int col = 0; col < numWords[row]; col++) {
				ans[wordsFound] = words[row][col];
				wordsFound++;
			}
		}
		return ans;
	}
	
	/**	Print the words found to the screen.
	 *  @param words	array containing the words to be printed
	 */
	public void printWords (String [] wordList) {
		for (int i = 0; i < wordsFound; i++){
			System.out.print(wordList[i] + " ");
			if ((i+1)%10 == 0)System.out.println();
		}
	}

	/**	Finds the highest scoring word according to a score table.
	 *
	 *  @param word  		An array of words to check
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return   			The word with the highest score
	 */
	public String bestWord (String [] wordList, int [] scoreTable)
	{
		int bestScore = 0;
		String curr = "";
		for (int i = 0; i < wordsFound; i++){
			if (getScore(wordList[i], scoreTable) > bestScore){
				curr = wordList[i];
			}
		}
		return curr;
	}
	
	/**	Calculates the score of one word according to a score table.
	 *
	 *  @param word			The word to score
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return				The integer score of the word
	 */
	public int getScore (String word, int [] scoreTable)
	{
		word = word.toUpperCase();
		int ans = 0;
		for (int i = 0; i < word.length(); i++){
			ans += scoreTable[word.charAt(i)-'A'];
		}
		return ans;
	}
	
	/***************************************************************/
	/************************** Testing ****************************/
	/***************************************************************/
	public static void main (String [] args)
	{
		WordUtils wu = new WordUtils();
		wu.run();
	}
	
	public void run() {
		String letters = Prompt.getString("Please enter a list of letters, from 3 to 12 letters long, without spaces");
		loadWords();
		String [] word = findAllWords(letters);
		System.out.println();
		printWords(word);
		
		// Score table in alphabetic order according to Scrabble
		int [] scoreTable = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
		String best = bestWord(word,scoreTable);
		System.out.println("\nHighest scoring word: " + best + "\nScore = " 
							+ getScore(best, scoreTable) + "\n");
	}
	
	
	/**
	 *  Decides if a word matches a group of letters.
	 *
	 *  @param word  The word to test.
	 *  @param letters  A string of letters to compare
	 *  @return  true if the word matches the letters, false otherwise
	 */
	public boolean isWordMatch (String word, String letters) {
		for (int a = 0; a < word.length(); a++){
			char c = word.charAt(a);
			if (letters.indexOf(c) > -1)
				letters = letters.substring(0, letters.indexOf(c))
						+ letters.substring(letters.indexOf(c) + 1);
			else
				return false;
		}
		return true;
	}
	

}
