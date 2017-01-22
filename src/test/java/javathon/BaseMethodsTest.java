package javathon;

import static javathon.BaseMethods.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class BaseMethodsTest {
	
	@Test
	public void roundTest(){
		/*
		 * round number
		 */
		assertEquals(4, round(4.1));
		assertEquals(5, round(4.6));
		assertEquals(4, round(4.0f));
		assertEquals(4, round(4));
		assertEquals(4, round("4.44"));
		assertEquals(4, round("4"));
	}


	@Test
	public void bitTest(){
		/*
		 * how many bits to represent the number?
		 */
		assertEquals(3, bitNeeded(4));
		assertEquals(3, bitNeeded(6));
		assertEquals(3, bitNeeded(7));
		assertEquals(9, bitNeeded(477));
	}
	
	@Test
	public void palindromicTest(){
		/*
		 * palindromic numbers
		 */
		assertTrue(palindromic(77));
		assertTrue(palindromic(99));
		assertTrue(palindromic(101));
		assertTrue(palindromic(7667));
		assertFalse(palindromic(1234));
		assertFalse(palindromic(4333));
		assertFalse(palindromic(7657));
	}
	
	@Test
	public void hexTest(){
		/*
		 * hex string to number
		 */
		assertEquals(4, hex("4"));
		assertEquals(514, hex("202"));
	}
	
	@Test
	public void swapTest(){
		/*
		 * replace x where y and y where x
		 * ignoring case
		 */
		assertEquals("codey", swapXY("codex"));
		assertEquals("jollx", swapXY("jolly"));
		assertEquals("yx", swapXY("XY"));
		assertEquals("joex and yavier plaxed hockex", swapXY("Joey and Xavier played hockey"));
	}
	
	@Test
	public void findNumbersTest(){
		/*
		 * find numbers
		 */
		assertArrayEquals(new Number[]{2015, 44, 2.5, .3}, findNumbers("In 2015 we hit 44°C for about 2.5 days in Italy, .3 avg"));
		assertArrayEquals(new Number[]{47, 38}, findNumbers("Plate EX47J--38"));
	}
	
	@Test
	public void reverseTest(){
		/*
		 * reverse
		 */
		assertEquals("kram", reverse("mark",1));
		assertEquals("rkma", reverse("mark",2));
		assertEquals("kmar", reverse("mark",3));
		assertEquals("inehercat", reverse("catherine",3));
		assertEquals("eerincath", reverse("catherine",4));
		assertEquals("rinecathe", reverse("catherine",5));
	}

}