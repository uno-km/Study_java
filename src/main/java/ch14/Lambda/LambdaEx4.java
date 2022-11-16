package main.java.ch14.Lambda;

import java.util.ArrayList;
import java.util.HashMap;

public class LambdaEx4 {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);

		}
		list.forEach(i -> System.out.println(i + ","));
		System.out.println();

		list.removeIf(x -> x % 2 == 0 || x % 3 == 0);
		System.out.println(list);
		list.replaceAll(i -> i * 10);
		System.out.println(list);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("1","1");
		map.put("2","2");
		map.put("3","3");
		map.put("4","4");
		
		map.forEach((k,v)->System.out.println("{"+k+","+v+"},"));
		System.out.println();
	}
}
