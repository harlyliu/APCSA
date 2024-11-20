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
 *	@author
 *	@version
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
			String currTag = tokens[i];
			if (currTag.charAt(0) == '<' && currTag.charAt(currTag.length()-1) == '>'){
				if (currTag.equalsIgnoreCase("<html>")||
					currTag.equalsIgnoreCase("</html>")||
					currTag.equalsIgnoreCase("<body>")||
					currTag.equalsIgnoreCase("</body>")||
					currTag.equalsIgnoreCase("<!DOCTYPE html>")){}
				else{
					
				}
			}
		}
		// Sample renderings from HtmlPrinter class
		
		// Print plain text without line feed at end
		browser.print("First line");
		
		// Print line feed
		browser.println();
		
		// Print bold words and plain space without line feed at end
		browser.printBold("bold words");
		browser.print(" ");
		
		// Print italic words without line feed at end
		browser.printItalic("italic words");
		
		// Print horizontal rule across window (includes line feed before and after)
		browser.printHorizontalRule();
		
		// Print words, then line feed (printBreak)
		browser.print("A couple of words");
		browser.printBreak();
		browser.printBreak();
		
		// Print a double quote
		browser.print("\"");
		
		// Print Headings 1 through 6 (Largest to smallest)
		browser.printHeading1("Heading1");
		browser.printHeading2("Heading2");
		browser.printHeading3("Heading3");
		browser.printHeading4("Heading4");
		browser.printHeading5("Heading5");
		browser.printHeading6("Heading6");
		
		// Print pre-formatted text (optional)
		browser.printPreformattedText("Preformat Monospace\tfont");
		browser.printBreak();
		browser.print("The end");
		
	}
	
	
}
