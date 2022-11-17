package Java.ch11.StackQueue;

import java.util.Stack;

public class StackEx1 {
	public static Stack back = new Stack();
	public static Stack forward = new Stack();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		goUrl("1.Nate");
		goUrl("2.Yahoo");
		goUrl("3.Google");
		goUrl("4.Naver");

		printStatus();

		goBack();
		System.out.println("뒤로 버튼을 누른후 ");
		printStatus();

		goBack();

		System.out.println("뒤로 버튼을 누른 후");
		printStatus();

		goUrl("5.Daum");
		System.out.println("새로운 주소로 이동 후 ");
		printStatus();
	}

	public static void printStatus() {
		System.out.println("back : " + back);
		System.out.println("forward : " + forward);
		System.out.println("this widow is '" + back.peek() + "'.");
		System.out.println();
	}

	public static void goUrl(String u) {
		back.push(u);
		if (!forward.empty()) {
			forward.clear();
		}
	}

	public static void goForward() {
		if (forward.empty()) {
			back.push(forward.pop());

		}
	}

	public static void goBack() {
		if (!back.empty()) {
			forward.push(back.pop());
		}
	}
}
