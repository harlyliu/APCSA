/**
 *	Utilities for handling HTML
 *
 *	@author	
 *	@since	
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
		String[] tokens = new String[str.length()];
		int count = 0;
		
		String currStr = new String();
		boolean inTag = false;
		if (str.equals("<pre>")){
			state = TokenState.PREFORMAT;
			tokens[0] = str;
		}
		else if (state == TokenState.PREFORMAT && str.equals("</pre>")){
			state = TokenState.NONE;
			tokens[0] = str;
		}
		else if (state == TokenState.PREFORMAT){
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
			else if (i +3 <= str.length() && state == TokenState.COMMENT && str.substring(i, i+3).equals("-->")){
				state = TokenState.NONE;
				i += 4;
			}
			if (i < str.length() && state == TokenState.NONE){
				if (c == '<') {
					if (currStr.length() > 0) {
						tokens[count++] = currStr.toString();
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
						tokens[count++] = currStr.toString();
						currStr = "";
					}
				}
				else if (Character.isDigit(c) || c == '.') {
					if (currStr.length() > 0 && 
						!Character.isDigit(currStr.charAt(currStr.length() - 1)) &&
						currStr.charAt(currStr.length() - 1) != '.' &&
						!(currStr.length() == 1 && currStr.charAt(0) == '-')) {
						tokens[count++] = currStr;
						currStr = "";
					}
					currStr += c;
				}
				else if (c == '-') {
					if (currStr.length() == 0 || 
						(!Character.isDigit(currStr.charAt(0)) && !Character.isLetter(currStr.charAt(0)))) {
						currStr += c;
					}
					else if (currStr.length() > 0 && Character.isLetter(currStr.charAt(currStr.length() - 1))) {
							tokens[count++] = currStr.toString();
							currStr = "";
							currStr += c;
						}
					else {
						if (currStr.length() > 0) {
							tokens[count++] = currStr.toString();
							currStr = "";
						}
						tokens[count++] = "" + c;
					}
				}
				else if (Character.isLetter(c)) {
					if (currStr.length() > 0 && 
						(!Character.isLetter(currStr.charAt(0)) && currStr.charAt(0) != '-' && !Character.isDigit(currStr.charAt(0)))) {
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
