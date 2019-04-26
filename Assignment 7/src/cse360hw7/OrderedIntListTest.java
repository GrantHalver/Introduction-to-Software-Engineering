//Grant Halver
//PIN: 200
//ASU ID: 1208909126
//CSE360
//Dr. Calliss

package cse360hw7;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrderedIntListTest {

	@Test
	//Tests that an insert with an empty array works
	//Tests that the array is created of the correct size
	public void testInsertEmpty(){
		OrderedIntList list = new OrderedIntList();
		list.insert(5);
		assertTrue(list.toString().equals("5"));
		assertEquals(list.size(), 10);
	}
	
	@Test
	//Tests that 4 is inserted before 5 into the array
	public void testInsertBeginning(){
		OrderedIntList list = new OrderedIntList();
		list.insert(5);
		list.insert(4);
		assertTrue(list.toString().equals("4 5"));
	}
	
	@Test
	//Tests that 6 is inserted after 5 in the array
	public void testInsertEnd(){
		OrderedIntList list = new OrderedIntList();
		list.insert(5);
		list.insert(6);
		assertTrue(list.toString().equals("5 6"));
	}
	
	@Test
	//Tests that 4 is inserted between 3 and 5
	public void testInsertMiddle(){
		OrderedIntList list = new OrderedIntList();
		list.insert(3);
		list.insert(5);
		list.insert(4);
		assertTrue(list.toString().equals("3 4 5"));
	}
	
	@Test
	//Tests that the duplicate 5 is not inserted into the list
	public void testInsertDuplicate(){
		OrderedIntList list = new OrderedIntList();
		list.insert(5);
		list.insert(5);
		assertTrue(list.toString().equals("5"));
	}
	
	@Test
	//Tests that 11 is inserted into the list properly
	//Tests that the array size is increased to the appropriate size
	public void testInsertArrayExpansion(){
		OrderedIntList list = new OrderedIntList();
		list.insert(1);
		list.insert(2);
		list.insert(3);
		list.insert(4);
		list.insert(5);
		list.insert(6);
		list.insert(7);
		list.insert(8);
		list.insert(9);
		list.insert(10);
		list.insert(11);
		assertTrue(list.toString().equals("1 2 3 4 5 6 7 8 9 10 11"));
		assertEquals(list.size(), 15);
	}
	
	@Test
	//Tests that if delete is called upon an empty array no exception occurs
	public void testDeleteEmpty(){
		OrderedIntList list = new OrderedIntList();
		list.delete(2);
		assertTrue(list.toString().equals(""));
	}
	
	@Test
	//Tests that the first number in the list can be deleted
	public void testDeleteBeginning(){
		OrderedIntList list = new OrderedIntList();
		list.insert(1);
		list.insert(2);
		list.insert(3);
		list.delete(1);
		assertTrue(list.toString().equals("2 3"));
	}
	
	@Test
	//Tests that the last number in the list can be deleted
	public void testDeleteEnd(){
		OrderedIntList list = new OrderedIntList();
		list.insert(1);
		list.insert(2);
		list.insert(3);
		list.delete(2);
		assertTrue(list.toString().equals("1 3"));
	}
	
	@Test
	//Tests that a middle number in the list can be deleted
	public void testDeleteMiddle(){
		OrderedIntList list = new OrderedIntList();
		list.insert(1);
		list.insert(2);
		list.insert(3);
		list.delete(3);
		assertTrue(list.toString().equals("1 2"));
	}
	
	@Test
	//Deletes a number that does not exist in array to check for an exception
	public void testDeleteNull(){
		OrderedIntList list = new OrderedIntList();
		list.insert(1);
		list.insert(2);
		list.insert(3);
		list.delete(4);
		assertTrue(list.toString().equals("1 2 3"));
	}
	
	@Test
	//User is able to set size of array to desired size
	public void testListSize(){
		OrderedIntList list = new OrderedIntList(5);
		assertEquals(list.size(), 5);
	}
}
