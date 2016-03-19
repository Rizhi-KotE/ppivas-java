package frm;

import java.util.HashMap;
import java.util.Map;

public class Counter {
	private static Map<Class<?>, Integer> counters;
	public static int getNextNum(Class<?> o){
		if(counters == null){
			counters = new HashMap<Class<?>, Integer>();
		}
		Integer i = counters.get(o);
		if(i == null){
			counters.put(o, Integer.MIN_VALUE + 1);
			i = Integer.MIN_VALUE;
		}
		else{
			Integer j =counters.get(o);
			j++;
			counters.replace(o, j);
		}
		return i;
	}
}
