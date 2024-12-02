/**
 *	HTMLRender
 *	This program renders HTML code into a JFrame window.
 *	It requires your HTMLUtilities class and
 *	the SimpleHtmlRenderer and HtmlPrinter classes.
 *
 *	The tags supported:
 *		<html>, </html> - start/end of the HTML file
 *		<body>, </body> - start/end of the HTML code
 *		<p>, </p> - Start/end of a paragraph.
 *					Causes a newline before and a blank line after. Lines are restricted
 *					to 80 characters maximum.
 *		<hr>	- Creates a horizontal rule on the following line.
 *		<br>	- newline (break)
 *		<b>, </b> - Start/end of bold font print
 *		<i>, </i> - Start/end of italic font print
 *		<q>, </q> - Start/end of quotations
 *		<hX>, </hX> - Start/end of heading with size X = 1, 2, 3, 4, 5, 6
 *		<pre>, </pre> - Preformatted text
 *
 *	@author Harly Liu
 *	@version 12/2/2024
 */
 import java.util.Scanner;
public class HTMLRender {
	
	// the array holding all the tokens of the HTML file
	private String [] tokens;
	private final int TOKENS_SIZE = 100000;	// size of array
	private int amt = 0;
	// SimpleHtmlRenderer fields
	private SimpleHtmlRenderer render;
	private HtmlPrinter browser;
	private HTMLUtilities util;			// HTMLUtilities used in tester
	
		
	public HTMLRender() {
		// Initialize token array
		tokens = new String[TOKENS_SIZE];
		util = new HTMLUtilities();
		// Initialize Simple Browser
		render = new SimpleHtmlRenderer();
		browser = render.getHtmlPrinter();
	}
	
	
	public static void main(String[] args) {
		HTMLRender hf = new HTMLRender();
		hf.run(args);
	}
	
	public void run(String[] args) {
		Scanner input = null;
		String fileName = "";
		// if the command line contains the file name, then store it
		if (args.length > 0)
			fileName = args[0];
		// otherwise print out usage message
		else {
			System.out.println("Usage: java HTMLTester <htmlFileName>");
			System.exit(0);
		}
		
		// Open the HTML file
		input = FileUtils.openToRead(fileName);
		
		// Read each line of the HTML file, tokenize, then print tokens
		while (input.hasNext()) {
			String line = input.nextLine();
			String [] lineTokens = util.tokenizeHTMLString(line);
			for (int i = 0; i < lineTokens.length; i++){
				tokens[amt] = lineTokens[i];
				amt++;
			}
		}
		input.close();
		for (int i = 0; i < amt; i++)System.out.print(tokens[i] + " ");
		String currLine = "";
		int state = 0;//1 for p, 2 for quotation, 3 for bold, 4 for italics
		//5 for horizontal, 6 for h1, 7 for h2, 8 for h3, 9 for h4, 10 for h5
		//11 for h6, 12 for pre
		for (int i = 0; i < amt; i++){
			String currTag = tokens[i];System.out.println(currTag);
			if (currTag.charAt(0) == '<' && currTag.charAt(currTag.length()-1) == '>'){
				if (currTag.equalsIgnoreCase("<pre>")){
					i++;
					while (!tokens[i].equalsIgnoreCase("</pre>")){
						browser.printPreformattedText(tokens[i+1]);
						browser.println();
						i++;
					}
					currLine = "";
				}
				if (currTag.equalsIgnoreCase("<html>")||
					currTag.equalsIgnoreCase("</html>")||
					currTag.equalsIgnoreCase("<body>")||
					currTag.equalsIgnoreCase("</body>")||
					currTag.equalsIgnoreCase("<!DOCTYPE html>")){}
				if (currTag.length() >= 2 && currTag.charAt(1) != '/' && currLine != ""){
					for (int ind = 0; ind < currLine.length(); ind+=80){
						browser.print(currLine.substring(ind, Math.min(ind+80, currLine.length())));
						if (ind + 80 < currLine.length()) browser.println();
						browser.print(" ");
					}
					currLine = "";
				}
				if (currTag.equalsIgnoreCase("<p>")){
					browser.println();
				}
				else if (currTag.equalsIgnoreCase("</p>")){
					if (currLine.length() != 0)browser.print(currLine);
					currLine = "";
					browser.println();
				}				
				else if (currTag.equalsIgnoreCase("<br>")){
					browser.printBreak();
				}
				else if (currTag.equalsIgnoreCase("<hr>")){
					browser.printHorizontalRule();
				}
				else if (currTag.equalsIgnoreCase("</b>")){
					for (int ind = 0; ind < currLine.length(); ind+=80){
						browser.printBold(currLine.substring(ind, Math.min(ind+80, currLine.length())));
						if (ind + 80 < currLine.length()) browser.println();
					}
					browser.print(" ");
					currLine = "";
				}
				else if (currTag.equalsIgnoreCase("</i>")){
					for (int ind = 0; ind < currLine.length(); ind+=80){
						browser.printItalic(currLine.substring(ind, Math.min(ind+80, currLine.length())));
						if (ind + 80 < currLine.length()) browser.println();
					}
					browser.print(" ");
					currLine = "";
				}
				else if (currTag.equalsIgnoreCase("<q>")){
					browser.print("\"");
				}
				else if (currTag.equalsIgnoreCase("</q>")){
					browser.print(currLine);
					currLine = "";
					browser.print("\"");
				}
				else if (currTag.equalsIgnoreCase("</h1>")){
					for (int ind = 0; ind < currLine.length(); ind+=40){
						browser.printHeading1(currLine.substring(ind, Math.min(ind+40, currLine.length())));
						if (ind + 40 < currLine.length()) browser.println();
					}
					currLine = "";
				}
				else if (currTag.equalsIgnoreCase("</h2>")){
					for (int ind = 0; ind < currLine.length(); ind+=50){
						browser.printHeading2(currLine.substring(ind, Math.min(ind+50, currLine.length())));
						if (ind + 50 < currLine.length()) browser.println();
					}
					currLine = "";
				}
				else if (currTag.equalsIgnoreCase("</h3>")){
					for (int ind = 0; ind < currLine.length(); ind+=60){
						browser.printHeading3(currLine.substring(ind, Math.min(ind+60, currLine.length())));
						if (ind + 60 < currLine.length()) browser.println();
					}
					currLine = "";
				}
				else if (currTag.equalsIgnoreCase("</h4>")){
					for (int ind = 0; ind < currLine.length(); ind+=80){
						browser.printHeading4(currLine.substring(ind, Math.min(ind+80, currLine.length())));
						if (ind + 80 < currLine.length()) browser.println();
					}
					currLine = "";
				}
				else if (currTag.equalsIgnoreCase("</h5>")){
					for (int ind = 0; ind < currLine.length(); ind+=100){
						browser.printHeading5(currLine.substring(ind, Math.min(ind+100, currLine.length())));
						if (ind + 100 < currLine.length()) browser.println();
					}
					currLine = "";
				}
				else if (currTag.equalsIgnoreCase("</h6>")){
					for (int ind = 0; ind < currLine.length(); ind+=120){
						browser.printHeading6(currLine.substring(ind, Math.min(ind+120, currLine.length())));
						if (ind + 120 < currLine.length()) browser.println();
					}
					currLine = "";
				}
			}
			else{
				
				if (currLine == "" || currTag.equals(".") || currTag.equals("!")
					|| currTag.equals(",") || currTag.equals("?"))currLine += currTag;
				else currLine += " " + currTag;
			}
		}
		if (currLine != "") browser.print(currLine);
	}
}
