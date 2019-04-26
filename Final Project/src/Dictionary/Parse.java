package Dictionary;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Parse
{
	private static int numWords = 0;

	//This method returns a linked list of all words found in the file
	public static ArrayList<String> parse(String filename) throws FileNotFoundException{	
		//Initializing reader to read in input and ArrayList to store words
		Scanner reader = new Scanner(new BufferedReader(new FileReader(filename)));
		ArrayList<String> file = new ArrayList<String>();
		
		//While there are more input strings, add to linked list
		while(reader.hasNext()){
			//Cleaning input to remove capital letters and punctuation
			String input = reader.next();
			input = input.toLowerCase();
			input = input.replaceAll("[^a-z0-9]", "");
			numWords++;
			
			//Some null characters were input into the ArrayList, this removes them
			if(!input.equals("") && !file.contains(input)){
				file.add(input);
			}
		}
		
		//Closing reader to remove memory leak and returning ArrayList
		reader.close();
		return file;
	}
	
	public static int getNumLines(String filename) throws IOException{
		//Initializing scanner and counter
		Scanner scan = new Scanner(new FileReader(filename));
		int numLines = 0;
		
		//Increments counter for each new line
		while(scan.hasNextLine()){
			numLines++;
			scan.nextLine();
		}
		
		//Closing to prevent resource leak
		scan.close();
		return numLines;
	}
	
	public static int getNumWords(){
		return numWords;
	}
}