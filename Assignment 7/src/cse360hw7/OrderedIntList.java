/**
 * @author Debra Calliss
 * 
 * OrderedIntList for JUnit test problem
 *
 */

package cse360hw7;
public class OrderedIntList {
	
	private int[] list;  
	private int count;
	
	/**
	 * Default constructor for a size of 10
	 */
			
	OrderedIntList ()
	{
		list = new int[10];
		count = 0;
	}
	
	/**
	 * Constructor to create an array of size specified
	 * 
	 * @param newsize specified size of new array
	 */
	
	OrderedIntList (int newsize)
	{
		list = new int[newsize];
		count = 0;
	}

	/**
	 * size
	 * 
	 * return the current size of the array, not the number of elements
	 * 
	 * @return length
	 */
	public int size () {
		return list.length;
	}
	
	// search for an item using the binary search algorithm
	
	private int search (int target, int low, int high) {
		
		if (low > high)
			return -1;
		else {
			int mid = (low + high) / 2;
			if (list[mid] == target) 
				return mid;
			else
				if (list[mid] < target)
					return search (target, mid + 1, high);
				else
					return search (target, low, mid - 1);
			}
	}
	
	/**
	 * insert
	 * 
	 * If the array is full, then increase the size by 50%.  Then insert the item
	 * into the array at the correct ordered position.  Do not allow duplicates.
	 * 
	 * @param val
	 */
	public void insert (int val) {
		if (count == list.length) {
			int newsize = count + count / 2;
			copyArray (newsize);
		}
		int pos = 0;
		while (pos < count && list[pos] < val)
			pos++;
		if (pos == count || list[pos] != val) {
			for (int index = count; index > pos; index--) 
				list[index] = list[index - 1];
			list[pos] = val;
			count++;
		}
	}
	
	// create a new array with the smaller size
	
	private void copyArray (int newsize) {
		int [] temp = new int [newsize];
		
		for (int index = 0; index < count; index++)
			temp[index] = list[index];
		
		list = temp;
	}
	
	/**
	 * delete
	 * 
	 * Search for and remove the item from the array by moving all items
	 * up in the array.  If the list is less than half full, copy the memory
	 * to a smaller array.
	 * 
	 * If the item is not in array, do not make any changes.
	 * 
	 * @param val integer value to remove from the array
	 */
	
	public void delete (int val) {
		int pos = search (val, 0, count - 1);
		
		if (pos != -1) {
			for (int index = pos + 1; index < count; index++)
				list[index - 1] = list[index];
			count--;
			
			int min = list.length / 2;
			if (count < min) {
				int newsize = list.length * 4 / 10;
				copyArray (newsize);
			}
		}
	}
	
	/** toString
	 *  return the contents of the array separated by a space 
	 */
	
	public String toString () {
		
		String str = "";
		if (count > 0) {
			str = "" + list[0];
			for (int index = 1; index < count; index++)
				str += " " + list[index];
		}
		return str;
	}
}
