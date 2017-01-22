package javathon;

import static javathon.BaseDataMethods.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import javathon.beans.OneSecondWait;
import javathon.beans.TimeBasedKVCache;
import javathon.beans.ValueCounter;

public class BaseDataMethodsTest {
	
	@Test
	public void testWait(){
		/*
		 * executes the given OneSecondWait the given amounts of time 
		 */
		executeWait(new OneSecondWait(), 1);
		assertEquals(1, OneSecondWait.waitedSeconds.get());
		OneSecondWait.reset();
		
		executeWait(new OneSecondWait(), 7);
		assertEquals(7, OneSecondWait.waitedSeconds.get());
		OneSecondWait.reset();
		
		/*
		 * execute OneSecondWait ten times
		 * in less than 5 seconds!
		 */
		long start = System.currentTimeMillis();
		executeWait(new OneSecondWait(), 10);
		assertEquals(10, OneSecondWait.waitedSeconds.get());
		long elapsed = System.currentTimeMillis() - start;
		assertTrue(elapsed < 5000);
		OneSecondWait.reset();
	}
	
	@Test
	public void testCounter(){
		/*
		 * Test a provided ValueCounter
		 */
		ValueCounter<String> counter = newValueCounter();
		assertEquals(0, counter.addedCount("foo"));
		
		counter.addValue("foo");
		assertEquals(1, counter.addedCount("foo"));
		counter.addValue("foo");
		assertEquals(2, counter.addedCount("foo"));
		counter.addValue("foo");
		assertEquals(3, counter.addedCount("foo"));
		
		counter.addValue("bar");
		assertEquals(1, counter.addedCount("bar"));
		assertEquals(3, counter.addedCount("foo"));
		
		assertEquals(0, counter.addedCount("baz"));
	}
	
	@Test
	public void testCache() throws InterruptedException{
		/*
		 * Test a provided TimeBasedKVCache
		 */
		TimeBasedKVCache<String, Integer> cache = newTimeBasedKVCache();
		cache.setElementsTimeToLive(500, TimeUnit.MILLISECONDS);
		cache.setMaximumElements(3);
		
		cache.addValue("1", 1);
		cache.addValue("2", 2);
		cache.addValue("3", 3);
		cache.addValue("4", 4);
		cache.addValue("4", 5);
		
		assertNull(cache.getValue("1"));
		assertEquals(new Integer(2), cache.getValue("2"));
		assertEquals(new Integer(3), cache.getValue("3"));
		assertEquals(new Integer(5), cache.getValue("4"));
		
		Thread.sleep(500);
		
		assertNull(cache.getValue("2"));
		assertNull(cache.getValue("3"));
		assertNull(cache.getValue("4"));
		
	}
	
	
}
