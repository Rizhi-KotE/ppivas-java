package frm;

import java.util.HashMap;
import java.util.Map;

public class Counter {
	private static Map<Class,Integer> counters;
	public static int getNextNum(Object o){
		if(counters == null){
			counters = new HashMap<Class, Integer>();
		}
		Integer i = counters.get(o.getClass());
		if(i == null){
			counters.put(o.getClass(), Integer.MIN_VALUE + 1);
			i = Integer.MIN_VALUE;
		}
		else{
			Integer j =counters.get(o.getClass());
			j++;
		}
		return i;
	}
}
