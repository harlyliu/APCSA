
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
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
public class WordUtils
{
	private List<String> allWords;		// the dictionary of words
	
	// File containing dictionary of almost 100,000 words.
	private final String WORD_FILE = "wordList.txt";
	
	private List<List<String>> words;	// words that match letters, 1st dimension
								// is number of letters in the word
	private int [] numWords;	// the number of words found
	
	private int wordsFound = 0;
	
	/* Constructor */
	public WordUtils(int letters) { 
		words = new ArrayList<new ArrayList<>>(letters);
		numWords = new ArrayList<>(letters);
	}
	
	/**	Load all of the dictionary from a file into words array. */
	private void loadWords () { 
			allWords = new ArrayList<>();
			Scanner wordScanner = FileUtils.openToRead(WORD_FILE);
			while (wordScanner.hasNext()){
				allWords.add(wordScanner.next());
			}
			wordScanner.close();
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
		/**
	 *	Determines if a word's characters match a group of letters
	 *	@param word		the word to check
	 *	@param letters	the letters
	 *	@return			true if the word's chars match; false otherwise
	 */
	private boolean wordMatch(String word, String letters) {
		// if the word is longer than letters return false
		if (word.length() > letters.length()) return false;
		
		// while there are still characters in word, check each word character
		// with letters
		while (word.length() > 0) {
			// using the first character in word, find the character's index inside letters
			// and ignore the case
			int index = letters.toLowerCase().indexOf(Character.toLowerCase(word.charAt(0)));
			// if the word character is not in letters, then return false
			if (index < 0) return false;
			
			// remove character from word and letters
			word = word.substring(1);
			letters = letters.substring(0, index) + letters.substring(index + 1);
		}
		// all word letters were found in letters
		return true;
	}
	
		/**
		 *	finds all words that match some or all of a group of alphabetic characters
		 *	Precondition: letters can only contain alphabetic characters a-z and A-Z
		 *	@param letters		group of alphabetic characters
		 *	@return				an ArrayList of all the words that match some or all
		 *						of the characters in letters
		 */
		public ArrayList<String> allWords(String letters) {
			ArrayList<String> wordsFound = new ArrayList<String>();
			// check each word in the database with the letters
			for (String word: words[letters.length()])
				if (wordMatch(word, letters))
					wordsFound.add(word);
			return wordsFound;
		}
		
		/**
		 *	Sort the words in the database
		 */
		public void sortWords() {
			SortMethods sm = new SortMethods();
			sm.mergeSort(words);
		}

}
