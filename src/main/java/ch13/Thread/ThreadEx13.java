package ch13.Thread;

import javax.swing.JOptionPane;

public class ThreadEx13 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadEx13_1 t1 = new ThreadEx13_1();
		t1.start();
		boolean b = true;
		while (b) {
			System.out.println(b);
			String input = JOptionPane.showInputDialog("아무거나 입력해주세요.");
			System.out.println("입력하신 값은 : " + input);
			if (input.equals("stop")) {
				t1.interrupt();
				System.out.println("digh");
				b = false;
			}
			System.out.println("isInterrupted() : " + t1.isInterrupted());
		}
	}
}

class ThreadEx13_1 extends Thread {
	public void run() {
		int i = 10;
		while (i != 0 && !isInterrupted()) {
			System.out.println(i--);
			for (long x = 0; x < 2500000000L; x++) {

			}

		}
		System.out.println("카운트다운이 종료되었습니다.");

	}
}