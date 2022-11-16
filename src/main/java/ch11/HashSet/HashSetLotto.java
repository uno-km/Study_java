package main.java.ch11.HashSet;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class HashSetLotto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set set = new HashSet();

		for (int i = 0; set.size() < 6; i++) {
			int num = (int) (Math.random() * 45) + 1;
			set.add(new Integer(num));
		}
		List list = new LinkedList(set);
		Collections.sort(list);
		System.out.println("list : " + list);
	}

}
