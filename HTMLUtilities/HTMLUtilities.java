/**
 *	Utilities for handling HTML
 *
 *	@author	
 *	@since	
 */
public class HTMLUtilities {

	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) {
		// make the size of the array large to start
		String[] result = new String[10000];
		int curr = 0;
		int currType = 0;//1 for string, 2 for number, 3 for punctuation
		String currStr = "";
		for (int i = 0; i < str.length(); i++){
			char currChar = str.charAt(i);
			if (currChar == ' '){
				result[curr] = currStr;
				currType = 0;
				curr++;
				currStr = "";
			}
			else if (currType == 0){
				if (isLetter(currChar)){
					currType = 1;
					currStr += currChar;
				}
				else if (isDigit(currChar)|| i < str.length()-1 && currChar == '-' && isDigit(str.charAt(i+1))){
					currType = 2;
					currStr += currChar;
				}
				else{
					currType = 3;
				}
			}
			else if (currType == 1){
			}
			else if (currType == 2){
			}
			else{
			}
		}
		// return the correctly sized array
		return result;
	}
	
	/**
	 *	Print the tokens in the array to the screen
	 *	Precondition: All elements in the array are valid String objects.
	 *				(no nulls)
	 *	@param tokens		an array of String tokens
	 */
	public void printTokens(String[] tokens) {
		if (tokens == null) return;
		for (int a = 0; a < tokens.length; a++) {
			if (a % 5 == 0) System.out.print("\n  ");
			System.out.print("[token " + a + "]: " + tokens[a] + " ");
		}
		System.out.println();
	}

}
