package Java.ch11.Iterator;

import java.util.ArrayList;
import java.util.ListIterator;

public class ListIteratorEx1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");

		ListIterator it = list.listIterator();

		while (it.hasNext()) {
			System.out.print(it.next());
		}
		System.out.println();
		while (it.hasPrevious()) {// 이동전 반드시 확인하고 이동해야한다
			System.out.print(it.previous());// 앞으로 이동된 순서를 뒤로이동시킴
		}
		System.out.println();
	}

}
