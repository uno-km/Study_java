package Java.ch13.ThreadT;
public class ThreadEx19 {
	static long startTime = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadEx19_1 t1 = new ThreadEx19_1();
		ThreadEx19_2 t2 = new ThreadEx19_2();
		t1.start();
		t2.start();
		startTime = System.currentTimeMillis();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
		}
		System.out.println("소요시간 : " + (System.currentTimeMillis() - ThreadEx19.startTime));
	}
}
class ThreadEx19_1 extends Thread {
	public void run() {
		for (int i = 0; i < 300; i++) {
			System.out.print(new String("-"));
		}
	}
}
class ThreadEx19_2 extends Thread {
	public void run() {
		for (int i = 0; i < 300; i++) {
			System.out.print(new String("|"));
		}
	}
}