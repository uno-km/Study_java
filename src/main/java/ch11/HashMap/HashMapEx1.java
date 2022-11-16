package main.java.ch11.HashMap;

import java.util.HashMap;
import java.util.Scanner;

public class HashMapEx1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("myId", "1234");
		map.put("asdf", "1234");
		map.put("asdf", "7334");
		Scanner s = new Scanner(System.in);

		while (true) {
			System.out.println("id와 pw를 입력하세요");
			System.out.println("id : ");
			String id = s.nextLine().trim();

			System.out.println("pw : ");
			String pw = s.nextLine().trim();
			System.out.println();

			if (!map.containsKey(id)) {
				System.out.println("your enterd id not exist, please try again to sign in");
				continue;
			}
			if (!map.get(id).equals(pw)) {
				System.out.println("The password your entered is invaild, please try agian");
			} else {
				System.out.println("The id and pw are right");
				break;
			}
		}
	}

}
