package ch11.HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		ArrayList list = new ArrayList();
		list.add(map);
		list.add("김건호");
		list.add("김찬호");
		list.add("김찬호");
		map.put("가족", list);

		Set set = map.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			System.out.println(e.getKey() + " : " + e.getValue());
		}

	}

	@FunctionalInterface
	interface MyFunction {
		public abstract int max(int a, int b);
	}
}
