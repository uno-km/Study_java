package Java.ch13.Thread;

public class ThreadEx2 {
	public static void main(String args[]) {
		ThreadTest2_1 t1 = new ThreadTest2_1();
		t1.start();
	}
}

class ThreadTest2_1 extends Thread {
	public void run() {
		System.out.println("쓰레드 시작?!");
		throwException();
	}

	private void throwException() {
		// TODO Auto-generated method stub
		try {
			throw new Exception();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
