package ch11.TreeSet;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetLotto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set Tset = new TreeSet(new TestComp());
		Set Hset = new HashSet();

//		for (int i = 0; Tset.size() < 6; i++) {
//			int num = (int) (Math.random() * 45) + 1;
		Tset.add(new Test());
		Tset.add(new Test());
		Tset.add(new Test());
		Tset.add(new Test());
//		}
//		for (int i = 0; Tset.size() < 6; i++) {
//			int num = (int) (Math.random() * 45) + 1;
//			Tset.add(num);
//		} // 여기에 중복값이 저장이 안되니 로또번호가 저장된다.
//		
		for (int i = 0; Hset.size() < 6; i++) {
			int num = (int) (Math.random() * 45) + 1;
			Hset.add(num);
		} // 여기에 중복값이 저장이 안되니 로또번호가 저장된다.
		System.out.println("TreeSet : " + Tset); // 정렬할 필요없음 - 알아서 정렬됨
		System.out.println("HashSet : " + Hset); // 정렬이 안됨

		LinkedList list = new LinkedList(Hset);
		Collections.sort(list);

		System.out.println("-----정렬시작-----");
		System.out.println("LinkedList list = new LinkedList(Hset);");
		System.out.println("Collections.sort(list);");
		System.out.println("-----정렬완료-----");
		System.out.println("HashSet : " + list);
	}

}

class Test { // 비교기준이 없음.

}

class TestComp implements Comparator {

	@Override
	public int compare(Object arg0, Object arg1) {
		return 12;
	}

}
