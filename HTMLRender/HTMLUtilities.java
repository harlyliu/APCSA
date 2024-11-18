/**
 *	Utilities for handling HTML
 * Breaks up a regular string into different tokens
 * tags: <html>
 * strings: hello-word
 * ints: 0, -4e-2,-3.15
 * punctuation: . : '
 * and takes into account comments and <pre>
 *	@author	Harly Liu
 *	@since	11/14/2024
 */
public class HTMLUtilities {
	// NONE = not nested in a block, COMMENT = inside a comment block
	// PREFORMAT = inside a pre-format block
	private enum TokenState { NONE, COMMENT, PREFORMAT };
	// the current tokenizer state
	private TokenState state = TokenState.NONE;
	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) {
		String[] tokens = new String[10000];
		int count = 0;
		
		String currStr = new String();
		boolean inTag = false;
		if (str.equals("<pre>")){
			state = TokenState.PREFORMAT;
			count++;
			tokens[0] = str;
		}
		else if (state == TokenState.PREFORMAT && str.equals("</pre>")){
			state = TokenState.NONE;
		}
		else if (state == TokenState.PREFORMAT){
			count++;
			tokens[0] = str;
		}
		
		for (int i = 0; i < str.length(); i++) {
		    char c = str.charAt(i);
			if (i +4 <= str.length() && str.substring(i, i+4).equals("<!--")){
				state = TokenState.COMMENT;
				if (currStr.length() != 0){
					tokens[count++] = currStr;
					currStr = "";
					i += 4;
				}
			}
			else if (i +3 <= str.length() && state == TokenState.COMMENT
			 && str.substring(i, i+3).equals("-->")){
				state = TokenState.NONE;
				currStr = "";
				i += 3;
				if (i < str.length()){
					c = str.charAt(i);
				}
			}
			if (i < str.length() && state == TokenState.NONE){
				if (c == '<') {
					if (currStr.length() > 0) {
						tokens[count++] = currStr;
						currStr = "";
					}
					inTag = true;
					currStr += c;
				}
				else if (c == '>') {
					if (inTag) {
						currStr += c;
						tokens[count++] = currStr;
						currStr = "";
						inTag = false;
					}
				}
				else if (inTag) {
					currStr += c;
				}
				else if (Character.isWhitespace(c)) {
					if (currStr.length() > 0) {
						tokens[count++] = currStr;
						currStr = "";
					}
				}
				else if (Character.isDigit(c) || c == '.') {
					if (currStr.length() > 0 && currStr.charAt(currStr.length()-1) == 'e' 
						&& !Character.isLetter(currStr.charAt(0)) 
						||  currStr.length() > 0 && 
						currStr.charAt(currStr.length()-1) == '-'){}
					else if (currStr.length() > 0 && 
						!Character.isDigit(currStr.charAt(currStr.length() - 1)) &&
						currStr.charAt(currStr.length() - 1) != '.' &&
						!(currStr.length() == 1 && currStr.charAt(0) == '-')
						|| c == '.' && currStr.indexOf('.') != -1) {
						tokens[count++] = currStr;
						currStr = "";
					}
					currStr += c;
				}
				else if (c == '-') {
					if (currStr.length() == 0 || 
						(!Character.isDigit(currStr.charAt(0)) && 
						!Character.isLetter(currStr.charAt(0)))
						|| Character.isDigit(currStr.charAt(0))
						&& currStr.charAt(currStr.length()-1) == 'e') {
						currStr += c;
					}
					else if (currStr.length() > 0 && 
						Character.isLetter(currStr.charAt(currStr.length() - 1))) {
							if (i + 1 < str.length() && Character.isLetter(str.charAt(i+1))){
								currStr += c;
							}
							else{
								tokens[count++] = currStr;
								currStr = "";
								currStr += c;
							}
						}
					else {
						if (currStr.length() > 0) {
							tokens[count++] = currStr;
							currStr = "";
						}
						tokens[count++] = "" + c;
					}
				}
				else if (c == 'e'){
					if (currStr.length() != 0 && 
					Character.isDigit(currStr.charAt(currStr.length()-1))){
						currStr += c;
					}
					else{
						if (currStr.length() > 0 && 
						(!Character.isLetter(currStr.charAt(0)) && 
						currStr.charAt(0) != '-' && !Character.isDigit(currStr.charAt(0)))) {
							tokens[count++] = currStr;
							currStr = "";
						}
						currStr += c;
					}
				}
				else if (Character.isLetter(c)) {
					if (currStr.length() > 0 && 
						(!Character.isLetter(currStr.charAt(0)) && 
						currStr.charAt(0) != '-' && 
						!Character.isDigit(currStr.charAt(0)))) {
						tokens[count++] = currStr;
						currStr = "";
					}
					currStr += c;
				}
				else {
					if (currStr.length() > 0) {
						tokens[count++] = currStr;
						currStr = "";
					}
					tokens[count++] = ""+c;
				}
			}
		}
		if (currStr.length() > 0) {
		    tokens[count++] = currStr;
		}
		
		String[] ans = new String[count];
		for (int i = 0; i < count; i++) ans[i] = tokens[i];
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
