package ch13.Thread;

import javax.swing.JOptionPane;

public class ThreadEx14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadEx14_1 t1 = new ThreadEx14_1();
		t1.start();
		String input = JOptionPane.showInputDialog("아무거나 입력해주세요.");
		System.out.println("입력하신 값은 : " + input);
		t1.interrupt();
		System.out.println("isInterrupted() : " + t1.isInterrupted());
		System.out.println("isInterrupted() : " + t1.isInterrupted());
	}

}

class ThreadEx14_1 extends Thread {
	public void run() {
		int i = 10;
		while (i != 0 && !isInterrupted()) {
			System.out.println(i--);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				this.interrupt();
			}
		}
		System.out.println("카운트가 종료되었습니다.");
	}
}