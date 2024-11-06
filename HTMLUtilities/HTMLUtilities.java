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
		System.out.println("\n\n\nTokenize called");
		System.out.println(str);
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
				if (currChar == '<'){
					currStr += str.substring(i, str.indexOf(">")+1);
					System.out.println(currStr);
					i = str.indexOf(">");
					if (currStr.charAt(currStr.length()-1) != '>'){
						currStr += '>';
					}
					result[curr] = currStr;
					curr++;
					currStr = "";
				}
				else if (Character.isLetter(currChar)){
					currType = 1;
					currStr += currChar;
				}
				else if (Character.isDigit(currChar)|| i < str.length()-1 && currChar == '-' && Character.isDigit(str.charAt(i+1))){
					currType = 2;
					currStr += currChar;
				}
				else{
					currType = 3;
					currStr += currChar;
				}
			}
			else if (currType == 1){
				if (Character.isLetter(currChar)||currChar == '-' && Character.isLetter(str.charAt(i+1))){
					currStr += currChar;
				}
				else if (Character.isDigit(currChar)|| currChar == '-' && Character.isDigit(str.charAt(i+1))){
					currType = 2;
					result[curr] = currStr;
					curr++;
					currStr = "" + currChar;
				}
				else{
					currType = 3;
					result[curr] = currStr;
					curr++;
					currStr = "" + currChar;
				}
			}
			else if (currType == 2){
				if (Character.isDigit(currChar)){
					currStr += currChar;
				}
				else if(currChar == '-' && Character.isDigit(str.charAt(i+1))){
					result[curr] = currStr;
					curr++;
					currStr += currChar;
				}
				else if (Character.isLetter(currChar)){
					currType = 1;
					result[curr] = currStr;
					curr++;
					currStr = "" + currChar;
				}
				else{
					currType = 3;
					result[curr] = currStr;
					curr++;
					currStr = "" + currChar;
				}
			}
			else{
				result[curr] = currStr;
				curr++;
				currType = 0;
				currStr = "" + currChar;
			}
		}
		String[] ans = new String[curr];
		for (int i = 0; i < curr; i++)ans[i] = result[i];
		return ans;
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
