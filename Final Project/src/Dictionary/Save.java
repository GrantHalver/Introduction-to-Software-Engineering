package Dictionary;

import java.io.*;   // Importing the packages to use function for reading and writing a file
import java.util.*; // Importing the packages to use the hashmap functions

public class Save {
	
	public void saveDictionary(Map<String,Object> map, String dictionaryFile){
	
		try{
		    // Creating a iterator object to iterate through the hashmap
			Iterator<Map.Entry<String, Object>> hashMapIterator = map.entrySet().iterator(); 
             
			// Creating a object write for buffered writer to write into the text file
		    BufferedWriter dictionaryWriter = new BufferedWriter (new FileWriter(dictionaryFile));
		    
		    // Loop to iterate through the hashmap and write it on to the file
		    while (hashMapIterator.hasNext()){ 
		    	
		    	//Creating a object write1 to store the value from the hashmap to be written on to the file
		    	Map.Entry<String, Object> dictionaryMapString = hashMapIterator.next();
		    	//Writing on to the file
		    	dictionaryWriter.write(dictionaryMapString.getKey() + "\n");
		    } 
		//Closing the file and saving it after writing is done
		dictionaryWriter.close();
	    }
		
		// Catching all the exceptions 
		catch (Exception fileWriterException){
			System.out.println("The file could not be written");
		}
             
    }
}