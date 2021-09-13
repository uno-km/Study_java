package ch12.Generic;

import java.util.HashMap;

public class Ex12_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, Student> map = new HashMap<>(); // jdk 1.7 부터 생성자에 타입지정 생략기능
		map.put("javaking", new Student("javaking", 1, 1, 100, 100, 100));
		// public Student get(Object key)
		Student s = map.get("javaking");
		try {
			System.out.println(map.get("uno").name);
		} catch (Exception e) {
			System.out.println("Nothing is here");
		}
		System.out.println(map.get("javaking").name);
	}
}

class Student {
	String name = "";
	int ban; // 반
	int no; // 번호

	int kor;
	int eng;
	int math;

	Student(String name, int ban, int no, int kor, int eng, int math) {
		this.name = name;
		this.ban = ban;
		this.no = no;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
}