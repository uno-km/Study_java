package main.java.ch11.Collection;

import static java.util.Collections.addAll;
import static java.util.Collections.binarySearch;
import static java.util.Collections.copy;
import static java.util.Collections.disjoint;
import static java.util.Collections.enumeration;
import static java.util.Collections.fill;
import static java.util.Collections.list;
import static java.util.Collections.max;
import static java.util.Collections.min;
import static java.util.Collections.nCopies;
import static java.util.Collections.replaceAll;
import static java.util.Collections.reverseOrder;
import static java.util.Collections.rotate;
import static java.util.Collections.shuffle;
import static java.util.Collections.sort;
import static java.util.Collections.swap;

// Collections를 생략가능하게함
import static java.lang.System.*;
//비슷하게 시스템을 없앨수있음 System.out.println()에서!
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class CollectionEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		System.out.println(list);
		out.println("qwe");
		addAll(list, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
		System.out.println(list);

		rotate(list, 2);
		System.out.println("rotate(요소를 반시계방향으로 회전(2번)) : " + list);

		swap(list, 0, 2);
		System.out.println("swap(첫 번째(idx = 0)와 세 번째(idx =2)를 교환) : " + list);

		shuffle(list);
		System.out.println("shuffle(무작위 섞기) : " + list);

		sort(list, reverseOrder());
		System.out.println("sort + reverseOrder(내림차순) : " + list);

		sort(list);
		System.out.println("sort(오름차순) : " + list);

		int idx = binarySearch(list, 3);
		System.out.println("binarySearch --- index of 3 = " + idx); // binarySearch를 위해서 항상 전에 정렬을 시켜야한다.

		System.out.println("max = " + max(list));
		System.out.println("min = " + min(list));
		System.out.println("min = " + max(list, reverseOrder()));

		fill(list, 9);
		System.out.println("list = " + list);

		// list와 같은 크기의 새로운 list를 생성하고 2로 채운다. 단, 결과는 변경 불가하다
		List newList = nCopies(list.size(), 2);
		System.out.println("newList = " + newList);

		System.out.println("모든값의 공통요소가 있드만 참, 하나라도 다르면 거짓 : " + disjoint(list, newList));

		copy(list, newList);
		System.out.println("newList = " + newList);
		System.out.println("list = " + list);

		replaceAll(list, 2, 1);
		System.out.println("list = " + list);

		Enumeration e = enumeration(list);
		ArrayList list2 = list(e);

		System.out.println("list2 = " + list2);
	}

}
