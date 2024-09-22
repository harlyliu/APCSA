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
			if (!((int)str.charAt(i) >= 65 &&  (int)str.charAt(i) <=90)
				&& !((int)str.charAt(i)  >= 97 && (int)str.charAt(i) <=122)){
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
		do{
			key = Prompt.getString("Please input a word to use as key (letters only) ");
		}while(!isValid(key));
		key.toUpperCase();
		/* Prompt for encrypt or decrypt */
		int encryptOrDecrypt = Prompt.getInt("Encrypt or decrypt? (1, 2)");
		/* Prompt for an input file name */
		String inFileName = "";
		if (encryptOrDecrypt == 1){
			inFileName = Prompt.getString("Name of file to encrypt");
		}
		else{
			inFileName = Prompt.getString("Name of file to decrypt");
		}
		
		/* Prompt for an output file name */
		
		String outputFileName = "";
		outputFileName = Prompt.getString("Name of output file");
		
		/* Read input file, encrypt or decrypt, and print to output file */
		Scanner input = FileUtils.openToRead(inFileName);
		PrintWriter output = FileUtils.openToWrite(outputFileName);
		
		int currkey = 0;
		String ansstring;
		while(input.hasNext()){
			ansstring = "";
			String currline = input.nextLine();
			for (int i = 0; i < currline.length(); i++){
				if (!((int)currline.charAt(i) >= 65 &&  (int)currline.charAt(i) <=90)
					&& !((int)currline.charAt(i)  >= 97 && (int)currline.charAt(i) <=122)){
						ansstring += currline.charAt(i);
						continue;
					}
				int shift = ((int)key.charAt(currkey)-64)%26;
				char newchar;
				if (encryptOrDecrypt == 1){
					if ((int)currline.charAt(i)  >= 65 && 
					(int)currline.charAt(i) <=90){
						newchar = (char)(((int)currline.charAt(i)-65 + shift)%26 + 65);
					}
					else{
						newchar = (char)(((int)currline.charAt(i)-97 + shift)%26 + 97);
					}
				}
				else{
					if ((int)currline.charAt(i)  >= 65 && 
					(int)currline.charAt(i) <=90){
						System.out.println(currline.charAt(i));
						System.out.println(shift);
						newchar = (char)(((int)currline.charAt(i)-65-shift+26)%26 + 65);
					}
					else{
						newchar = (char)(((int)currline.charAt(i)-97-shift+26)%26 + 97);
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
