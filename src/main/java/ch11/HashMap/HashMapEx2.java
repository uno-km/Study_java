package main.java.ch11.HashMap;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapEx2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("unoKim", new Integer(100));
		map.put("hunaHwang", new Integer(100));
		map.put("dwaniKim", new Integer(80));
		map.put("yahoChun", new Integer(90));

		Set set = map.entrySet();
		Iterator it = set.iterator();

		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			System.out.println("name : " + e.getKey() + ", score : " + e.getValue());
		}
		set = map.keySet();
		System.out.println("member list : " + set);
		Collection values = map.values();
		it = values.iterator();

		int total = 0;

		while (it.hasNext()) {
			Integer i = (int) it.next();
			total += i.intValue();
		}
		System.out.println("total score : " + total);
		System.out.println("average : " + (float) total / set.size());
		System.out.println("highest score : " + Collections.max(values));
		System.out.println("lowest score : " + Collections.min(values));
	}

}
