package javathon;

import static javathon.CollectionMethods.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class CollectionMethodsTest {
	
	@Test
	public void boundaryTest(){
		/*
		 * find max & min on a given array
		 */
		assertEquals(3, max(new int[]{-3,1,2,3,-1,0}));
		assertEquals(-3, min(new int[]{-3,1,2,3,-1,0}));
	}
	
	@Test
	public void sortArrayTest(){
		/*
		 * sort arrays
		 */
		assertArrayEquals(new boolean[]{true, true, true, false, false}, sortIt(new boolean[]{false, true, true, false, true}));
		assertArrayEquals(new int[]{1,2,3,4,5}, sortIt(new int[]{4,2,5,3,1}));
		assertArrayEquals(new int[]{1,2,3,4,5}, sortIt(new int[]{1,2,3,4,5}));
	}
	
	@Test
	public void filterTest(){
		/*
		 * filter arrays
		 */
		assertArrayEquals(new int[]{1,2,3}, removeNegative(new int[]{-3,-2,-1,1,2,3}));
		assertArrayEquals(new int[]{0,1,2}, removeNegative(new int[]{-3,1,0,2,-1}));
	}
	
	@Test
	public void countTest(){
		/*
		 * count elements in array
		 */
		assertEquals(1, count(new String[]{"x","y","z"}, "x"));
		assertEquals(3, count(new String[]{"x","y","z","x","x"}, "x"));

		/*
		 * count most used string
		 */
		assertEquals(3, maxRepetitions(new String[]{"x","y","z","x","x"}));
		assertEquals(1, maxRepetitions(new String[]{"x","y","z"}));
	}
	
	@Test
	public void mergeAndSortTest(){
		/*
		 * merge & sort two arrays
		 */
		assertArrayEquals(new int[]{1,2,3,4,5,6}, mergeAndSort(new int[]{3,1,5}, new int[]{6,2,4}));
		assertArrayEquals(new int[]{-3,-2,-1,0,1,2,3}, mergeAndSort(new int[]{-1,0,1}, new int[]{-2,0,2}, new int[]{-3,0,3}));
	}

}
