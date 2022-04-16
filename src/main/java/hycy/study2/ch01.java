package hycy.study2;

import java.util.Scanner;

public class ch01 {
	public static void main(String[] args) {
		System.out.println("hello world");
//		Scanner sc = new Scanner(System.in);
//		String input = sc.nextLine();
//		System.out.println(input);
//		sc.close();
		short a = 3;
		for (byte b = 0; b < 12; b++) {
			System.out.println(b);
		}
		boolean bq = true;
		short q = 3;
		bq = (q!=a);
		System.out.println(bq);
	}
}
