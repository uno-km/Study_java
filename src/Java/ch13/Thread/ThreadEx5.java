package Java.ch13.Thread;

public class ThreadEx5 {
	static long startTime = 0;

	public static void main(String[] args) {
		ThreadEx5_1 th1 = new ThreadEx5_1();
		th1.start();
		startTime = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			System.out.printf("%s", new String("-"));

		}
		System.out.println("소요시간(메인메서드) : " + (System.currentTimeMillis() - startTime));

	}
}

class ThreadEx5_1 extends Thread {
	public void run() {
		for (int i = 0; i < 10000; i++) {
			System.out.printf("%s", new String("|"));
		}
		System.out.println("소요시간(쪼인) : " + (System.currentTimeMillis() - ThreadEx5.startTime));
	}
}