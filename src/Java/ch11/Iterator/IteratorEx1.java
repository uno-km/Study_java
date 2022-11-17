package Java.ch11.Iterator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class IteratorEx1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ArrayList list = new ArrayList();
//		Collection list = new ArrayList();
//		Collection list = new TreeSet();
		Collection list = new HashSet();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");

		Iterator it = list.iterator(); // 이터레이터는 셋이나 맵이나 리스트다 다 똑같이 동작
		while (it.hasNext()) {
			Object obj = it.next();
			System.out.println(obj);
		}
		if (it.hasNext()) {
			while (it.hasNext()) {
				Object obj = it.next();
				System.out.println(obj);
			}
		} else {
			System.out.println("empty");
		}
	}

}
