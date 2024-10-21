/*
 * File Utilities for reading and writing
 * 
 * 	@author Harly Liu
 *  @since 8/23/2024
 */
 import java.util.Scanner;
 import java.io.PrintWriter;
 import java.io.File;
 import java.io.FileNotFoundException;
 
 public class FileUtils{
	
	/** 
	 * Opens a file to read using the Scanner class
	 * @param fileName      name of the file to open
	 * @return              file scanner object to the file
	 */
	public static Scanner openToRead(String fileName){
		Scanner input = null;
		try{
			input = new Scanner(new File(fileName));
		}
		catch(FileNotFoundException e){
			System.err.println("ERROR: Cannot open " + fileName + " for reading.");
			System.exit(1);
		}
		return input;
	}
	
	/**
	 * opens a file to write using the PrintWriter class
	 * @param fileName        name of the fiel to open
	 * @return                the PrintWriter object to the file 
	 */
	 public static PrintWriter openToWrite(String fileName){
		PrintWriter output = null;
		try{
			output = new PrintWriter(new File(fileName));
		}
		catch(FileNotFoundException e){
			System.err.println("ERROR: Cannot open " + fileName + " for writing.");
			System.exit(2);
		}
		return output;
	 } 
		 
 }
