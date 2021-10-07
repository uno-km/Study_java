package ch13.Thread;

public class ThreadEx1 {
	public static void main(String args[]) {
		ThreadTest1_1 t1 = new ThreadTest1_1();
		Runnable r = new ThreadTest1_2();
		Thread t2 = new Thread(r);

		t1.start();
		t2.start();
	}

}

class ThreadTest1_1 extends Thread {
	public void run() {
		for (int i = 0; i < 700; i++) {
			System.out.print(0);
		}
	}
}

class ThreadTest1_2 implements Runnable {
	public void run() {
		for (int i = 0; i < 700; i++) {
			System.out.print(1);
		}
	}
}
