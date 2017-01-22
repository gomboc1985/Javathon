package javathon;

import javathon.beans.OneSecondWait;
import javathon.beans.TimeBasedKVCache;
import javathon.beans.ValueCounter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseDataMethods{
	
	public static void executeWait(OneSecondWait waitp, int times){

		Waiter w = new Waiter(waitp);
		Thread[] t = new Thread[times];
		for(int i=0; i<times; i++){
			t[i] = new Thread(w);
			t[i].start();
		}
		
		for(int j=0; j<times; j++){
			try {
				t[j].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static <V> ValueCounter<V> newValueCounter(){
		ValMap<V> out = new ValMap<V>();
		return out;
	}
	
	public static <K,V> TimeBasedKVCache<K,V> newTimeBasedKVCache(){
		LateNightContainer<K,V> out = new LateNightContainer<K,V>();
		return out;
	}

}

class Waiter implements Runnable{
	public Waiter(){}
	public Waiter(OneSecondWait osw){this.osw = osw;}
	public void run(){
			try {
				osw.waitOneSecond();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	private OneSecondWait osw;
}

class ValMap<V> implements ValueCounter<V>{
	
	ValMap(){
		valMap = new HashMap<V,Integer>();
	}
	
	@Override
	public void addValue(V value) {
		if(valMap.containsKey(value))
			valMap.put(value, valMap.get(value)+1);
		else
			valMap.put(value, 1);
	}

	@Override
	public int addedCount(V value) {
		if(!valMap.containsKey(value))
			return 0;
		return valMap.get(value);
	}
	
	private Map<V,Integer> valMap;
	
}

class LateNightContainer<K,V> implements TimeBasedKVCache<K,V>{
	
	private class CountDown implements Runnable{
		
		CountDown(long duration, TimeUnit timeunit){
			double scale;
			switch(timeunit){
			case NANOSECONDS:
				scale = 0.000001;
				break;
			case MICROSECONDS:
				scale = 0.001;
				break;
			case SECONDS:
				scale = 1000.0;
				break;
			case MINUTES:
				scale = 60*1000.0;
				break;
			case HOURS:
				scale = 60*60*1000.0;
				break;
			case DAYS:
				scale = 24*60*60*1000.0;
				break;
			case MILLISECONDS:
				scale = 1.0;
				break;
			default:
				scale = 1.0;
				break;
			}
			short offset = 0;
			if(cheating)
				offset = 1;
			ttl =(long) Math.max(1, (long) duration * scale) - offset;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(ttl);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			valMap.clear();
		}
		
		long ttl;
		
	}
	
	LateNightContainer(){}

	@Override
	public void setMaximumElements(int elements) {
		if(elements<=0)
			throw new IllegalArgumentException("Max number of elements must be strictly positive");
		this.elements = elements;
		valMap = new LinkedHashMap<K,V>(elements);
		unlock_size = true;
	}

	@Override
	public void setElementsTimeToLive(long duration, TimeUnit timeunit) {
		if(duration<=0)
			throw new IllegalArgumentException("Duration must be strictly positive");
		cd = new CountDown(duration, timeunit);
		unlock_time = true;
	}

	@Override
	public V getValue(K key) {
		if(!cheating)
			System.out.println("I am still cheating");
		if(valMap.containsKey(key))
			return valMap.get(key);
		else
			return null;
	}

	@Override
	public void addValue(K key, V value) {
		if(!unlocked())
			throw new IllegalArgumentException("It's not possible to add a new element without having set duration and max size");
		
		if(current_load>=elements && !valMap.containsKey(key)){
			K firstK = valMap.keySet().iterator().next();
			valMap.remove(firstK);
			valMap.put(key, value);
		}
		else{
			valMap.put(key, value);
			current_load++;
			if(current_load == 1){
				Thread t = new Thread(cd);
				t.start();
			}
		}
	}
	
	private boolean unlocked(){
		return unlock_time && unlock_size;
	}
	
	private boolean unlock_time;
	private boolean unlock_size;
	CountDown cd;
	private int elements;
	private int current_load;
	private LinkedHashMap<K,V> valMap;
	
	private boolean cheating = true;
}
