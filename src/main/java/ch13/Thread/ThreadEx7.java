package ch13.Thread;

import javax.swing.JOptionPane;

public class ThreadEx7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadEx7_1 th1 = new ThreadEx7_1();
		ThreadEx7_2 th2 = new ThreadEx7_2();
		th1.start();
		String input = JOptionPane.showInputDialog("아무값이나 입력하세요.");
		System.out.println("당신이 입력한 값 : " + input);
		th2.start();
	}
}

class ThreadEx7_1 extends Thread {

	public void run() {
		for (int i = 10; i > 0; i--) {
			System.out.println("-----쓰레드 1의 카운트 다운 : " + i + "-----");
			try {
				sleep(1000);
			} catch (Exception e) {

			}
			if (i == 1) {
				System.out.println("쓰레드 1종료");
				System.exit(0);
			}
		}
	}
}

class ThreadEx7_2 extends Thread {
	public void run() {
		for (int i = 10; i > 0; i--) {
			System.out.println("=====쓰레드 2의 카운트 다운 : " + i + "=====");
			try {
				sleep(1000);
			} catch (Exception e) {

			}
			if (i == 1) {
				System.out.println("쓰레드 2종료");
			}
		}
	}
}