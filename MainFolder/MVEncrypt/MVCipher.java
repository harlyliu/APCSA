// imports go here
 import java.util.Scanner;
 import java.io.PrintWriter;
/**
 *	MVCipher - Add your description here
 *	Requires Prompt and FileUtils classes.
 *	
 *	@author	Harly Liu
 *	@since	9/20/2024
 */
public class MVCipher {
	
	// fields go here
	private final int NUM_LETTERS = 26;
	/** Constructor */
	public MVCipher() { }
	
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	public boolean isValid(String str){
		if (str.length() < 3){
			 System.out.println("ERROR: Key must be all letters and at"+
				" least 3 characters long");
			 return false;
		}
		for (int i = 0; i < str.length(); i++){
			if (!(str.charAt(i) >= 'A' &&  str.charAt(i) <= 'Z')
					&& !(str.charAt(i)  >= 'a' && str.charAt(i) <='z')){
				System.out.println("ERROR: Only allowed to use alphabet");
				return false;
			} 
		}
		return true;
	}
	
	/**
	 *	Method header goes here
	 */
	public void run() {
		System.out.println("\n Welcome to the MV Cipher machine!\n");
		
		/* Prompt for a key and change to uppercase
		   Do not let the key contain anything but alpha
		   Use the Prompt class to get user input */
		String key;
		System.out.println();
		do{
			key = Prompt.getString("Please input a word to use as key (letters only) ");
		}while(!isValid(key));
		key = key.toUpperCase();
		/* Prompt for encrypt or decrypt */
		System.out.println();
		int encryptOrDecrypt = Prompt.getInt("Encrypt or decrypt? (1, 2)");
		System.out.println();
		/* Prompt for an input file name */
		String inFileName = "";
		System.out.println();
		if (encryptOrDecrypt == 1){
			inFileName = Prompt.getString("Name of file to encrypt");
		}
		else{
			inFileName = Prompt.getString("Name of file to decrypt");
		}
		
		/* Prompt for an output file name */
		
		String outputFileName = "";
		outputFileName = Prompt.getString("Name of output file");
		System.out.println();
		/* Read input file, encrypt or decrypt, and print to output file */
		Scanner input = FileUtils.openToRead(inFileName);
		PrintWriter output = FileUtils.openToWrite(outputFileName);
		
		int currkey = 0;
		String ansstring;
		while(input.hasNext()){
			ansstring = "";
			String currline = input.nextLine();
			for (int i = 0; i < currline.length(); i++){
				if (!(currline.charAt(i) >= 'A' &&  currline.charAt(i) <= 'Z')
					&& !(currline.charAt(i)  >= 'a' && currline.charAt(i) <='z')){
						ansstring += currline.charAt(i);
						continue;
					}
				int shift = (key.charAt(currkey)-'A'+1)%NUM_LETTERS;
				char newchar;
				if (encryptOrDecrypt == 1){
					if (currline.charAt(i)  >= 'A' && 
					currline.charAt(i) <='Z'){
						newchar = (char)((currline.charAt(i)-'A' + shift)%NUM_LETTERS + (int)'A');
					}
					else{
						newchar = (char)((currline.charAt(i)-'a' + shift)%NUM_LETTERS + (int)'a');
					}
				}
				else{
					if (currline.charAt(i)  >= 'A' && 
					currline.charAt(i) <='Z'){
						newchar = (char)((currline.charAt(i)-'A'-shift+NUM_LETTERS)%NUM_LETTERS + (int)'A');
					}
					else{
						newchar = (char)((currline.charAt(i)-'a'-shift+NUM_LETTERS)%NUM_LETTERS + (int)'a');
					}
				}
				currkey++;
				currkey %= key.length();
				ansstring += newchar;
			}
			output.println(ansstring);
		}
		/* Don't forget to close your output file */
		if (encryptOrDecrypt == 1){
			System.out.println("The encrypted file " + outputFileName + " has been"
				+" created using the keyword -> " + key);
		}
		else{
			System.out.println("The decrypted file " + outputFileName + " has been"
				+" created using the keyword -> " + key);
		}
		output.close();
	}
	
	// other methods go here
	
}
