package javathon.beans;

import java.util.concurrent.atomic.AtomicInteger;


public class OneSecondWait {
	
	public static AtomicInteger waitedSeconds = new AtomicInteger(0);
	
	public void waitOneSecond() throws InterruptedException{
		Thread.sleep(1000);
		waitedSeconds.incrementAndGet();
	}
	
	public static void reset(){
		waitedSeconds.set(0);
	}

}
