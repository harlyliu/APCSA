import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *	AnagramMaker - <description goes here>
 *
 *	Requires the WordUtilities, SortMethods, Prompt, and FileUtils classes
 *
 *	@author	
 *	@since	
 */
public class AnagramMaker {
								
	private final String FILE_NAME = "randomWords.txt";	// file containing all words
	
	private WordUtilities wu;	// the word utilities for building the word
								// database, sorting the database,
								// and finding all words that match
								// a string of characters
	
	// variables for constraining the print output of AnagramMaker
	private int numWords;		// the number of words in a phrase to print
	private int maxPhrases;		// the maximum number of phrases to print
	private int numPhrases;		// the number of phrases that have been printed
		
	/*	Initialize the database inside WordUtilities
	 *	The database of words does NOT have to be sorted for AnagramMaker to work,
	 *	but the output will appear in order if you DO sort.
	 */
	public AnagramMaker() {
		wu = new WordUtilities();
		wu.readWordsFromFile(FILE_NAME);
		wu.sortWords();
	}
	
	public static void main(String[] args) {
		AnagramMaker am = new AnagramMaker();
		am.run();
	}
	
	/**	The top routine that prints the introduction and runs the anagram-maker.
	 */
	public void run() {
		printIntroduction();
		runAnagramMaker();
		System.out.println("\nThanks for using AnagramMaker!\n");
	}
	
	/**
	 *	Print the introduction to AnagramMaker
	 */
	public void printIntroduction() {
		System.out.println("\nWelcome to ANAGRAM MAKER");
		System.out.println("\nProvide a word, name, or phrase and out comes their anagrams.");
		System.out.println("You can choose the number of words in the anagram.");
		System.out.println("You can choose the number of anagrams shown.");
		System.out.println("\nLet's get started!");
	}
	
	/**
	 *	Prompt the user for a phrase of characters, then create anagrams from those
	 *	characters.
	 */
	public void runAnagramMaker() {
		String testWord = "computerscience";
		numPhrases = 0;
		maxPhrases = 10;
		numWords = 3;
		recur(testWord, "", numWords);
	}
	
	public String removeSubstring(String str, String subStr) {
        String ans = new String(str);
        for (int i = 0; i < subStr.length(); i++){
			int ind = ans.indexOf(subStr.charAt(i));
			ans = ans.substring(0, ind) + ans.substring(ind+1);
		}
		return ans;
	}
	
       
	
	public void recur(String str, String currAnagram, int wordsLeft){
		if (wordsLeft == 0 && str.length() != 0)return;
		if (numPhrases >= maxPhrases) return;
		if (str.length() == 0){
			if (wordsLeft == 0) {
				System.out.println(currAnagram);
				numPhrases++;
			}
			return;
		}
		List<String> temp = new ArrayList<String>();
		temp = wu.allWords(str);
		for (int i = 0; i < temp.size(); i++){
			recur(removeSubstring(str, temp.get(i)), currAnagram + temp.get(i) + " ", wordsLeft-1);
		}
		
	}

	
}
