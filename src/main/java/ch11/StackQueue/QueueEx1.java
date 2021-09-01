package ch11.StackQueue;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Scanner;

public class QueueEx1 {
	static Queue q = new LinkedList();
	static final int MAX_SIZE = 5;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("help를 입력하면 도움말을 볼 수 있습니다.");

		while (true) {
			System.out.println(">>");
			try {
				Scanner s = new Scanner(System.in);
				String input = s.nextLine();
				if ("".equals(input)) {
					continue;
				}
				if (input.equals("q")) {
					System.exit(0);

				} else if (input.equalsIgnoreCase("help")) {
					System.out.println("help = 도움말을 보여줍니다.");
					System.out.println("q또는 Q = 프로그램을 종료합니다.");
					System.out.println("history = 최근에 입력한 명령어를 " + MAX_SIZE + "개 보여줍니다.");
				} else if (input.equalsIgnoreCase("history")) {
					int i = 0;
					save(input);
					LinkedList tmp = (LinkedList) q;
//					ListIterator it = tmp.listIterator(); // 이걸 이용해도됨 그런데 워낙 잘안씀
//					while (it.hasNext()) {
//						System.out.println(++i + "." + it.next());
//					}
					final int SIZE = tmp.size();
					for (int j = 0; j < SIZE; j++) {
						System.out.println((j + 1) + "." + tmp.get(j));
					}
				} else {
					save(input);
					System.out.println(input);
				}
			} catch (Exception e) {
				System.out.println("입력오류입니다.");
			}
		}
	}

	public static void save(String input) {
		if (!"".equals(input)) {
			q.offer(input);
		}
		if (q.size() > MAX_SIZE) {
			q.remove();
		}
	}

}
