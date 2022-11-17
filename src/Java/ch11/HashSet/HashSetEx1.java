package Java.ch11.HashSet;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetEx1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object[] objArr = { "1", new Integer(1), 1, "2", "3", "4", "4", "4", "5" };
		Set set = new HashSet();
		for (int i = 0; i < objArr.length; i++) {
			System.out.println("추가 성공 유무 : " + objArr[i] + "는 추가 " + set.add(objArr[i]));
		}
		int a = set.size();
		System.out.println(set);
		Iterator it = set.iterator();

		while (it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println(a);

	}

}
