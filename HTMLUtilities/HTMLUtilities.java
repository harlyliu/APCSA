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
		String[] tokens = new String[str.length()];
		int tokenCount = 0;
		
		StringBuilder currentToken = new StringBuilder();
		boolean inTag = false;
		
		for (int i = 0; i < str.length(); i++) {
		    char c = str.charAt(i);
		
		    if (c == '<') {
		        if (currentToken.length() > 0) {
		            tokens[tokenCount++] = currentToken.toString();
		            currentToken.setLength(0);
		        }
		        inTag = true;
		        currentToken.append(c);
		    }
		    else if (c == '>') {
		        if (inTag) {
		            currentToken.append(c);
		            tokens[tokenCount++] = currentToken.toString();
		            currentToken.setLength(0);
		            inTag = false;
		        }
		    }
		    else if (inTag) {
		        currentToken.append(c);
		    }
		    else if (Character.isWhitespace(c)) {
		        if (currentToken.length() > 0) {
		            tokens[tokenCount++] = currentToken.toString();
		            currentToken.setLength(0);
		        }
		    }
		    else if (Character.isDigit(c) || c == '.') {
		        // Handle digits and decimal points
		        if (currentToken.length() > 0 && 
		            !Character.isDigit(currentToken.charAt(currentToken.length() - 1)) &&
		            currentToken.charAt(currentToken.length() - 1) != '.' &&
		            !(currentToken.length() == 1 && currentToken.charAt(0) == '-')) {
		            tokens[tokenCount++] = currentToken.toString();
		            currentToken.setLength(0);
		        }
		        currentToken.append(c);
		    }
		    else if (c == '-') {
		        if (currentToken.length() == 0 || 
		            (!Character.isDigit(currentToken.charAt(0)) && !Character.isLetter(currentToken.charAt(0)))) {
		            // Treat as a negative sign if at the start or not following a letter/number
		            currentToken.append(c);
		        } else if (currentToken.length() > 0 && Character.isLetter(currentToken.charAt(currentToken.length() - 1))) {
		            // Treat as part of a number if it follows a letter (e.g., "hello-1.1")
		            tokens[tokenCount++] = currentToken.toString();
		            currentToken.setLength(0);
		            currentToken.append(c);
		        } else {
		            // Treat as a hyphen in a word or punctuation
		            if (currentToken.length() > 0) {
		                tokens[tokenCount++] = currentToken.toString();
		                currentToken.setLength(0);
		            }
		            tokens[tokenCount++] = String.valueOf(c); // Treat the hyphen as a separate token
		        }
		    }
		    else if (Character.isLetter(c)) {
		        // Handle letters
		        if (currentToken.length() > 0 && 
		            (!Character.isLetter(currentToken.charAt(0)) && currentToken.charAt(0) != '-' && !Character.isDigit(currentToken.charAt(0)))) {
		            tokens[tokenCount++] = currentToken.toString();
		            currentToken.setLength(0);
		        }
		        currentToken.append(c);
		    }
		    else {
		        // Handle other characters as separate tokens
		        if (currentToken.length() > 0) {
		            tokens[tokenCount++] = currentToken.toString();
		            currentToken.setLength(0);
		        }
		        tokens[tokenCount++] = String.valueOf(c);
		    }
		}
		if (currentToken.length() > 0) {
		    tokens[tokenCount++] = currentToken.toString();
		}
		
		String[] ans = new String[tokenCount];
		for (int i = 0; i < tokenCount; i++) ans[i] = tokens[i];
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
