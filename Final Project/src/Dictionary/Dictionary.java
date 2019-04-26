package Dictionary;

import java.util.*;

public class Dictionary {

	/*Dictionary object contains a HashMap and an ArrayList*/
	public Map<String, Object> dictionaryHash;
	public ArrayList<String> dictionaryArray;
	public int numWordsAdded = 0;
	
	/*Constructor for a Dictionary object takes an ArrayList of strings as a parameter*/
	public Dictionary(ArrayList<String> inputArray){
		dictionaryArray = inputArray;
		this.createDictionaryHash(dictionaryArray);
	}
	
	/*private method to create a HashMap from the ArrayList given in the constructor*/
	private void createDictionaryHash(ArrayList<String> dictionaryArray){
		dictionaryHash = new HashMap<String, Object>();
		int arrayIterator = 0;
		while (arrayIterator < dictionaryArray.size()){
			dictionaryHash.put(dictionaryArray.get(arrayIterator), null);
			arrayIterator ++;
		}
	}

	/*this function returns the HashMap*/
	public Map<String, Object> getDictionaryHash(){
		return dictionaryHash;
	}
	
	/*this function takes in a string and adds it to the Dictionary's HashMap*/
	public void addWord(String newWord){
		dictionaryHash.put(newWord, null);
		numWordsAdded ++;
	}
	
	/*checks each word in an ArrayList to see if that word is in the Dictionary's HashMap*/
	public ArrayList<String> compare(ArrayList<String> inputFileText){
		ArrayList<String> wordsNotFound = new ArrayList<String>();
		
		if(inputFileText.isEmpty()){
			wordsNotFound = null;
		}
		
		else{
			int arrayIterator = 0;
			while (arrayIterator < inputFileText.size()){
				if (dictionaryHash.containsKey(inputFileText.get(arrayIterator)))
					arrayIterator ++;
				else{
					wordsNotFound.add(inputFileText.get(arrayIterator));
					arrayIterator ++;
				}
			}
		}
		
		return wordsNotFound;
	}
}