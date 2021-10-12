package ch13.Thread;

public class ThreadEx12 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadEx12_1 t1 = new ThreadEx12_1();
		ThreadEx12_2 t2 = new ThreadEx12_2();
		t1.start();
		t2.start();
		try {
			t1.sleep(3000);
		} catch (InterruptedException e) {

		}
		System.out.print("<main> 종료");
	}

}

class ThreadEx12_1 extends Thread {
	public void run() {
		for (int i = 0; i < 300; i++) {
			System.out.print("-");
		}
		System.out.println("<t1> end");

	}
}

class ThreadEx12_2 extends Thread {
	public void run() {
		for (int i = 0; i < 300; i++) {
			System.out.print("|");
		}
		System.out.println("<t2> end");
	}
}